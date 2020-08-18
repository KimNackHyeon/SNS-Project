package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name="MyChat")
public class MyChat {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	private String chatKey;
	
	private String chatName;
	
	private String myEmail;
	
	private String myNickname;
	
	private String otherEmail;
	
	private String otherNickname;
	
	private String type;
	
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public MyChat() {
		super();
	}

	public MyChat(String chatKey, String chatName, String myEmail, String myNickname, String otherEmail,
			String otherNickname, String type, String image) {
		super();
		this.chatKey = chatKey;
		this.chatName = chatName;
		this.myEmail = myEmail;
		this.myNickname = myNickname;
		this.otherEmail = otherEmail;
		this.otherNickname = otherNickname;
		this.type = type;
		this.image = image;
	}

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getChatKey() {
		return chatKey;
	}

	public void setChatKey(String chatKey) {
		this.chatKey = chatKey;
	}

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}

	public String getMyEmail() {
		return myEmail;
	}

	public void setMyEmail(String myEmail) {
		this.myEmail = myEmail;
	}

	public String getMyNickname() {
		return myNickname;
	}

	public void setMyNickname(String myNickname) {
		this.myNickname = myNickname;
	}

	public String getOtherEmail() {
		return otherEmail;
	}

	public void setOtherEmail(String otherEmail) {
		this.otherEmail = otherEmail;
	}

	public String getOtherNickname() {
		return otherNickname;
	}

	public void setOtherNickname(String otherNickname) {
		this.otherNickname = otherNickname;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MyChat [no=" + no + ", chatKey=" + chatKey + ", chatName=" + chatName + ", myEmail=" + myEmail
				+ ", myNickname=" + myNickname + ", otherEmail=" + otherEmail + ", otherNickname=" + otherNickname
				+ ", type=" + type + ", image="+image+"]";
	}
	
}
