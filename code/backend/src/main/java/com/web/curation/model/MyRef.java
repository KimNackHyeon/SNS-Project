package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="MyRef")
public class MyRef {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	private String name_kor;
	
	@NotEmpty
	private String expire_date;
	
	private Long amount;
	
	private String img;

	public MyRef() {
	}


	public String getImg() {
		return img;
	}


	public void setImg(String img) {
		this.img = img;
	}


	public MyRef(Long no, @NotEmpty String email, @NotEmpty String name, @NotEmpty String name_kor,
			@NotEmpty String expire_date, Long amount, String img) {
		super();
		this.no = no;
		this.email = email;
		this.name = name;
		this.name_kor = name_kor;
		this.expire_date = expire_date;
		this.amount = amount;
		this.img = img;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName_kor() {
		return name_kor;
	}

	public void setName_kor(String name_kor) {
		this.name_kor = name_kor;
	}

	public String getExpire_date() {
		return expire_date;
	}

	public void setExpire_date(String expire_date) {
		this.expire_date = expire_date;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}


	@Override
	public String toString() {
		return "MyRef [no=" + no + ", email=" + email + ", name=" + name + ", name_kor=" + name_kor + ", expire_date="
				+ expire_date + ", amount=" + amount + ", img=" + img + "]";
	}
	
	
}
