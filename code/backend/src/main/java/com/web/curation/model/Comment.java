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
@Table(name = "comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no; // 댓글번호

	private Long feedNo; // 글번호

	@NotEmpty
	private String nickname; // 작성자


	private String email; // 작성자 이메일

	@NotEmpty
	private String comment; // 댓글 내용

	private String img; // 이미지 경로

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

	public Long getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(Long feedNo) {
		this.feedNo = feedNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Override
	public String toString() {
		return "Comment [no=" + no + ", feedNo=" + feedNo + ", nickname=" + nickname + ", email=" + email + ", comment="
				+ comment + ", img=" + img + ", create_date=" + create_date + "]";
	}

	

}
