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
				+ profile + ", likecount=" + likecount + ", create_date=" + create_date + "]";
	}

}
