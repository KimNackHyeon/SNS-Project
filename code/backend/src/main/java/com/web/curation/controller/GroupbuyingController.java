package com.web.curation.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.web.curation.model.GroupBuying;
import com.web.curation.model.GroupMember;
import com.web.curation.model.Member;
import com.web.curation.model.ReceiveDistance;
import com.web.curation.model.ReceiveNo;
import com.web.curation.model.Trade;
import com.web.curation.model.tmpAddress;
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
//		for (Object object : list) {
//			System.out.println(object.toString());
//		}
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
	public ResponseEntity<String> participate(@RequestBody GroupMember groupmember) {
		Optional<GroupMember> duplicateCheck = groupmemberrepo.findByGroupNoAndParticipantEmail(groupmember.getGroupNo(), groupmember.getParticipantEmail());
		if(!duplicateCheck.isPresent()) {
			groupmemberrepo.save(groupmember);
			groupbuyingrepo.updateMembercount(Long.parseLong(groupmember.getGroupNo()));
			GroupBuying gb = groupbuyingrepo.findByNo(Long.parseLong(groupmember.getGroupNo()));
			return new ResponseEntity<String>(gb.getNow_people()+"", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("Fail", HttpStatus.OK);
		}
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
	@PostMapping("/groupbuying/beforeupdate")
	@ApiOperation(value="공동구매글 수정할 페이지 조회")
	public ResponseEntity<GroupBuying> beforeUpdate(@RequestBody ReceiveNo gb){
		GroupBuying a = groupbuyingrepo.findByNo(Long.parseLong(gb.getNo()));
		return new ResponseEntity<GroupBuying>(a, HttpStatus.OK);
	}
	@PostMapping("/groupbuying/update")
	@ApiOperation(value="공동구매글 수정할 페이지 조회")
	public ResponseEntity<String> Update(@RequestBody GroupBuying gb){
		groupbuyingrepo.update(gb.getNo(), gb.getTitle(), gb.getFood(), gb.getFood_kor(), gb.getEnd_date(), gb.getMax_people(), gb.getContent(), gb.getLink());
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	@PostMapping("/groupbuying/delete")
	@ApiOperation(value="공동구매글 삭제")
	public ResponseEntity<String> Delete(@RequestBody ReceiveNo gb){
		groupbuyingrepo.d(Long.parseLong(gb.getNo()));
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
	@GetMapping("/groupbuying/orderbyenddate")
	@ApiOperation(value="공동구매글 마감순 정렬")
	public ResponseEntity<ArrayList<GroupBuying>> OrderbyEnddate(){
		System.out.println("hihi");
		ArrayList<GroupBuying> list  = (ArrayList<GroupBuying>) groupbuyingrepo.findAll();
		list.sort(new Comparator<GroupBuying>() {
			@Override
			public int compare(GroupBuying o1, GroupBuying o2) {
				// TODO Auto-generated method stub
				return o1.getEnd_date().compareTo(o2.getEnd_date());
			}
		});
		return new ResponseEntity<ArrayList<GroupBuying>>(list, HttpStatus.OK);
	}
	@PostMapping("/groupbuying/orderbyaddress")
	@ApiOperation(value="공동구매글 거리순 정렬")
	public ResponseEntity<ArrayList<tmpAddress>> OrderbyAddress(@RequestBody ArrayList<ReceiveDistance> grouplist){
		System.out.println(grouplist.toString());
		ArrayList<GroupBuying> list  = (ArrayList<GroupBuying>) groupbuyingrepo.findAll();
		ArrayList<tmpAddress> result = new ArrayList<tmpAddress>();
		for (int i = 0; i < grouplist.size(); i++) {
			GroupBuying gb = list.get(i);
			result.add(new tmpAddress(gb.getNo(), gb.getEmail(), gb.getNickname(), gb.getTitle(), 
					gb.getFood(), gb.getFood_kor(), gb.getAddress(), gb.getEnd_date(), gb.getMax_people(), 
					gb.getNow_people(), gb.getLink(), gb.getContent(), gb.getRegist_date(), grouplist.get(i).getDistance()));
		}
		Collections.sort(result);
		return new ResponseEntity<ArrayList<tmpAddress>>(result, HttpStatus.OK);
	}

}