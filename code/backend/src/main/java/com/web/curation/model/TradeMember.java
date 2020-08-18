package com.web.curation.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "TradeMember")
public class TradeMember {
   @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long no;
   
   private String tradeNo;
   
   private String participantEmail;
   
   private String participantNickname;

   public TradeMember(Long no, String tradeNo, String participantEmail, String participantNickname) {
      super();
      this.no = no;
      this.tradeNo = tradeNo;
      this.participantEmail = participantEmail;
      this.participantNickname = participantNickname;
   }

   public TradeMember() {
      super();
   }

   public Long getNo() {
      return no;
   }

   public void setNo(Long no) {
      this.no = no;
   }

   public String getTradeNo() {
      return tradeNo;
   }

   public void setTradeNo(String tradeNo) {
      this.tradeNo = tradeNo;
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

   @Override
   public String toString() {
      return "TradeMember [no=" + no + ", tradeNo=" + tradeNo + ", participantEmail=" + participantEmail
            + ", participantNickname=" + participantNickname + "]";
   }
   
   
}