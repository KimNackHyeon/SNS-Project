package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "LikeFeed")
public class LikeFeed {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	private String email; // 좋아요한 회원 이메일

	private Long feedNo; // 좋아요한 글 번호

	public LikeFeed() {
		super();
	}

	public LikeFeed(String email, Long feedNo) {
		super();
		this.email = email;
		this.feedNo = feedNo;
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

	public Long getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(Long feedNo) {
		this.feedNo = feedNo;
	}

	@Override
	public String toString() {
		return "Like [no=" + no + ", email=" + email + ", feedNo=" + feedNo + "]";
	}

}
