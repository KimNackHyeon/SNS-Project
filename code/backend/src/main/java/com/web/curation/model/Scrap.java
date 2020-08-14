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
@Table(name = "Scrap")
public class Scrap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	private String email; // 스크랩한 회원 이메일

	private Long feedNo; // 스크랩한 글 번호
	
	public Scrap() {
		
	}

	public Scrap(String email, Long feedNo) {
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
		return "Scrap [no=" + no + ", email=" + email + ", feedNo=" + feedNo + "]";
	}

}
