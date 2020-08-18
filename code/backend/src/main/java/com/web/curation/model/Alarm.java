package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "Alarm")
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	private String email; // 알람을 받을 이메일

	private String content; // 알람 내용

	private String type; // 알람 타입 ( 1 : 팔로우 , 2 : 게시글 댓글, 3 : 게시글 좋아요, 4: 신선도 평가 )

	private String image; // 알람을 보낸 사람의 프로필 사진

	private Long confirm; // 알람을 읽었는지 체크 ( 0 : 확인 x , 1 : 확인 o )

	private Long feedNo; // 연결되는 피드번호

	private String semail; // 연결되는 사용자 계정

	public Alarm() {
		super();
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getConfirm() {
		return confirm;
	}

	public void setConfirm(Long confirm) {
		this.confirm = confirm;
	}

	public Long getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(Long feedNo) {
		this.feedNo = feedNo;
	}

	public String getSemail() {
		return semail;
	}

	public void setSemail(String semail) {
		this.semail = semail;
	}

	@Override
	public String toString() {
		return "Alarm [no=" + no + ", email=" + email + ", content=" + content + ", type=" + type + ", image=" + image
				+ ", confirm=" + confirm + ", feedNo=" + feedNo + ", semail=" + semail + "]";
	}

}
