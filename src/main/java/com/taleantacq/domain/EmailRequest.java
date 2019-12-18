package com.taleantacq.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EmailRequest {

	@JsonProperty(value = "emailIds")
	public String emailIDs;
	
	@JsonProperty(value = "emailBody")
	public String emailBody;
	
	@JsonProperty(value = "emailSubject")
	public String emailSubject;
	
	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailIDs() {
		return emailIDs;
	}

	public void setEmailIDs(String emailIDs) {
		this.emailIDs = emailIDs;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

}
