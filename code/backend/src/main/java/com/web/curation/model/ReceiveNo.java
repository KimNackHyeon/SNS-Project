package com.web.curation.model;

import org.springframework.stereotype.Component;

@Component
public class ReceiveNo {
	private String no;

	public ReceiveNo(String no) {
		super();
		this.no = no;
	}

	public ReceiveNo() {
		super();
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "ReceiveNo [no=" + no + "]";
	}
	
}
