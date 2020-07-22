package com.web.curation.controller.account;

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

import com.web.curation.dao.user.UserDao;
import com.web.curation.model.BasicResponse;
import com.web.curation.model.user.SignupRequest;
import com.web.curation.model.user.User;
import com.web.curation.service.user.UserService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = BasicResponse.class),
		@ApiResponse(code = 403, message = "Forbidden", response = BasicResponse.class),
		@ApiResponse(code = 404, message = "Not Found", response = BasicResponse.class),
		@ApiResponse(code = 500, message = "Failure", response = BasicResponse.class) })

//  http://localhost:9999/food/swagger-ui.html
@CrossOrigin(origins = { "http://localhost:3000" })
@RestController
public class AccountController {

	@Autowired
	UserDao userDao;
	
	@Autowired
	UserService userService;

	@GetMapping("/account/login")
	@ApiOperation(value = "로그인")
	public Object login(@RequestParam(required = true) final String email,
			@RequestParam(required = true) final String password) {

		Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);

		ResponseEntity response = null;

		if (userOpt.isPresent()) {
			final BasicResponse result = new BasicResponse();
			System.out.println("로그인된 아이디 정보");
			System.out.println(userOpt);
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
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
		Optional<User> userOpt = userDao.findByEmail(email);

		ResponseEntity response = null;

		if (userOpt.isPresent()) {
			final BasicResponse result = new BasicResponse();
			System.out.println("비밀번호 찾기 아이디 정보");
			System.out.println(userOpt);
			userService.sendMail(userOpt.get().getPassword(), userOpt.get().getEmail());
			result.status = true;
			result.data = "success";
			response = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return response;
	}

}