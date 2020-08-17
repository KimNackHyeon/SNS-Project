package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="DirectChat")
public class DirectChat {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	private String chatName;
	
	private String receiverNickname;
	
	private String senderNickname;
	
	private String ReceiverEmail;
	
	private String SenderEmail;

	public DirectChat() {
		super();
	}
	public DirectChat(Long no, String chatName, String receiverNickname, String senderNickname, String ReceiverEmail,
			String senderEmail) {
		super();
		this.no = no;
		this.chatName = chatName;
		this.receiverNickname = receiverNickname;
		this.senderNickname = senderNickname;
		this.ReceiverEmail = ReceiverEmail;
		SenderEmail = senderEmail;
	}
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getChatName() {
		return chatName;
	}
	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	public String getReceiverNickname() {
		return receiverNickname;
	}
	public void setReceiverNickname(String receiverNickname) {
		this.receiverNickname = receiverNickname;
	}
	public String getSenderNickname() {
		return senderNickname;
	}
	public void setSenderNickname(String senderNickname) {
		this.senderNickname = senderNickname;
	}
	public String getReceiverEmail() {
		return ReceiverEmail;
	}
	public void setReceiverEmail(String ReceiverEmail) {
		this.ReceiverEmail = ReceiverEmail;
	}
	public String getSenderEmail() {
		return SenderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.SenderEmail = senderEmail;
	}
	@Override
	public String toString() {
		return "DirectChat [no=" + no + ", chatName=" + chatName + ", receiverNickname=" + receiverNickname
				+ ", senderNickname=" + senderNickname + ", ReceiverEmail=" + ReceiverEmail + ", SenderEmail="
				+ SenderEmail + "]";
	}
	
	
	
}
