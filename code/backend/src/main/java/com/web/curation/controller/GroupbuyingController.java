package com.web.curation.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.springframework.web.bind.annotation.RestController;

import com.web.curation.model.BasicResponse;
import com.web.curation.model.GroupBuying;
import com.web.curation.model.GroupMember;
import com.web.curation.model.Member;
import com.web.curation.model.ReceiveNo;
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
		Optional<GroupMember> duplicateCheck = groupmemberrepo
				.findByGroupNoAndParticipantEmail(groupmember.getGroupNo(), groupmember.getParticipantEmail());
		if (!duplicateCheck.isPresent()) {
			groupmemberrepo.save(groupmember);
			groupbuyingrepo.updateMembercount(Long.parseLong(groupmember.getGroupNo()));
			GroupBuying gb = groupbuyingrepo.findByNo(Long.parseLong(groupmember.getGroupNo()));
			return new ResponseEntity<String>(gb.getNow_people() + "", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Fail", HttpStatus.OK);
		}
	}

	@PostMapping("/groupbuying/participatelist")
	@ApiOperation(value = "참가자 명단")
	public ResponseEntity<ArrayList<Member>> searchList(@RequestBody GroupMember groupmember) {
		ArrayList<GroupMember> gmlist = groupmemberrepo.findByGroupNo(groupmember.getGroupNo());
		ArrayList<Member> memberlist = new ArrayList<Member>();
		for (GroupMember member : gmlist) {
			memberlist.add(memberrepo.getUserByEmail(member.getParticipantEmail()));
		}
		return new ResponseEntity<ArrayList<Member>>(memberlist, HttpStatus.OK);
	}

	@PostMapping("/groupbuying/beforeupdate")
	@ApiOperation(value = "공동구매글 수정할 페이지 조회")
	public ResponseEntity<GroupBuying> beforeUpdate(@RequestBody ReceiveNo gb) {
		GroupBuying a = groupbuyingrepo.findByNo(Long.parseLong(gb.getNo()));
		return new ResponseEntity<GroupBuying>(a, HttpStatus.OK);
	}

	@PostMapping("/groupbuying/update")
	@ApiOperation(value = "공동구매글 수정할 페이지 조회")
	public ResponseEntity<String> Update(@RequestBody GroupBuying gb) {
		groupbuyingrepo.update(gb.getNo(), gb.getTitle(), gb.getFood(), gb.getFood_kor(), gb.getEnd_date(),
				gb.getMax_people(), gb.getContent(), gb.getLink());
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@PostMapping("/groupbuying/delete")
	@ApiOperation(value = "공동구매글 삭제")
	public ResponseEntity<String> Delete(@RequestBody ReceiveNo gb) {
		groupbuyingrepo.d(Long.parseLong(gb.getNo()));
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@GetMapping("/groupbuying/orderbyenddate")
	@ApiOperation(value = "공동구매글 마감순 정렬")
	public ResponseEntity<ArrayList<GroupBuying>> OrderbyEnddate() {
		System.out.println("hihi");
		ArrayList<GroupBuying> list = (ArrayList<GroupBuying>) groupbuyingrepo.findAll();
		list.sort(new Comparator<GroupBuying>() {
			@Override
			public int compare(GroupBuying o1, GroupBuying o2) {
				// TODO Auto-generated method stub
				return o1.getEnd_date().compareTo(o2.getEnd_date());
			}
		});
		return new ResponseEntity<ArrayList<GroupBuying>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/groupbuying/orderbyaddress/{mylat}/{mylng}")
	@ApiOperation(value = "공동구매글 거리순 정렬")
	public ResponseEntity<ArrayList<tmpAddress>> OrderbyAddress(@RequestBody List<GroupBuying> grouplist,
			@PathVariable String mylat, @PathVariable String mylng) throws Exception {
		ArrayList<tmpAddress> result = new ArrayList<tmpAddress>();
		System.out.println(mylat+" "+mylng);
		for (GroupBuying address : grouplist) {
			String distance = geoCoding(mylng+","+mylat, address.getAddress());
			tmpAddress tmp = new tmpAddress(address.getNo(),
					address.getEmail(),
					address.getNickname(),
					address.getTitle(),
					address.getFood(),
					address.getFood_kor(),
					address.getAddress(),
					address.getEnd_date(),
					address.getMax_people(),
					address.getNow_people(),
					address.getLink(),
					address.getContent(),
					address.getRegist_date(),
					Double.parseDouble(distance));
			result.add(tmp);
		}
		Collections.sort(result);
		return new ResponseEntity<ArrayList<tmpAddress>>(result, HttpStatus.OK);
	}
	
	public String geoCoding(String myAddress, String otherAddress) throws Exception {
		String clientId = "61rysofn73"; // 애플리케이션 클라이언트 아이디값"
		String clientSecret = "M8R2rrYZS0ay6DVKZrJNgAwndyVU90awpnonYJZk"; // 애플리케이션 클라이언트 시크릿값"

		String text = null;
		try {
			text = URLEncoder.encode(otherAddress, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패", e);
		}

		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + text
				+ "&coordinate="+ myAddress; // json 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-NCP-APIGW-API-KEY-ID", clientId);
		requestHeaders.put("X-NCP-APIGW-API-KEY", clientSecret);
		String responseBody = get(apiURL, requestHeaders);
		int beginIdx = responseBody.indexOf("x")-1;
		int endIdx = responseBody.indexOf("errorMessage")-4;
		String data = responseBody.substring(beginIdx, endIdx);
		data.replaceAll("\"", "");
		
        Map<String, String> MapData = new HashMap<String, String>();
        
        String parts[] = data.split(",");
        for(String part : parts){
            String empdata[] = part.split(":");
            
            String strId = empdata[0].trim();
            String strName = empdata[1].trim();
            
            //add to map
            MapData.put(strId, strName);
        }
        return MapData.get("\"distance\"");
	}
	
	private String get(String apiUrl, Map<String, String> requestHeaders) {
		HttpURLConnection con = connect(apiUrl);
		try {
			con.setRequestMethod("GET");
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				con.setRequestProperty(header.getKey(), header.getValue());
			}

			int responseCode = con.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
				return readBody(con.getInputStream());
			} else { // 에러 발생
				return readBody(con.getErrorStream());
			}
		} catch (IOException e) {
			throw new RuntimeException("API 요청과 응답 실패", e);
		} finally {
			con.disconnect();
		}
	}

	private HttpURLConnection connect(String apiUrl) {
		try {
			URL url = new URL(apiUrl);
			return (HttpURLConnection) url.openConnection();
		} catch (MalformedURLException e) {
			throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
		} catch (IOException e) {
			throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
		}
	}

	private String readBody(InputStream body) {
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
			StringBuilder responseBody = new StringBuilder();

			String line;
			while ((line = lineReader.readLine()) != null) {
				responseBody.append(line);
			}

			return responseBody.toString();
		} catch (IOException e) {
			throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
		}
	}

}