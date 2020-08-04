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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Member;
import com.web.curation.model.Trade;
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
	
	@GetMapping("/")
	@ApiOperation(value = "전체 조회")
	public ResponseEntity<Map> searchAll() {
		Map<String, ArrayList<Trade>> map = new HashMap<String, ArrayList<Trade>>();
		ArrayList<Trade> list = new ArrayList<Trade>();
		System.out.println("hihihi");
		list = tradeRepo.findAll();
		map.put("list", list);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
//	List<Trade> tradeList = new ArrayList<Trade>();

}
