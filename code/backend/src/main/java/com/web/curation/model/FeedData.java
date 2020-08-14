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
@Table(name = "FeedData")
public class FeedData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	private Long feedNo;

	private Long tindex;

	@NotEmpty
	private String img;

	@NotEmpty
	private String content;

	public FeedData(Long feedNo, Long tindex, String content, String img) {
		this.tindex = tindex;
		this.feedNo = feedNo;
		this.content = content;
		this.img = img;
	}
	
	public FeedData() {
		
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public Long getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(Long feedNo) {
		this.feedNo = feedNo;
	}

	public Long getTindex() {
		return tindex;
	}

	public void setTindex(Long tindex) {
		this.tindex = tindex;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "FeedData [no=" + no + ", feedNo=" + feedNo + ", tindex=" + tindex + ", img=" + img + ", content="
				+ content + "]";
	}

}
