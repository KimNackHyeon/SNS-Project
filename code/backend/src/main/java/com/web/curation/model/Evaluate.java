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
@Table(name = "Evaluate")
public class Evaluate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotEmpty
	private String email; // 평가할 이메일

	@NotEmpty
	private String nickname; // 평가할 닉네임

	public Evaluate() {
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

	@Override
	public String toString() {
		return "Evaluate [no=" + no + ", email=" + email + ", nickname=" + nickname + "]";
	}

}
