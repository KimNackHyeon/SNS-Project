package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Member;
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

//  http://localhost:9999/food/swagger-ui.html
//  http://i3b301.p.ssafy.io:9999/food/swagger-ui.html
@CrossOrigin("*")
@RestController
@RequestMapping("/trade")
public class TradeController {
	@Autowired
	private TradeRepo tradeRepo;
	
	@Autowired
	private MyRefRepo myrefRepo;
	
	@GetMapping("/")
	@ApiOperation(value = "전체 조회")
	public ResponseEntity<Map> searchAll() {
		Map<String, ArrayList<Trade>> map = new HashMap<String, ArrayList<Trade>>();
		ArrayList<Trade> list = new ArrayList<Trade>();
		list = tradeRepo.findAll();
		map.put("list", list);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@PostMapping("/")
	@ApiOperation(value = "교환 가능한 물품만 조회")
	public ResponseEntity<Map> searchByMyRef(@RequestBody Member member) {
		System.out.println(member.toString());
		Map<String, ArrayList<Trade>> map = new HashMap<String, ArrayList<Trade>>();
		ArrayList<MyRef> mylist = new ArrayList<MyRef>();
		mylist = myrefRepo.findByEmail(member.getEmail());
		ArrayList<Trade> tradelist = new ArrayList<Trade>();
		tradelist = tradeRepo.findAll();
		ArrayList<Trade> resultList = new ArrayList<Trade>();
		for (Trade trade : tradelist) {
			for (MyRef myref : mylist) {
				String myfood = myref.getFood();
				if(trade.getTradefood1().equals(myfood) || trade.getTradefood2().equals(myfood)) {
					resultList.add(trade);
				}
			}
		}
		map.put("list", resultList);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
	@GetMapping("/search/{content}")
	@ApiOperation(value = "교환 가능한 물품만 조회")
	public ResponseEntity<Map> searchByContent(@PathVariable String content) {
		Map<String, ArrayList<Trade>> map = new HashMap<String, ArrayList<Trade>>();
		ArrayList<Trade> tradelist = new ArrayList<Trade>();
		tradelist = tradeRepo.findByMyfood(content);
		map.put("list", tradelist);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
//	@PostMapping("/")
//	@ApiOperation(value = "전체 조회")
//	public ResponseEntity<Map> s(@RequestBody ArrayList<Trade> list) {
//		Map<String, ArrayList<Trade>> map = new HashMap<String, ArrayList<Trade>>();
//		System.out.println(list.toString());
//		return new ResponseEntity<Map>(map, HttpStatus.OK);
//	}
//	List<Trade> tradeList = new ArrayList<Trade>();

}
