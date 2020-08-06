package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.web.curation.model.ChatRoom;
import com.web.curation.repo.ChatRepo;

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
@RequestMapping("/chatting")
public class ChatController {
	@Autowired
	ChatRepo chatRepo;
	@PostMapping("/")
	@ApiOperation(value = "나의 채팅방 등록")
	public ResponseEntity<Map> registmyChattingroom(@RequestBody ChatRoom chatroom) {
		ArrayList<ChatRoom> myChatList = chatRepo.findByEmail(chatroom.getEmail());
		boolean insert = true;
		for (ChatRoom chatRoom2 : myChatList) {
			if(chatRoom2.getChatNo().equals(chatroom.getChatNo()) && chatRoom2.getEmail().equals(chatroom.getEmail())) {
				insert = false;
				break;
			}
		}
		Map<String, ArrayList<ChatRoom>> map = new HashMap<String, ArrayList<ChatRoom>>();
		if(insert) {
			chatRepo.save(chatroom);
			return new ResponseEntity<Map>(map, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Map>(null, HttpStatus.NOT_ACCEPTABLE);
		}
	}
	@GetMapping("/search/{email}")
	@ApiOperation(value = "나의 채팅방 리스트조회")
	public ResponseEntity<Map> searchmyChattingroom(@PathVariable String email) {
		Map<String, ArrayList<ChatRoom>> map = new HashMap<String, ArrayList<ChatRoom>>();
		ArrayList<ChatRoom> chatlist = new ArrayList<ChatRoom>();
		chatlist = chatRepo.findByEmail(email);
		map.put("list", chatlist);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
}
