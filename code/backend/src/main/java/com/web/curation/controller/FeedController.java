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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Comment;
import com.web.curation.model.FeedData;
import com.web.curation.model.Food;
import com.web.curation.model.FormWrapper;
import com.web.curation.model.Member;
import com.web.curation.model.MyBoard;
import com.web.curation.model.Tag;
import com.web.curation.repo.CommentRepo;
import com.web.curation.repo.FeedDataRepo;
import com.web.curation.repo.FoodRepo;
import com.web.curation.repo.MemberRepo;
import com.web.curation.repo.MyBoardRepo;
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

	@ApiOperation(value = "피드 전체 불러오기")
	@GetMapping("/feed/searchAll")
	public ResponseEntity<Map> searchAll() {

		Map<String, List> map = new HashMap<>();

		List<MyBoard> feedlist = myboardRepo.findAll();
		List<Tag> taglist = tagRepo.findAll();
		List<FeedData> datalist = feedDataRepo.findAll();

		map.put("feedlist", feedlist);
		map.put("taglist", taglist);
		map.put("datalist", datalist);

//		for (MyBoard myBoard : feedlist) {
//			System.out.println(myBoard);
//		}

		if (!feedlist.isEmpty()) {
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		} else {
			return new ResponseEntity<Map>(map, HttpStatus.INTERNAL_SERVER_ERROR);
		}
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

		if (!list.isEmpty()) {
			return new ResponseEntity<List>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "피드에 대한 댓글 등록")
	@PostMapping("/feed/register")
	public ResponseEntity<String> register(@RequestBody Comment comment) {
		System.out.println(comment);

		commentRepo.save(comment);
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

	@ApiOperation(value = "피드 이미지 등록")
	@PostMapping("/feed/img")
	public ResponseEntity<List> registerImg(@RequestParam MultipartFile[] images)
			throws IllegalStateException, IOException {

		List<String> imgList = new ArrayList<String>();

		Long feedNo = myboardRepo.findTopByOrderByNoDesc().getNo()+1; // 가장 최근 피드 번호
		String email = myboardRepo.findTopByOrderByNoDesc().getEmail(); // 가장 최근 피드 작성자

		for (MultipartFile mfile : images) {
			String imgurl = upload(mfile, feedNo, email);
			imgList.add(imgurl);
		}

		return new ResponseEntity<List>(imgList, HttpStatus.OK);
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