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
	private String content; // 글내용

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Override
	public String toString() {
		return "MyBoard [no=" + no + ", nickname=" + nickname + ", title=" + title + ", email=" + email + ", content="
				+ content + ", likecount=" + likecount + ", create_date=" + create_date + "]";
	}

}
