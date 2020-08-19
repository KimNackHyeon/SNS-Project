// 하단 DB 설정 부분은 Sub PJT II에서 데이터베이스를 구성한 이후에 주석을 해제하여 사용.

package com.web.curation.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	private String nickname;

	private String address;

	@Column
	@ColumnDefault("/img/profile_default.png")
	private String image;

	@Column(name = "create_date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreationTimestamp
	private LocalDateTime create_date;

	private Long evalCount;

	private Long evalPoint;

	public Member() {
	};

	public Member(@NotEmpty String email, @NotEmpty String password, String nickname, String address) {
		super();
		this.email = email;
		this.password = password;
		this.nickname = nickname;
		this.address = address;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDateTime getCreate_date() {
		return create_date;
	}

	public void setCreate_date(LocalDateTime create_date) {
		this.create_date = create_date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getEvalCount() {
		return evalCount;
	}

	public void setEvalCount(Long evalCount) {
		this.evalCount = evalCount;
	}

	public Long getEvalPoint() {
		return evalPoint;
	}

	public void setEvalPoint(Long evalPoint) {
		this.evalPoint = evalPoint;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", email=" + email + ", password=" + password + ", nickname=" + nickname
				+ ", address=" + address + ", image=" + image + ", create_date=" + create_date + ", evalCount="
				+ evalCount + ", evalPoint=" + evalPoint + "]";
	}

}
