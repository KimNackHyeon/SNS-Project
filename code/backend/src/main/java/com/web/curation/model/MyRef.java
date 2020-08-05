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
	private String food;

	public MyRef() {
	}

	public MyRef(Long no, @NotEmpty String email, @NotEmpty String food) {
		super();
		this.no = no;
		this.email = email;
		this.food = food;
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

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	@Override
	public String toString() {
		return "MyRef [no=" + no + ", email=" + email + ", food=" + food + "]";
	}
	
}
