package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "GroupMember")
public class GroupMember {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;

	private String participantEmail;

	private String participantNickname;

	private String groupNo;

	public GroupMember(Long no, String participantEmail, String participantNickname, String groupNo) {
		super();
		this.no = no;
		this.participantEmail = participantEmail;
		this.participantNickname = participantNickname;
		this.groupNo = groupNo;
	}

	public GroupMember() {
		super();
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getParticipantEmail() {
		return participantEmail;
	}

	public void setParticipantEmail(String participantEmail) {
		this.participantEmail = participantEmail;
	}

	public String getParticipantNickname() {
		return participantNickname;
	}

	public void setParticipantNickname(String participantNickname) {
		this.participantNickname = participantNickname;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}

	@Override
	public String toString() {
		return "GroupMember [no=" + no + ", participantEmail=" + participantEmail + ", participantNickname="
				+ participantNickname + ", groupNo=" + groupNo + "]";
	}

	

}
