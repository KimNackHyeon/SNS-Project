package com.web.curation.controller;

import static org.junit.Assert.assertEquals;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Optional;

import javax.validation.Valid;

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
import com.web.curation.model.SignupRequest;
import com.web.curation.model.Member;
import com.web.curation.repo.MemberRepo;
import com.web.curation.service.MemberService;

import io.fusionauth.jwt.Signer;
import io.fusionauth.jwt.Verifier;
import io.fusionauth.jwt.domain.JWT;
import io.fusionauth.jwt.hmac.HMACSigner;
import io.fusionauth.jwt.hmac.HMACVerifier;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//  http://localhost:9999/food/swagger-ui.html
@CrossOrigin(origins = { "http://localhost:8080" })
@RestController
@RequestMapping("/api/")
public class AccountController {

	@Autowired
	MemberRepo memberRepo;

	@Autowired
	MemberService memberService;

//	@GetMapping("/account/login")
//	@ApiOperation(value = "로그인")
//	public Object login(@RequestParam(required = true) final String email,
//			@RequestParam(required = true) final String password) {
//
//		Optional<Member> userOpt = memberRepo.findUserByEmailAndPassword(email, password);
//
//		ResponseEntity response = null;
//
//		if (userOpt.isPresent()) {
//			final BasicResponse result = new BasicResponse();
//			System.out.println("로그인된 아이디 정보");
//			System.out.println(userOpt);
//			result.status = true;
//			result.data = "success";
//			response = new ResponseEntity<>(result, HttpStatus.OK);
//		} else {
//			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//
//		return response;
//	}

//	@RequestParam(required = false) String email, @RequestParam(required = false) String password
	
	@ApiOperation(value = "로그인 처리")
	@PostMapping("/account/login")
	public ResponseEntity<String> login(@RequestBody Member member) {
		System.out.println(member.getEmail() + " " + member.getPassword());
		Optional<Member> userOpt = memberRepo.findUserByEmailAndPassword(member.getEmail(), member.getPassword());

		if (userOpt.isPresent()) {
			System.out.println("로그인된 아이디 정보");
			System.out.println(userOpt.get());
			String token = getToken(userOpt.get());
			return new ResponseEntity<String>(token, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
		}
		
//		ResponseEntity response = null;
//
//		if (userOpt.isPresent()) {
//			final BasicResponse result = new BasicResponse();
//			System.out.println("로그인된 아이디 정보");
//			System.out.println(userOpt);
//			result.status = true;
//			result.data = "success";
//			response = new ResponseEntity<>(result, HttpStatus.OK);
//		} else {
//			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//		}
//
//		return response;

	}

	@PostMapping("/account/signup")
	@ApiOperation(value = "가입하기")
	public Object signup(@Valid @RequestBody SignupRequest request) {
		// 이메일, 닉네임 중복처리 필수
		// 회원가입단을 생성해 보세요.

		final BasicResponse result = new BasicResponse();
		result.status = true;
		result.data = "success";

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@PostMapping("/account/searchpwd")
	@ApiOperation(value = "비밀번호 찾기")
	public Object searchpwd(@RequestParam(required = true) final String email) {
		Optional<Member> userOpt = memberRepo.findByEmail(email);

		ResponseEntity response = null;

		if (userOpt.isPresent()) {
			final BasicResponse result = new BasicResponse();
			System.out.println("비밀번호 찾기 아이디 정보");
			System.out.println(userOpt);
			memberService.sendMail(userOpt.get().getPassword(), userOpt.get().getEmail());
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}
	
	static Signer signer = HMACSigner.newSHA256Signer("coldudong");

	public String getToken(Member member) {
		// Useremail로 토큰을 만든다.
		// plusMinutes 는 토큰을 등록하는 시간임 지금은 1분
		JWT jwt = new JWT().setIssuer(member.getEmail()).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject("hellossafy")
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(30));
		// Sign and encode the JWT to a JSON string representation
		String token = JWT.getEncoder().encode(jwt, signer);

		return token;
	}

	public String getToken(String data) {
		// Useremail로 토큰을 만든다.
		// plusMinutes 는 토큰을 등록하는 시간임 지금은 1분
		JWT jwt = new JWT().setIssuer(data).setIssuedAt(ZonedDateTime.now(ZoneOffset.UTC))
				.setSubject("hellossafy")
				.setExpiration(ZonedDateTime.now(ZoneOffset.UTC).plusMinutes(3));
		// Sign and encode the JWT to a JSON string representation
		String token = JWT.getEncoder().encode(jwt, signer);

		return token;
	}

	// 복호화 하는 방법 : 내이름 넣음
	// 토큰이 필요한 API 정보에 대해서 유효성을 체크해주면된다
	static Verifier verifier = HMACVerifier.newVerifier("coldudong");

	// Token이 유효하면 True 유효하지 않으면 False를 반환한다.
	// API를 받을때 유효한 토큰인지 함께 검사한다.
	public boolean cmpToekn(String token) {
		try {
			// Build an HMC verifier using the same secret that was used to sign the JWT
			JWT jwt = JWT.getDecoder().decode(token, verifier);
			assertEquals(jwt.subject, "hellossafy");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}