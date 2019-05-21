package com.fonix.FonixFlightOffers.model;

public class EmailContent {

	private String confirmationUrl;
	
	private String body;
	
	private String subject;
	
	private String toEmailAddress;

	public EmailContent(String confirmationUrl, String body, String subject, String toEmailAddress) {
		super();
		this.confirmationUrl = confirmationUrl;
		this.body = body;
		this.subject = subject;
		this.toEmailAddress = toEmailAddress;
	}

	public String getConfirmationUrl() {
		return confirmationUrl;
	}

	public void setConfirmationUrl(String confirmationUrl) {
		this.confirmationUrl = confirmationUrl;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

}
