package com.web.curation.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.web.curation.model.GroupBuying;
import com.web.curation.model.GroupMember;
import com.web.curation.model.Member;
import com.web.curation.repo.GroupBuyingRepo;
import com.web.curation.repo.GroupMemberRepo;
import com.web.curation.repo.MemberRepo;

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
public class GroupbuyingController {

	@Autowired
	GroupBuyingRepo groupbuyingrepo;
	@Autowired
	GroupMemberRepo groupmemberrepo;
	
	@Autowired
	MemberRepo memberrepo;

	@PostMapping("/groupbuying/create")
	@ApiOperation(value = "공동구매글 작성하기")
	public ResponseEntity<String> createGroupBuying(@RequestBody GroupBuying groupbuying) {
		groupbuyingrepo.save(groupbuying);
		return new ResponseEntity<String>("등록성공", HttpStatus.OK);
	}

	@GetMapping("/groupbuying/read")
	@ApiOperation(value = "공동구매글 조회하기")
	public ResponseEntity<ArrayList<GroupBuying>> readGroupBuying() {
		ArrayList list = new ArrayList<GroupBuying>();
		list = (ArrayList) groupbuyingrepo.findAll();
		return new ResponseEntity<ArrayList<GroupBuying>>(list, HttpStatus.OK);
	}

	@GetMapping("/groupbuying/readdetail/{no}")
	@ApiOperation(value = "공동구매글 상세조회하기")
	public ResponseEntity<GroupBuying> readDetail(@PathVariable Long no) {
		GroupBuying gb = null;
		gb = groupbuyingrepo.findByNo(no);

		return new ResponseEntity<GroupBuying>(gb, HttpStatus.OK);
	}

	@PostMapping("/groupbuying/participate")
	@ApiOperation(value = "참가하기")
	public ResponseEntity<Long> participate(@RequestBody GroupMember groupmember) {
		System.out.println(groupmember);
		groupmemberrepo.save(groupmember);
		groupbuyingrepo.updateMembercount(Long.parseLong(groupmember.getGroupNo()));
		GroupBuying gb = groupbuyingrepo.findByNo(Long.parseLong(groupmember.getGroupNo()));
		return new ResponseEntity<Long>(gb.getNow_people(), HttpStatus.OK);
	}
	
	@PostMapping("/groupbuying/participatelist")
	@ApiOperation(value="참가자 명단")
	public ResponseEntity<ArrayList<Member>> searchList(@RequestBody GroupMember groupmember){
		ArrayList<GroupMember> gmlist = groupmemberrepo.findByGroupNo(groupmember.getGroupNo());
		ArrayList<Member> memberlist = new ArrayList<Member>();
		for (GroupMember member : gmlist) {
			memberlist.add(memberrepo.getUserByEmail(member.getParticipantEmail()));
		}
		return new ResponseEntity<ArrayList<Member>>(memberlist, HttpStatus.OK);
	}

}