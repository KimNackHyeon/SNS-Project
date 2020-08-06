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
@Table(name = "Follow")
public class Follow {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotEmpty
	private String email;

	@NotEmpty
	private String yourEmail;

	public Follow() {
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

	public String getYourEmail() {
		return yourEmail;
	}

	public void setYourEmail(String yourEmail) {
		this.yourEmail = yourEmail;
	}

	@Override
	public String toString() {
		return "Follow [no=" + no + ", email=" + email + ", yourEmail=" + yourEmail + "]";
	}

}
