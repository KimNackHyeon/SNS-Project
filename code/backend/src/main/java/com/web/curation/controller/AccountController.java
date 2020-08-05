package com.web.curation.controller;

import static org.junit.Assert.assertEquals;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Member;
import com.web.curation.model.MyBoard;
import com.web.curation.model.MyRef;
import com.web.curation.repo.FollowRepo;
import com.web.curation.repo.MemberRepo;
import com.web.curation.repo.MyBoardRepo;
import com.web.curation.repo.MyRefRepo;
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
	@ApiOperation(value = "내 냉장고")
	@GetMapping("/account/myref/{email}")
	public ResponseEntity<Map> myRef(@PathVariable String email) {
		ArrayList<MyRef> myrefList = myrefRepo.findByEmail(email);
		Map<String, ArrayList<MyRef>> map = new HashMap<String, ArrayList<MyRef>>();
		if (!myrefList.isEmpty()) {
			System.out.println("hihi");
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
//		Optional<MyBoard> myBoardOpt = myboardRepo.getMyBoardByEmail(email);
		Long following = followRepo.countByEmail(email);
		Long follower = followRepo.countByYourEmail(email);
		Map<String, Long> map = new HashMap<String, Long>();
//		final BasicResponse result = new BasicResponse();
//		result.status = true;
		map.put("following", following);
		map.put("follower", follower);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@PostMapping("/account/emailconfirm")
	@ApiOperation(value = "이메일 인증하기")
	public Object emailconfirm(@RequestBody Member member) {
		final BasicResponse result = new BasicResponse();
		// 이메일, 닉네임 중복처리 필수
		if (memberRepo.getUserByEmail(member.getEmail()) != null) {
			result.status = true;
			result.data = "1";
		} else {
			String code = memberService.sendMail(member.getEmail());
			result.status = true;
			result.data = code;
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
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
		System.out.println(temp);
		final BasicResponse result = new BasicResponse();
		member.setNo(temp.getNo());
		member.setCreate_date(temp.getCreate_date());
		memberRepo.save(member);
//		memberRepo.saveAndFlush(member);
		result.status = true;
		result.data = "success";
		return new ResponseEntity<>(result, HttpStatus.OK);
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