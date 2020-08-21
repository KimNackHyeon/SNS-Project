package com.web.curation.controller;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.Alarm;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Evaluate;
import com.web.curation.model.FeedData;
import com.web.curation.model.Follow;
import com.web.curation.model.GroupBuying;
import com.web.curation.model.Member;
import com.web.curation.model.MyBoard;
import com.web.curation.model.MyRef;
import com.web.curation.model.Scrap;
import com.web.curation.repo.AlarmRepo;
import com.web.curation.repo.EvaluateRepo;
import com.web.curation.repo.FeedDataRepo;
import com.web.curation.repo.FollowRepo;
import com.web.curation.repo.MemberRepo;
import com.web.curation.repo.MyBoardRepo;
import com.web.curation.repo.MyRefRepo;
import com.web.curation.repo.ScrapRepo;
import com.web.curation.service.MemberService;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//  http://localhost:9999/food/swagger-ui.html
//  http://i3b301.p.ssafy.io:9999/food/swagger-ui.html
@CrossOrigin("*")
@RestController
@RequestMapping("/api/")
public class AccountController {

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	MemberService memberService;

	@Autowired
	MyBoardRepo myboardRepo;

	@Autowired
	FollowRepo followRepo;

	@Autowired
	MyRefRepo myrefRepo;

	@Autowired
	FeedDataRepo feedDataRepo;

	@Autowired
	ScrapRepo scrapRepo;

	@Autowired
	AlarmRepo alarmRepo;
	
	@Autowired
	EvaluateRepo evaluateRepo;

	@ApiOperation(value = "로그인 처리")
	@PostMapping("/account/login")
	public ResponseEntity<Map> login(@RequestBody Member member) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println(member.getEmail() + " " + member.getPassword());
		Optional<Member> userOpt = memberRepo.findUserByEmailAndPassword(member.getEmail(), member.getPassword());
		System.out.println(userOpt.isPresent());
		if (userOpt.isPresent()) {
			System.out.println("로그인된 아이디 정보");
			System.out.println(userOpt.get().getEmail());
			String token = getToken(userOpt.get());
			map.put("token", token);
			map.put("userinfo", userOpt.get());
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "카카오 로그인 처리")
	@PostMapping("/account/kakaologin")
	public ResponseEntity<Object> kakaologin(@RequestBody Member member) {
		System.out.println(member);
		Member userOpt = memberRepo.getUserByEmail(member.getEmail());
		if (userOpt == null) { // 중복된 이메일이 없으면
			System.out.println("로그인된 아이디 정보");
			member.setEvalCount(1L);
			member.setEvalPoint(3L);
			memberRepo.save(member);
			return new ResponseEntity<Object>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<Object>(userOpt, HttpStatus.OK);
		}
	}

	@ApiOperation(value = "내 냉장고")
	@GetMapping("/account/myref/{email}")
	public ResponseEntity<Map> myRef(@PathVariable String email) {
		ArrayList<MyRef> myrefList = myrefRepo.findByEmail(email);
		Map<String, ArrayList<MyRef>> map = new HashMap<String, ArrayList<MyRef>>();
		if (!myrefList.isEmpty()) {
			map.put("myreflist", myrefList);
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "로그아웃 처리")
	@GetMapping("/account/logout")
	public ResponseEntity<String> logout(@RequestParam(value = "token") String token) {
		System.out.println("로그아웃 ============");
		System.out.println(token);
		boolean check = cmpToekn(token);
		System.out.println(check);
		if (check) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "읽지않은 새소식 반환")
	@GetMapping("/account/new/{email}")
	public ResponseEntity<Map> observeNew(@PathVariable String email) {
		Map<String, Alarm> map = new HashMap<String, Alarm>();
		// type 1 : 나를 팔로우함
		// type 2 : 내 게시글에 댓글 달림
		// type 3 : 내 글에 좋아요 눌림
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "토큰 검증")
	@GetMapping("/info")
	public ResponseEntity<String> verify(@RequestParam String token) {
		System.out.println(token);

		boolean check = cmpToekn(token);
		System.out.println(check);
		if (check) {
			return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
		}
	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@Valid @RequestBody Member member) {
		final BasicResponse result = new BasicResponse();
		// 회원가입단을 생성해 보세요.'
		member.setImage("/img/profile_default.png");
		member.setEvalCount(1L);
		member.setEvalPoint(3L);
		memberRepo.save(member);
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/account/searchpwd")
	@ApiOperation(value = "비밀번호 찾기")
	public Object searchpwd(@RequestBody Member Member) {
		Optional<Member> userOpt = memberRepo.findByEmail(Member.getEmail());
		ResponseEntity response = null;
		if (userOpt.isPresent()) {
			final BasicResponse result = new BasicResponse();
//			memberService.sendMail(userOpt.get().getPassword(), userOpt.get().getEmail());
			String certificateNumeber = memberService.sendMailforpassword(userOpt.get().getEmail());
			result.status = true;
			result.data = certificateNumeber;
			result.email = userOpt.get().getEmail();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@PostMapping("/account/researchpwd")
	@ApiOperation(value = "인증번호 다시보내기")
	public Object researchpwd(@RequestBody Member Member) {
		Optional<Member> userOpt = memberRepo.findByEmail(Member.getEmail());
		ResponseEntity response = null;
		if (userOpt.isPresent()) {
			final BasicResponse result = new BasicResponse();
//			memberService.sendMail(userOpt.get().getPassword(), userOpt.get().getEmail());
			String certificateNumeber = memberService.sendMailforpassword(userOpt.get().getEmail());
			result.status = true;
			result.data = certificateNumeber;
			result.email = userOpt.get().getEmail();
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}
	@PostMapping("/account/qna")
	@ApiOperation(value = "관리자에게 문의하기")
	public void researchpwd(@RequestBody GroupBuying groupbuying) {
		memberService.qnaMail(groupbuying.getNickname(), groupbuying.getTitle(), groupbuying.getContent());
	}

	@PostMapping("/account/findpwd")
	@ApiOperation(value = "비밀번호 전송")
	public Object findpwd(@RequestBody Member Member) {
		Optional<Member> userOpt = memberRepo.findByEmail(Member.getEmail());
		ResponseEntity response = null;
		if (userOpt.isPresent()) {
			final BasicResponse result = new BasicResponse();
			memberService.sendMail(userOpt.get().getPassword(), userOpt.get().getEmail());
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}

	@GetMapping("/account/mypage/{email}")
	@ApiOperation(value = "내 페이지 보기")
	public ResponseEntity<Map> showmypage(@PathVariable String email) {
		Long recipe = myboardRepo.countByEmail(email);
		Long following = followRepo.countByEmail(email);
		Long follower = followRepo.countByYourEmail(email);
		Long eval_count = memberRepo.findByEmail(email).get().getEvalCount();
		Long eval_point = memberRepo.findByEmail(email).get().getEvalPoint();
		Map<String, Long> map = new HashMap<String, Long>();
//		final BasicResponse result = new BasicResponse();
//		result.status = true;
		map.put("recipe", recipe);
		map.put("following", following);
		map.put("follower", follower);
		map.put("eval_count",eval_count);
		map.put("eval_point",eval_point);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@GetMapping("/account/yourpage/{email}")
	@ApiOperation(value = "상대 페이지 보기")
	public ResponseEntity<Map> showyourpage(@PathVariable String email) {
//		Optional<MyBoard> myBoardOpt = myboardRepo.getMyBoardByEmail(email);
		Member member = memberRepo.getUserByEmail(email);
		Long following = followRepo.countByEmail(email);
		Long follower = followRepo.countByYourEmail(email);
		Long eval_count = memberRepo.findByEmail(email).get().getEvalCount();
		Long eval_point = memberRepo.findByEmail(email).get().getEvalPoint();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("nickname", member.getNickname());
		map.put("img", member.getImage());
		map.put("following", following);
		map.put("follower", follower);
		map.put("eval_count",eval_count);
		map.put("eval_point",eval_point);
		// 게시글 수 추가

		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@GetMapping("/account/isfollow/")
	@ApiOperation(value = "팔로우 인지 아닌지 검색")
	public ResponseEntity<Boolean> isFollow(@RequestParam String email, @RequestParam String yourEmail) {
		Optional<Follow> fOpt = followRepo.findUserByEmailAndYourEmail(email, yourEmail);

		boolean result = fOpt.isPresent();
		return new ResponseEntity<Boolean>(fOpt.isPresent(), HttpStatus.OK);
	}

	@GetMapping("/account/follow/")
	@ApiOperation(value = "팔로워 탐색")
	public List<Member> followList(@RequestParam String email) {
		List<Follow> list = followRepo.findByYourEmail(email);
		List<Member> followList = new ArrayList<Member>();

		for (Follow f : list) {
			Member member = memberRepo.getUserByEmail(f.getEmail());
			followList.add(member);
		}

		return followList;
	}

	@GetMapping("/account/following/")
	@ApiOperation(value = "팔로잉 탐색")
	public List<Member> followingList(@RequestParam String email) {

		List<Follow> list = followRepo.findByEmail(email);
		List<Member> followingList = new ArrayList<Member>();

		for (Follow f : list) {
			Member member = memberRepo.getUserByEmail(f.getYourEmail());
			followingList.add(member);
		}

		return followingList;
	}

	@PostMapping("/account/follow/")
	@ApiOperation(value = "팔로우 추가")
	public ResponseEntity<String> addFollow(@RequestBody Follow follow) {
		System.out.println(follow);
		/* 팔로우 디비에 저장 */
		followRepo.save(follow);

		/* 알람 디비에 저장 */
		Alarm alarm = new Alarm();
		String sMember = memberRepo.getUserByEmail(follow.getEmail()).getNickname();
		String content = sMember + "님이 회원님을 팔로우합니다.";

		alarm.setEmail(follow.getYourEmail()); // 알람을 받을 사람
		alarm.setType("1"); // 알람 타입 ( 1 : 팔로우 )
		alarm.setConfirm(0L); // 알람 확인 체크 ( 0 : 확인 x 1 : 확인 o )
		alarm.setContent(content);
		alarm.setImage(memberRepo.getUserByEmail(follow.getEmail()).getImage());
		alarm.setSemail(memberRepo.getUserByEmail(follow.getEmail()).getEmail());

		System.out.println(alarm);
		alarmRepo.save(alarm);

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@PostMapping("/account/unfollow/")
	@ApiOperation(value = "팔로우 삭제")
	public ResponseEntity<String> unFollow(@RequestBody Follow follow) {
		Optional<Follow> fOpt = followRepo.findUserByEmailAndYourEmail(follow.getEmail(), follow.getYourEmail());
		System.out.println(follow);
		followRepo.delete(fOpt.get());
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@PostMapping("/account/emailconfirm")
	@ApiOperation(value = "이메일 인증하기")
	public ResponseEntity<HashMap<String, String>> emailconfirm(@RequestBody Member member) {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		// 이메일, 닉네임 중복처리 필수
		System.out.println("hihi");
		if (memberRepo.getUserByEmail(member.getEmail()) != null) {
			hashmap.put("data", "1");
		} else {
			String code = memberService.sendMail(member.getEmail());
			hashmap.put("data", code);
		}
		return new ResponseEntity<HashMap<String, String>>(hashmap, HttpStatus.OK);
	}

	@PostMapping("/account/checkemail")
	@ApiOperation(value = "이메일 중복체크")
	public ResponseEntity<HashMap<String, String>> checkemail(@RequestBody Member member) {
		HashMap<String, String> hashmap = new HashMap<String, String>();
		// 이메일, 닉네임 중복처리 필수
		if (memberRepo.getUserByEmail(member.getEmail()) != null) {
			hashmap.put("data", "1");
		} else {
//			String code = memberService.sendMail(member.getEmail());
			hashmap.put("data", "0");
		}
		return new ResponseEntity<HashMap<String, String>>(hashmap, HttpStatus.OK);
	}

	@PostMapping("/account/nicknameconfirm")
	@ApiOperation(value = "닉네임 중복검사")
	public Object nicknameconfirm(@RequestBody Member member) {
		final BasicResponse result = new BasicResponse();
		// 이메일, 닉네임 중복처리 필수
		if (memberRepo.getUserByNickname(member.getNickname()) != null) {
			result.status = true;
			result.data = "1";
		} else {
			result.status = true;
			result.data = "0";
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PutMapping("/account/update")
	@ApiOperation(value = "회원정보 수정")
	public Object update(@RequestBody Member member) {
		System.out.println(member);
		Member temp = memberRepo.getUserByEmail(member.getEmail());
		final BasicResponse result = new BasicResponse();
		member.setNo(temp.getNo());
		member.setCreate_date(temp.getCreate_date());
		member.setEvalCount(temp.getEvalCount());
		member.setEvalPoint(temp.getEvalPoint());
		memberRepo.save(member);
//		memberRepo.saveAndFlush(member);
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/account/upload")
	@ApiOperation(value = "프로필사진 업로드")
	public Object upload(@RequestParam MultipartFile image, @RequestParam String email)
			throws IllegalStateException, IOException {
		System.out.println("UPLOAD =======================");
		String filename = image.getOriginalFilename(); // 파일 이름
		System.out.println(filename);
		Member member = memberRepo.getUserByEmail(email); // 폴더명
//		String filepath = "/image/" + member.getNo() + "/profile";// 폴더 상대 경로
		String filepath = "/dist/img/" + member.getNo() + "/profile";// 폴더 상대 경로

		String path = System.getProperty("user.dir") + filepath; // 폴더 상대 경로
		System.out.println(path); // 상대경로
		File folder = new File(path);

		if (!folder.exists()) {
			try {
				folder.mkdirs(); // 폴더 생성
				System.out.println("폴더가 생성");

			} catch (Exception e) {
				e.getStackTrace();
			}
		} else {
			System.out.println("폴더가 이미 존재");
		}
		image.transferTo(new File(path + "/" + filename));
		String result = "/img/" + member.getNo() + "/profile/" + filename;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/account/myrecipe/")
	@ApiOperation(value = "내 레시피 탐색")
	public List<FeedData> recipeList(@RequestParam String email) {

		List<MyBoard> boardList = myboardRepo.findByEmail(email);
		List<FeedData> datalist = new ArrayList<FeedData>();
		for (MyBoard myBoard : boardList) {
			FeedData feeddata = feedDataRepo.findByFeedNoAndTindex(myBoard.getNo(), (long) 0);
			datalist.add(feeddata);
		}

		return datalist;
	}

	@GetMapping("/account/myscrap/")
	@ApiOperation(value = "내 스크랩 글 탐색")
	public List<FeedData> scrapList(@RequestParam String email) {

		List<Scrap> scrapList = scrapRepo.findByEmail(email);
		List<FeedData> datalist = new ArrayList<FeedData>();
		for (Scrap scrap : scrapList) {
			FeedData feeddata = feedDataRepo.findByFeedNoAndTindex(scrap.getFeedNo(), (long) 0);
			datalist.add(feeddata);
		}

		return datalist;
	}

	@GetMapping("/account/apitest")
	@ApiOperation(value = "외부 api 호출")
	public String callapi() {
		StringBuffer result = new StringBuffer();
		System.out.println("외부 api 호출 ======================");
		try {
			String urlstr = "http://www.kamis.or.kr/service/price/xml.do?" + "action=dailySalesList"
					+ "&p_cert_key=73081fa5-fa86-492a-b3f3-905e315da6ac" + "&p_cert_id=1137" + "&p_returntype=json";

			URL url = new URL(urlstr);
			HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
			urlconnection.setRequestMethod("GET");

			BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(), "UTF-8"));

			String returnLine;

			while ((returnLine = br.readLine()) != null) {
				result.append(returnLine);
			}
			urlconnection.disconnect();

		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(result.toString());
		return result.toString();
	}

	@GetMapping("/account/alarm")
	@ApiOperation(value = "내 알람 탐색")
	public List<Alarm> alarmList(@RequestParam String email) {
		System.out.println(email);
		List<Alarm> alarmList = alarmRepo.findByEmail(email);
		for (Alarm alarm : alarmList) {
			System.out.println(alarm);
		}

		return alarmList;
	}

	@PostMapping("/account/freshalarm")
	@ApiOperation(value = "평가 알람")
	public ResponseEntity<String> freshAlarm(@RequestBody Alarm alarm) {
		System.out.println(alarm.toString());
		
		Evaluate eval = new Evaluate();
		eval.setEmail(alarm.getSemail());
		eval.setNickname(memberRepo.findByEmail(alarm.getSemail()).get().getNickname());
		
		Evaluate e = evaluateRepo.save(eval);
		System.out.println(e);
		
		alarm.setConfirm(0L);
		alarm.setFeedNo(e.getNo());
		alarmRepo.save(alarm);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/account/alarmcheck")
	@ApiOperation(value = "알람 체크")
	public ResponseEntity<String> alarmCheck(@RequestParam Long no) {
		Optional<Alarm> alarm = alarmRepo.findById(no);
		alarm.get().setConfirm(1L);
		System.out.println(alarm.get().toString());
		alarmRepo.save(alarm.get());
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	
	@GetMapping("/account/evaluate")
	@ApiOperation(value = "신선도 페이지 이동")
	public ResponseEntity<Evaluate> moveEval(@RequestParam Long no) {
		Optional<Evaluate> eval = evaluateRepo.findById(no);
		return new ResponseEntity<Evaluate>(eval.get(), HttpStatus.OK);
	}
	
	@PostMapping("/account/evaluate")
	@ApiOperation(value = "신선도 평가")
	public ResponseEntity<String> addEval(@RequestBody Map<String, String> map) {
		Optional<Evaluate> eval = evaluateRepo.findById(Long.parseLong(map.get("no")));
		
		Member member = memberRepo.findByEmail(map.get("email")).get();
		member.setEvalCount(member.getEvalCount()+1L);
		member.setEvalPoint(member.getEvalPoint()+ Long.parseLong(map.get("score")));
		
		evaluateRepo.delete(eval.get());
		
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	static Signer signer = HMACSigner.newSHA256Signer("coldudong");

	public String getToken(Member member) {
		// Useremail로 토큰을 만든다.
		// plusMinutes 는 토큰을 등록하는 시간임 지금은 1분
		JWT jwt = new JWT().setIssuer(member.getEmail()).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject("hellossafy").setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(30));
		// Sign and encode the JWT to a JSON string representation
		String token = JWT.getEncoder().encode(jwt, signer);

		return token;
	}

	public String getToken(String data) {
		// Useremail로 토큰을 만든다.
		// plusMinutes 는 토큰을 등록하는 시간임 지금은 1분
		JWT jwt = new JWT().setIssuer(data).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC)).setSubject("hellossafy")
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(3));
		// Sign and encode the JWT to a JSON string representation
		String token = JWT.getEncoder().encode(jwt, signer);

		return token;
	}

	// 복호화 하는 방법 : 내이름 넣음
	// 토큰이 필요한 API 정보에 대해서 유효성을 체크해주면된다
	static Verifier verifier = HMACVerifier.newVerifier("coldudong");

	// Token이 유효하면 True 유효하지 않으면 False를 반환한다.
	// API를 받을때 유효한 토큰인지 함께 검사한다.
	public boolean cmpToekn(String token) {
		try {
			// Build an HMC verifier using the same secret that was used to sign the JWT
			JWT jwt = JWT.getDecoder().decode(token, verifier);
			assertEquals(jwt.subject, "hellossafy");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}