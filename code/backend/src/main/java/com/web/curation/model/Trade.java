package com.web.curation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "Trade")
public class Trade {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String nickname;
	
	@NotEmpty
	private String myfood;
	
	@NotEmpty
	private String myfood_kor;
	
	@NotEmpty
	private String price;
	
	@NotEmpty
	private String tradefood1;
	
	private String tradefood2;
	
	@NotEmpty
	private String tradefood1_kor;
	
	private String tradefood2_kor;
	
	@NotEmpty
	private String myfoodcount1;
	@NotEmpty
	private String tradefoodcount1;
	
	
	private String myfoodcount2;
	
	private String tradefoodcount2;
	
	@NotEmpty
	private String content;
	
	@NotEmpty
	private String address;
	
	@Column(name = "regist_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDateTime regist_date;
	
	
	public Trade() {
		super();
	}

	


	public Trade(Long no, @NotEmpty String email, @NotEmpty String nickname, @NotEmpty String myfood,
			@NotEmpty String myfood_kor, @NotEmpty String price, @NotEmpty String tradefood1, String tradefood2,
			@NotEmpty String tradefood1_kor, String tradefood2_kor, @NotEmpty String myfoodcount1,
			@NotEmpty String tradefoodcount1, @NotEmpty String myfoodcount2, @NotEmpty String tradefoodcount2,
			@NotEmpty String content, @NotEmpty String address, LocalDateTime regist_date) {
		super();
		this.no = no;
		this.email = email;
		this.nickname = nickname;
		this.myfood = myfood;
		this.myfood_kor = myfood_kor;
		this.price = price;
		this.tradefood1 = tradefood1;
		this.tradefood2 = tradefood2;
		this.tradefood1_kor = tradefood1_kor;
		this.tradefood2_kor = tradefood2_kor;
		this.myfoodcount1 = myfoodcount1;
		this.tradefoodcount1 = tradefoodcount1;
		this.myfoodcount2 = myfoodcount2;
		this.tradefoodcount2 = tradefoodcount2;
		this.content = content;
		this.address = address;
		this.regist_date = regist_date;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getMyfood() {
		return myfood;
	}

	public void setMyfood(String myfood) {
		this.myfood = myfood;
	}

	public String getMyfood_kor() {
		return myfood_kor;
	}

	public void setMyfood_kor(String myfood_kor) {
		this.myfood_kor = myfood_kor;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getTradefood1() {
		return tradefood1;
	}

	public void setTradefood1(String tradefood1) {
		this.tradefood1 = tradefood1;
	}

	public String getTradefood2() {
		return tradefood2;
	}

	public void setTradefood2(String tradefood2) {
		this.tradefood2 = tradefood2;
	}

	public String getTradefood1_kor() {
		return tradefood1_kor;
	}

	public void setTradefood1_kor(String tradefood1_kor) {
		this.tradefood1_kor = tradefood1_kor;
	}

	public String getTradefood2_kor() {
		return tradefood2_kor;
	}

	public void setTradefood2_kor(String tradefood2_kor) {
		this.tradefood2_kor = tradefood2_kor;
	}

	public String getMyfoodcount1() {
		return myfoodcount1;
	}

	public void setMyfoodcount1(String myfoodcount1) {
		this.myfoodcount1 = myfoodcount1;
	}

	public String getTradefoodcount1() {
		return tradefoodcount1;
	}

	public void setTradefoodcount1(String tradefoodcount1) {
		this.tradefoodcount1 = tradefoodcount1;
	}

	public String getMyfoodcount2() {
		return myfoodcount2;
	}

	public void setMyfoodcount2(String myfoodcount2) {
		this.myfoodcount2 = myfoodcount2;
	}

	public String getTradefoodcount2() {
		return tradefoodcount2;
	}

	public void setTradefoodcount2(String tradefoodcount2) {
		this.tradefoodcount2 = tradefoodcount2;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(LocalDateTime regist_date) {
		this.regist_date = regist_date;
	}

	@Override
	public String toString() {
		return "Trade [no=" + no + ", email=" + email + ", nickname=" + nickname + ", myfood=" + myfood
				+ ", myfood_kor=" + myfood_kor + ", price=" + price + ", tradefood1=" + tradefood1 + ", tradefood2="
				+ tradefood2 + ", tradefood1_kor=" + tradefood1_kor + ", tradefood2_kor=" + tradefood2_kor
				+ ", myfoodcount1=" + myfoodcount1 + ", tradefoodcount1=" + tradefoodcount1 + ", myfoodcount2="
				+ myfoodcount2 + ", tradefoodcount2=" + tradefoodcount2 + ", content=" + content + ", address="
				+ address + ", regist_date=" + regist_date + "]";
	}
	

}
