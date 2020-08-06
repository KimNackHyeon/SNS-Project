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

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "MyBoard")
public class MyBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no; // 글번호

	@NotEmpty
	private String nickname; // 작성자 닉네임

	@NotEmpty
	private String title; // 레시피 제목

	@NotEmpty
	private String email; // 작성자 이메일

	@NotEmpty
	private String profile; // 작성자 프로필 사진

	private Long likecount; // 좋아요 수

	private String content1; // 내용 1

	private String img1; // 이미지 1

	private String content2; // 내용 2

	private String img2; // 이미지 2

	private String content3; // 내용 3

	private String img3; // 이미지 3

	private String content4; // 내용 4

	private String img4; // 이미지 4

	private String content5; // 내용 5

	private String img5; // 이미지 5

	private String content6; // 내용 6

	private String img6; // 이미지 6

	private String content7; // 내용 7

	private String img7; // 이미지 7

	private String content8; // 내용 8

	private String img8; // 이미지 8

	private String content9; // 내용 9

	private String img9; // 이미지 9

	private String content10; // 내용 10

	private String img10; // 이미지 10

	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDateTime create_date;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getLikecount() {
		return likecount;
	}

	public void setLikecount(Long likecount) {
		this.likecount = likecount;
	}

	public LocalDateTime getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@Override
	public String toString() {
		return "MyBoard [no=" + no + ", nickname=" + nickname + ", title=" + title + ", email=" + email + ", profile="
				+ profile + ", likecount=" + likecount + ", content1=" + content1 + ", img1=" + img1 + ", content2="
				+ content2 + ", img2=" + img2 + ", content3=" + content3 + ", img3=" + img3 + ", content4=" + content4
				+ ", img4=" + img4 + ", content5=" + content5 + ", img5=" + img5 + ", content6=" + content6 + ", img6="
				+ img6 + ", content7=" + content7 + ", img7=" + img7 + ", content8=" + content8 + ", img8=" + img8
				+ ", content9=" + content9 + ", img9=" + img9 + ", content10=" + content10 + ", img10=" + img10
				+ ", create_date=" + create_date + "]";
	}

}
