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
	private String price;
	
	@NotEmpty
	private String tradefood1;
	
	private String tradefood2;
	
	@NotEmpty
	private String foodcount1;
	
	private String foodcount2;
	
	@NotEmpty
	private String content;
	
	@NotEmpty
	private String address;
	
	private String imgUrl;
	
	@Column(name = "regist_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDateTime regist_date;

	public Trade() {
	}
	
	@Override
	public String toString() {
		return "Trade [no=" + no + ", email=" + email + ", nickname=" + nickname + ", myfood=" + myfood + ", price="
				+ price + ", tradefood1=" + tradefood1 + ", tradefood2=" + tradefood2 + ", foodcount1=" + foodcount1
				+ ", foodcount2=" + foodcount2 + ", content=" + content + ", address=" + address + ", imgUrl=" + imgUrl
				+ ", regist_date=" + regist_date + "]";
	}

	public Trade(@NotEmpty String email, @NotEmpty String nickname, @NotEmpty String myfood,
			@NotEmpty String price, @NotEmpty String tradefood1, String tradefood2, @NotEmpty String foodcount1,
			String foodcount2, @NotEmpty String content, @NotEmpty String address, String imgUrl) {
		super();
		this.no = no;
		this.email = email;
		this.nickname = nickname;
		this.myfood = myfood;
		this.price = price;
		this.tradefood1 = tradefood1;
		this.tradefood2 = tradefood2;
		this.foodcount1 = foodcount1;
		this.foodcount2 = foodcount2;
		this.content = content;
		this.address = address;
		this.imgUrl = imgUrl;
		this.regist_date = regist_date;
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

	public String getFoodcount1() {
		return foodcount1;
	}

	public void setFoodcount1(String foodcount1) {
		this.foodcount1 = foodcount1;
	}

	public String getFoodcount2() {
		return foodcount2;
	}

	public void setFoodcount2(String foodcount2) {
		this.foodcount2 = foodcount2;
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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public LocalDateTime getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(LocalDateTime regist_date) {
		this.regist_date = regist_date;
	}
	
	
	
}
