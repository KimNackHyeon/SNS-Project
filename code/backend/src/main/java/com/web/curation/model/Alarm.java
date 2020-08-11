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
@Table(name="Alarm")
public class Alarm {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String content;
	
	@NotEmpty
	private String type;
	
	@NotEmpty
	private String nickname;
	
	@NotEmpty
	private Long confirm;

	public Alarm(Long no, @NotEmpty String email, @NotEmpty String content, @NotEmpty String type,
			@NotEmpty String nickname, @NotEmpty Long confirm) {
		super();
		this.no = no;
		this.email = email;
		this.content = content;
		this.type = type;
		this.nickname = nickname;
		this.confirm = confirm;
	}

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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Long getConfirm() {
		return confirm;
	}

	public void setConfirm(Long confirm) {
		this.confirm = confirm;
	}
	

	
	
}
