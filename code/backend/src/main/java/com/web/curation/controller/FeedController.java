package com.web.curation.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Comment;
import com.web.curation.model.Member;
import com.web.curation.model.MyBoard;
import com.web.curation.repo.CommentRepo;
import com.web.curation.repo.MyBoardRepo;

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

	@ApiOperation(value = "피드 전체 불러오기")
	@GetMapping("/feed/searchAll")
	public ResponseEntity<List> searchAll() {

		List<MyBoard> list = myboardRepo.findAll();

		for (MyBoard myBoard : list) {
			System.out.println(myBoard);
		}

		if (!list.isEmpty()) {
			return new ResponseEntity<List>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<List>(list, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "피드 게시글 상세정보 불러오기")
	@GetMapping("/feed/search")
	public ResponseEntity<MyBoard> search(@RequestParam Long feedNo) {

		Optional<MyBoard> myBoard = myboardRepo.findMyBoardByNo(feedNo);
		System.out.println(myBoard);
		if (myBoard.isPresent()) {
			return new ResponseEntity<MyBoard>(myBoard.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<MyBoard>(myBoard.get(), HttpStatus.INTERNAL_SERVER_ERROR);
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

	@ApiOperation(value = "피드 등록")
	@PostMapping("/feed/registerfeed")
	public ResponseEntity<String> registerFeed(@RequestBody MyBoard myBoard) {
		System.out.println(myBoard);
		if (myBoard.getEmail() != null) {
			myboardRepo.save(myBoard);
			return new ResponseEntity<String>("success", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}