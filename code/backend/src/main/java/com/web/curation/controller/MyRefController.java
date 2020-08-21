package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.MyRef;
import com.web.curation.model.Trade;
import com.web.curation.repo.MyRefRepo;
import com.web.curation.repo.TradeRepo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })
@CrossOrigin("*")
@RestController
@RequestMapping("/api/myref")
public class MyRefController {

	@Autowired
	private MyRefRepo myrefRepo;
	@Autowired
	private TradeRepo tradeRepo;

	// Create
	@PostMapping("/regist")
	@ApiOperation(value = "나의 음식재료 등록")
	public ResponseEntity<String> registMyRef(@RequestBody MyRef myref) {
		MyRef food = myrefRepo.findByEmailAndName(myref.getEmail(), myref.getName());
		if (food == null) {
			myrefRepo.save(myref);
		} else {
			myrefRepo.update(myref.getEmail(), myref.getName(), myref.getAmount());
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

	// Read
	@GetMapping("/search/{email}")
	@ApiOperation(value = "나의 음식재료 조회")
	public ResponseEntity<Map> myRef(@PathVariable String email) {
		System.out.println(email);
		ArrayList<MyRef> myrefList = myrefRepo.findByEmail(email);
		Map<String, ArrayList<MyRef>> map = new HashMap<String, ArrayList<MyRef>>();

		map.put("myreflist", myrefList);
		return new ResponseEntity<Map>(map, HttpStatus.OK);

	}

	// Delete
	@PostMapping("/delete")
	@ApiOperation(value = "나의 음식 삭제")
	public ResponseEntity<String> deleteMyRef(@RequestBody MyRef myref) {
		System.out.println(myref.toString());
		MyRef food = myrefRepo.findByEmailAndName(myref.getEmail(), myref.getName());
		if (food == null) {
			return new ResponseEntity<String>("삭제할 재료가 없습니다.", HttpStatus.NOT_ACCEPTABLE);
		} else {
			if (food.getAmount() + myref.getAmount() <= 0) {
				myrefRepo.deleteByEmailAndName(myref.getEmail(), myref.getName());
			} else {
				myrefRepo.update(myref.getEmail(), myref.getName(), myref.getAmount());
			}
			return new ResponseEntity<String>("success", HttpStatus.OK);
		}
	}

	@PostMapping("/share")
	@ApiOperation(value = "나의 음식 공유")
	public ResponseEntity<String> shareMyRef(@RequestBody ArrayList<Trade> trade) {
		System.out.println(trade.toString());
		for (Trade trade2 : trade) {
			tradeRepo.save(trade2);
		}
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}
}
