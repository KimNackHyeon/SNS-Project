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
@Table(name = "GroupBuying")
public class GroupBuying {
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String nickname;
	
	@NotEmpty
	private String title;
	
	@NotEmpty
	private String food;
	
	@NotEmpty
	private String food_kor;
	
	@NotEmpty
	private String address;
	
	@NotEmpty
	private String end_date;
	
	private Long max_people;
	
	private Long now_people;
	
	@NotEmpty
	private String link;
	
	private String content;
	
	@Column(name = "regist_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDateTime regist_date;

	public GroupBuying() {
		super();
	}
	public GroupBuying(Long no, @NotEmpty String email, @NotEmpty String nickname, @NotEmpty String title,
			@NotEmpty String food, @NotEmpty String food_kor, @NotEmpty String address, @NotEmpty String end_date,
			@NotEmpty Long max_people, @NotEmpty Long now_people, @NotEmpty String link, String content,
			LocalDateTime regist_date) {
		super();
		this.no = no;
		this.email = email;
		this.nickname = nickname;
		this.title = title;
		this.food = food;
		this.food_kor = food_kor;
		this.address = address;
		this.end_date = end_date;
		this.max_people = max_people;
		this.now_people = now_people;
		this.link = link;
		this.content = content;
		this.regist_date = regist_date;
	}
	

	@Override
	public String toString() {
		return "GroupBuying [no=" + no + ", email=" + email + ", nickname=" + nickname + ", title=" + title + ", food="
				+ food + ", food_kor=" + food_kor + ", address=" + address + ", end_date=" + end_date + ", max_people="
				+ max_people + ", now_people=" + now_people + ", link=" + link + ", content=" + content
				+ ", regist_date=" + regist_date + "]";
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getFood_kor() {
		return food_kor;
	}

	public void setFood_kor(String food_kor) {
		this.food_kor = food_kor;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Long getMax_people() {
		return max_people;
	}

	public void setMax_people(Long max_people) {
		this.max_people = max_people;
	}

	public Long getNow_people() {
		return now_people;
	}

	public void setNow_people(Long now_people) {
		this.now_people = now_people;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getRegist_date() {
		return regist_date;
	}

	public void setRegist_date(LocalDateTime regist_date) {
		this.regist_date = regist_date;
	}
	
	
}
