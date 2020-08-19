package com.web.curation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.web.curation.model.GenerateCertCharacter;

@Service
public class MemberService {

	@Autowired
	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "skrgus0226@gmail.com";
	private GenerateCertCharacter GCC = new GenerateCertCharacter();

	public void sendMail(String pwd, String address) {

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(address);
		message.setFrom(FROM_ADDRESS);
		message.setSubject("비밀번호찾기입니다.");
		message.setText("비밀번호는 " + pwd + "입니다.");
		System.out.println(pwd + "," + address);
		mailSender.send(message);

	}

	public String sendMail(String address) {

		SimpleMailMessage message = new SimpleMailMessage();
		String certificateNumber = GCC.excuteGenerate();
		message.setTo(address);
		message.setFrom(FROM_ADDRESS);
		message.setSubject("회원가입 인증번호입니다.");
		message.setText("인증번호는    " + certificateNumber + "    입니다.");
		mailSender.send(message);
		return certificateNumber;
	}
	public void qnaMail(String sendNickname, String title, String content) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("rjsgh1232@naver.com");
		message.setFrom(FROM_ADDRESS);
		message.setSubject(sendNickname+"님이 문의메일을 보냈습니다.");
		message.setText(content);
		mailSender.send(message);
	}
	public String sendMailforpassword(String address) {
		
		SimpleMailMessage message = new SimpleMailMessage();
		String certificateNumber = GCC.excuteGenerate();
		message.setTo(address);
		message.setFrom(FROM_ADDRESS);
		message.setSubject("비밀번호 찾기 인증번호입니다.");
		message.setText("인증번호는    " + certificateNumber + "    입니다.");
		mailSender.send(message);
		return certificateNumber;
	}

}
