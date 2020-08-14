package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="ChatRoom")
public class ChatRoom {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long no;
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String chatTitle;
	
	@NotEmpty
	private String chatNo;
	
	@NotEmpty
	private String nickname;

	public ChatRoom() {
	}

	public ChatRoom(Long no, @NotEmpty String email, @NotEmpty String chatTitle, @NotEmpty String chatNo,
			@NotEmpty String nickname) {
		super();
		this.no = no;
		this.email = email;
		this.chatTitle = chatTitle;
		this.chatNo = chatNo;
		this.nickname = nickname;
	}
	


	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public String getChatTitle() {
		return chatTitle;
	}

	public void setChatTitle(String chatTitle) {
		this.chatTitle = chatTitle;
	}

	public String getChatNo() {
		return chatNo;
	}

	public void setChatNo(String chatNo) {
		this.chatNo = chatNo;
	}

	@Override
	public String toString() {
		return "ChatRoom [no=" + no + ", email=" + email + ", chatTitle=" + chatTitle + ", chatNo=" + chatNo
				+ ", nickname=" + nickname + "]";
	}
	
}
