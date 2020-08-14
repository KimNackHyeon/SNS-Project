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
@Table(name = "Tag")
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	private Long feedNo;

	private String tagName;

	public Tag() {
	}

	public Tag(Long feedNo, String tagName) {
		this.tagName = tagName;
		this.feedNo = feedNo;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Long getFeedNo() {
		return feedNo;
	}

	public void setFeedNo(Long feedNo) {
		this.feedNo = feedNo;
	}

	@Override
	public String toString() {
		return "Tag [no=" + no + ", feedNo=" + feedNo + ", tagName=" + tagName + "]";
	}

}
