package com.web.curation.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.Alarm;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.Comment;
import com.web.curation.model.FeedData;
import com.web.curation.model.Food;
import com.web.curation.model.FormWrapper;
import com.web.curation.model.LikeFeed;
import com.web.curation.model.Member;
import com.web.curation.model.MyBoard;
import com.web.curation.model.Scrap;
import com.web.curation.model.Tag;
import com.web.curation.repo.AlarmRepo;
import com.web.curation.repo.CommentRepo;
import com.web.curation.repo.FeedDataRepo;
import com.web.curation.repo.FoodRepo;
import com.web.curation.repo.LikeRepo;
import com.web.curation.repo.MemberRepo;
import com.web.curation.repo.MyBoardRepo;
import com.web.curation.repo.ScrapRepo;
import com.web.curation.repo.TagRepo;

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
public class FeedController {

	@Autowired
	MyBoardRepo myboardRepo;

	@Autowired
	CommentRepo commentRepo;

	@Autowired
	TagRepo tagRepo;

	@Autowired
	FoodRepo foodRepo;

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	FeedDataRepo feedDataRepo;

	@Autowired
	LikeRepo likeRepo;

	@Autowired
	ScrapRepo scrapRepo;

	@Autowired
	AlarmRepo alarmRepo;

	@ApiOperation(value = "피드 전체 불러오기")
	@GetMapping("/feed/searchAll")
	public ResponseEntity<Map> searchAll() {

		Map<String, List> map = new HashMap<>();

		List<MyBoard> feedlist = myboardRepo.findAll();
		List<Tag> taglist = tagRepo.findAll();
		List<FeedData> datalist = feedDataRepo.findAll();
		List<Food> foodlist = foodRepo.findAll();

		map.put("feedlist", feedlist);
		map.put("taglist", taglist);
		map.put("datalist", datalist);
		map.put("foodlist",foodlist);

//		for (MyBoard myBoard : feedlist) {
//			System.out.println(myBoard);
//		}

		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "피드 게시글 상세정보 불러오기")
	@GetMapping("/feed/search")
	public ResponseEntity<Map> search(@RequestParam Long feedNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		/* 피드 기본 정보 */
		Optional<MyBoard> myBoard = myboardRepo.findMyBoardByNo(feedNo);
		System.out.println(myBoard);
		map.put("feeddata", myBoard);

		/* 피드 태그 정보 */
		List<Tag> taglist = tagRepo.findByFeedNo(feedNo);
		System.out.println(taglist);
		map.put("taglist", taglist);

		/* 피드 재료 정보 */
		List<Food> foodlist = foodRepo.findByFeedNo(feedNo);
		System.out.println(foodlist);
		map.put("foodlist", foodlist);

		/* 피드 이미지 내용 정보 */
		List<FeedData> datalist = feedDataRepo.findByFeedNo(feedNo);
		System.out.println(datalist);
		map.put("datalist", datalist);
		if (myBoard.isPresent()) {
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map>(null, HttpStatus.NO_CONTENT);
		}
	}

	@ApiOperation(value = "피드에 대한 댓글 불러오기")
	@GetMapping("/feed/searchComment")
	public ResponseEntity<List> searchComment(@RequestParam Long feedNo) {

		List<Comment> list = commentRepo.findByFeedNo(feedNo);

		for (Comment c : list) {
			System.out.println(c);
		}
		return new ResponseEntity<List>(list, HttpStatus.OK);
	}

	@ApiOperation(value = "피드에 대한 댓글 등록")
	@PostMapping("/feed/register")
	public ResponseEntity<String> register(@RequestBody Comment comment) {
		System.out.println(comment);

		/* 댓글 정보 저장 */
		commentRepo.save(comment);

		/* 알람 디비에 저장 */
		Alarm alarm = new Alarm();

		String sMember = comment.getNickname();
		String rMember = myboardRepo.findById(comment.getFeedNo()).get().getEmail();
		String rMember_nickname = myboardRepo.findById(comment.getFeedNo()).get().getNickname();
		String content = sMember + "님이 회원님의 게시물에 댓글을 남겼습니다.";
		if (!sMember.trim().equals(rMember_nickname.trim())) {
			alarm.setEmail(rMember); // 알람을 받을 사람
			alarm.setType("2"); // 알람 타입 ( 2 : 게시글 댓글 )
			alarm.setConfirm(0L); // 알람 확인 체크 ( 0 : 확인 x 1 : 확인 o )
			alarm.setContent(content);
			alarm.setImage(memberRepo.getUserByEmail(comment.getEmail()).getImage());
			alarm.setSemail(memberRepo.getUserByEmail(comment.getEmail()).getEmail());
			alarm.setFeedNo(comment.getFeedNo());

			System.out.println(alarm);
			alarmRepo.save(alarm);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "피드 정보 등록")
	@PutMapping("/feed/write")
	public ResponseEntity<String> registerFeed(@RequestBody FormWrapper data) {
//		System.out.println(data);
		MyBoard feedData = data.getFeedData();
		Food[] food = data.getFood();
		String[] tags = data.getTags();
		String[] contents = data.getContents();
		String[] images = data.getImages();

		System.out.println(feedData);
		System.out.println(Arrays.toString(contents));
		System.out.println(Arrays.toString(images));

		if (images.length == 0)
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		/* 피드 정보 등록 */
		myboardRepo.save(feedData);
		Long feedNo = myboardRepo.findTopByOrderByNoDesc().getNo(); // 가장 최근 피드 번호

		System.out.println(feedNo);
		/* 피드 재료 등록 */
		for (Food f : food) {
			f.setFeedNo(feedNo);
			System.out.println(f);
			foodRepo.save(f);
		}
		System.out.println(Arrays.toString(food));
		/* 피드 태그 등록 */
		for (int i = 0; i < tags.length; i++) {
			Tag tag = new Tag(feedNo, tags[i]);
			tagRepo.save(tag);
		}
		System.out.println(Arrays.toString(tags));

		/* 피드 내용, 이미지 등록 */
		for (int index = 0; index < contents.length; index++) {
			String content = contents[index];
			String imgurl = images[index];
			FeedData feed = new FeedData();
			feed.setFeedNo(feedNo);
			feed.setTindex((long) index);
			feed.setContent(content);
			feed.setImg(imgurl);
			feedDataRepo.save(feed);
		}

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "피드 정보 수정")
	@PutMapping("/feed/update")
	public ResponseEntity<String> updateFeed(@RequestBody FormWrapper data) {
//		System.out.println(data);
		MyBoard feedData = data.getFeedData();
		Food[] food = data.getFood();
		String[] tags = data.getTags();
		String[] contents = data.getContents();
		String[] images = data.getImages();

		System.out.println(feedData);
//		System.out.println(Arrays.toString(food));
//		System.out.println(Arrays.toString(tags));
		System.out.println(Arrays.toString(contents));
		System.out.println(Arrays.toString(images));

		if (images.length == 0)
			return new ResponseEntity<String>("fail", HttpStatus.NO_CONTENT);
		/* 피드 정보 등록 */
		myboardRepo.save(feedData);
		Long feedNo = feedData.getNo(); // 수정할 피드 번호

		System.out.println(feedNo);
		/* 피드 재료 등록 */
		List<Food> oriFood = foodRepo.findByFeedNo(feedNo);
		for (Food f : oriFood) {
			foodRepo.delete(f);
		}

		for (Food f : food) {
			f.setFeedNo(feedNo);
			foodRepo.save(f);
		}

		System.out.println(Arrays.toString(food));
		/* 피드 태그 등록 */

		List<Tag> oriTag = tagRepo.findByFeedNo(feedNo);
		for (Tag t : oriTag) {
			tagRepo.delete(t);
		}

		for (int i = 0; i < tags.length; i++) {
			Tag tag = new Tag(feedNo, tags[i]);
			tagRepo.save(tag);
		}
		System.out.println(Arrays.toString(tags));

		/* 피드 내용, 이미지 등록 */

		List<FeedData> oriFeedData = feedDataRepo.findByFeedNo(feedNo);

		for (FeedData fd : oriFeedData) {
			feedDataRepo.delete(fd);
		}

		for (int index = 0; index < contents.length; index++) {
			String content = contents[index];
			String imgurl = images[index];
			FeedData feed = new FeedData();
			feed.setFeedNo(feedNo);
			feed.setTindex((long) index);
			feed.setContent(content);
			feed.setImg(imgurl);
			feedDataRepo.save(feed);
		}

		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	@ApiOperation(value = "피드 이미지 등록")
	@PostMapping("/feed/img")
	public ResponseEntity<List> registerImg(@RequestParam MultipartFile[] images, @RequestParam String email)
			throws IllegalStateException, IOException {
		System.out.println(email);
		List<String> imgList = new ArrayList<String>();
		Long feedNo = 0L;
		if (myboardRepo.findAll().size() != 0) {
			feedNo = myboardRepo.findTopByOrderByNoDesc().getNo() + 1L; // 가장 최근 피드 번호
		}

		for (MultipartFile mfile : images) {
			String imgurl = upload(mfile, feedNo, email);
			imgList.add(imgurl);
		}

		return new ResponseEntity<List>(imgList, HttpStatus.OK);
	}

	@ApiOperation(value = "피드 좋아요 등록")
	@GetMapping("/feed/like")
	public ResponseEntity<String> addLike(@RequestParam String email, @RequestParam Long feedNo) {
		System.out.println("좋아요 등록 시작============================");
		System.out.println(email + " " + feedNo);
		Optional<MyBoard> boardOpt = myboardRepo.findMyBoardByNo(feedNo); // 해당 글 정보

		Optional<LikeFeed> isLike = likeRepo.findByEmailAndFeedNo(email, feedNo);
		System.out.println(isLike.isPresent());
		if (!isLike.isPresent()) { // 좋아요 안 눌렀으면
			LikeFeed like = new LikeFeed(email, feedNo);
			likeRepo.save(like); // 좋아요 등록하고
			boardOpt.get().setLikecount(boardOpt.get().getLikecount() + 1L); // 좋아요 수 늘려주고
			myboardRepo.save(boardOpt.get()); // 업데이트
			
			
			/* 알람 디비에 저장 */
			Alarm alarm = new Alarm();

			String sMember = memberRepo.getUserByEmail(email).getNickname(); // 좋아요 누른 사람의 닉네임
			String rMember = myboardRepo.findById(feedNo).get().getEmail(); // 작성자의 이메일 
			String rMember_nickname = myboardRepo.findById(feedNo).get().getNickname(); // 작성자의 닉네임
			String content = sMember + "님이 회원님의 게시물을 좋아합니다.";
			if (!sMember.trim().equals(rMember_nickname.trim())) {
				alarm.setEmail(rMember); // 알람을 받을 사람
				alarm.setType("3"); // 알람 타입 ( 3 : 게시글 좋아요 )
				alarm.setConfirm(0L); // 알람 확인 체크 ( 0 : 확인 x 1 : 확인 o )
				alarm.setContent(content);
				alarm.setImage(memberRepo.getUserByEmail(email).getImage());
				alarm.setSemail(memberRepo.getUserByEmail(email).getEmail());
				alarm.setFeedNo(feedNo);

				System.out.println(alarm);
				alarmRepo.save(alarm);
			}
			
			
			return new ResponseEntity<String>("like", HttpStatus.OK);
		} else {
			likeRepo.delete(isLike.get()); // 좋아요 해제하고
			boardOpt.get().setLikecount(boardOpt.get().getLikecount() - 1L); // 좋아요 수 빼주고
			myboardRepo.save(boardOpt.get()); // 업데이트
			return new ResponseEntity<String>("unlike", HttpStatus.OK);
		}
	}

	@ApiOperation(value = "피드 스크랩 등록")
	@GetMapping("/feed/scrap")
	public ResponseEntity<String> addScrap(@RequestParam String email, @RequestParam Long feedNo) {
		System.out.println("스크랩 등록 시작============================");
		System.out.println(email + " " + feedNo);

		Optional<Scrap> isScrap = scrapRepo.findByEmailAndFeedNo(email, feedNo);
		System.out.println(isScrap.isPresent());
		if (!isScrap.isPresent()) { // 스크랩 안 눌렀으면
			Scrap scrap = new Scrap(email, feedNo);
			scrapRepo.save(scrap); // 스크랩 등록하고
			return new ResponseEntity<String>("scrap", HttpStatus.OK);
		} else {
			scrapRepo.delete(isScrap.get()); // 스크랩 해제하고
			return new ResponseEntity<String>("unscrap", HttpStatus.OK);
		}
	}

	@ApiOperation(value = "피드 좋아요 스크랩 체크")
	@GetMapping("/feed/check")
	public ResponseEntity<Map> check(@RequestParam String email, @RequestParam Long feedNo) {

		Map<String, Boolean> map = new HashMap<>();

		Optional<LikeFeed> like = likeRepo.findByEmailAndFeedNo(email, feedNo);
		Optional<Scrap> scrap = scrapRepo.findByEmailAndFeedNo(email, feedNo);

		map.put("like", like.isPresent());
		map.put("scrap", scrap.isPresent());

		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}

	@ApiOperation(value = "피드 삭제")
	@DeleteMapping("/feed/delete")
	public ResponseEntity<String> deleteFeed(@RequestParam Long feedNo) {

		Optional<MyBoard> myBoardOpt = myboardRepo.findMyBoardByNo(feedNo);
		String result = "fail";
		if (myBoardOpt.isPresent()) {
			myboardRepo.delete(myBoardOpt.get());
			result = "success";
		}

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@ApiOperation(value = "피드 댓글 삭제")
	@DeleteMapping("/feed/comment")
	public ResponseEntity<String> deleteComment(@RequestParam Long no) {
//		System.out.println(commentRepo.findById(no).get());
		commentRepo.delete(commentRepo.findById(no).get());
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	public String upload(MultipartFile image, Long feedNo, String email) throws IllegalStateException, IOException {
		System.out.println("UPLOAD =======================");
		System.out.println(image);
		String filename = image.getOriginalFilename(); // 파일 이름
		System.out.println(filename);
		Member member = memberRepo.getUserByEmail(email); // 폴더명
		String filepath = "/dist/img/" + member.getNo() + "/" + feedNo + "/";// 폴더 상대 경로

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
		image.transferTo(new File(path + filename));
		String result = "/img/" + member.getNo() + "/" + feedNo + "/" + filename;
		return result;
	}

}