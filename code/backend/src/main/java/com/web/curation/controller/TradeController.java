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
@RequestMapping("/api/trade")
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
				String myfood = myref.getName();
				if(trade.getTradefood1().equals(myfood) || trade.getTradefood2().equals(myfood)) {
					resultList.add(trade);
				}
			}
		}
		map.put("list", resultList);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	
	@GetMapping("/search/{content}")
	@ApiOperation(value = "검색")
	public ResponseEntity<Map> searchByContent(@PathVariable String content) {
		Map<String, ArrayList<Trade>> map = new HashMap<String, ArrayList<Trade>>();
		ArrayList<Trade> tradelist = new ArrayList<Trade>();
		tradelist = tradeRepo.findByMyfood(content);
		map.put("list", tradelist);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/article/{no}")
	@ApiOperation(value = "우리동네 장터 세부 내용")
	public ResponseEntity<Trade> searchDetail(@PathVariable Long no) {
		Trade tradeDetail = tradeRepo.getByNo(no);
		return new ResponseEntity<Trade>(tradeDetail, HttpStatus.OK);
	}
	
	@PostMapping("/beforeupdate")
	@ApiOperation(value ="우리동네 장터 글 내용 수정 조회")
	public ResponseEntity<Trade> readUpdate(@RequestBody Trade trade) {
		Trade tradeDetail = tradeRepo.getByNo(trade.getNo());
		return new ResponseEntity<Trade>(tradeDetail, HttpStatus.OK);
	}
	
	@PostMapping("/deletetrade")
	@ApiOperation(value="우리동네 장터 글 삭제")
	public ResponseEntity<String> deleteTrade(@RequestBody Trade trade) {
		tradeRepo.deleteByNo(trade.getNo());
		return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
	}
//	{address: this.beforedata.address, content: this.beforedata.content, email: this.beforedata.email, myfood: this.beforedata.myfood, myfood_kor: this.beforedata.myfood_kor, myfoodcount1: this.beforedata.myfoodcount1, myfoodcount2: this.beforedata.myfoodcount2, nickname: this.beforedata.nickname, price: this.beforedata.price, tradefood1: this.beforedata.tradefood1, tradefood1_kor: this.beforedata.tradefood1_kor, tradefood2: this.beforedata.tradefood2, tradefood2_kor: this.beforedata.tradefood2_kor, tradefoodcount1: this.beforedata.tradefoodcount1, tradefoodcount2: this.beforedata.tradefoodcount2}
	@PostMapping("/updatetrade")
	@ApiOperation(value="우리동네 장터 글 수정")
	public ResponseEntity<String> updateTrade(@RequestBody Trade trade) {
		tradeRepo.update(trade.getNo(), trade.getTradefood1(), trade.getTradefood2(), trade.getTradefood1_kor(), trade.getTradefood2_kor(), trade.getMyfoodcount1(), trade.getMyfoodcount2(), trade.getContent(), trade.getAddress(), trade.getTradefoodcount1(), trade.getTradefoodcount2());
		return new ResponseEntity<String>("삭제 성공", HttpStatus.OK);
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
