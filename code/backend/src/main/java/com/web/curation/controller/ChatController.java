package com.web.curation.controller;

import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.Member;
import com.web.curation.model.MyChat;
import com.web.curation.repo.DirectChatRepo;
import com.web.curation.repo.MemberRepo;
import com.web.curation.repo.MyChatRepo;

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
@RequestMapping("/api")
public class ChatController {
	@Autowired
	MyChatRepo mychatRepo;
	
	@Autowired
	DirectChatRepo directchatRepo;
	
	@Autowired
	MemberRepo memberRepo;
	
	@PostMapping("/chatting")
	@ApiOperation(value = "나의 채팅방 등록")
	public ResponseEntity<String> registmyChattingroom(@RequestBody MyChat chatroom) {
		if(chatroom.getType().equals("1")) { // 우리동네 장터
			ArrayList<MyChat> myChatList = mychatRepo.findByMyEmail(chatroom.getMyEmail());
			
			boolean insert = true;
			for (MyChat chatRoom2 : myChatList) {
				if(chatRoom2.getChatKey().equals(chatroom.getChatKey())) {
					insert = false;
					break;
				}
			}
			boolean insert2 = true;
			ArrayList<MyChat> myChatList2 = mychatRepo.findByMyEmail(chatroom.getOtherEmail());
			for (MyChat chatRoom2 : myChatList2) {
				if(chatRoom2.getChatKey().equals(chatroom.getChatKey())) {
					insert2 = false;
					break;
				}
			}
			if(insert) {
				mychatRepo.save(chatroom);
			}
			if(insert2) {
				MyChat chatroom2 = new MyChat(chatroom.getChatKey(), chatroom.getChatName(), chatroom.getOtherEmail(), chatroom.getOtherNickname(), chatroom.getMyEmail(), chatroom.getMyNickname(), "1", "");
				mychatRepo.save(chatroom2);
			}
			return new ResponseEntity<>(chatroom.getChatName(), HttpStatus.OK);
		}else {
			int compareNum = chatroom.getOtherNickname().compareTo(chatroom.getMyNickname());
			String chatKey = null;
			ArrayList<Optional<MyChat>> opt;
			if(compareNum < 0) { // 만약 수신자의 닉네임이 사전상 앞에 위치한다면
				chatKey = chatroom.getOtherNickname()+chatroom.getMyNickname();
			}else if(compareNum > 0) { // 만약 수신자의 닉네임이 사전상 뒤에 위치한다면
				chatKey = chatroom.getMyNickname()+chatroom.getOtherNickname();
			}else { // 두 닉네임이 같다면
				return new ResponseEntity<String>("불가능", HttpStatus.OK);
			}
			opt = mychatRepo.findByChatKey(chatKey);
			if(opt.isEmpty()) {
				chatroom.setChatKey(chatKey);
				chatroom.setChatName(chatroom.getOtherNickname()+"님과의 채팅");
				Member member = memberRepo.getUserByEmail(chatroom.getOtherEmail());
				chatroom.setImage(member.getImage());
				mychatRepo.save(chatroom);
				MyChat chatroom2 = new MyChat(chatroom.getChatKey(), chatroom.getMyNickname()+"님과의 채팅", chatroom.getOtherEmail(), chatroom.getOtherNickname(), chatroom.getMyEmail(), chatroom.getMyNickname(), "2", "");
				chatroom2.setImage((memberRepo.getUserByEmail(chatroom2.getOtherEmail())).getImage());
				mychatRepo.save(chatroom2);
			}
			return new ResponseEntity<String>(chatKey, HttpStatus.OK);
		}
	}
	
	@GetMapping("/chatting/{email}")
	@ApiOperation(value = "나의 채팅방 리스트조회")
	public ResponseEntity<Map> searchmyChattingroom(@PathVariable String email) {
		System.out.println(email);
		Map<String, ArrayList<MyChat>> map = new HashMap<String, ArrayList<MyChat>>();
		ArrayList<MyChat> chatlist = new ArrayList<MyChat>();
		chatlist = mychatRepo.findByMyEmail(email);
		map.put("list", chatlist);
		return new ResponseEntity<Map>(map, HttpStatus.OK);
	}
	@GetMapping("/chattingrefresh/{no}/{email}")
	   @ApiOperation(value = "나의 채팅방 새로고침")
	   public ResponseEntity<MyChat> researchmyChattingroom(@PathVariable String no, @PathVariable String email) {
	      System.out.println(no+" "+email);
	      MyChat chat = mychatRepo.findByChatKeyAndMyEmail(no, email);
	      return new ResponseEntity<MyChat>(chat, HttpStatus.OK);
	   }
	
}
