package com.fonix.FonixFlightOffers.model;

/*
 * This is the class to receive the data provided by users in UI
 */
public class UserSubscription {

	private String email;
	
	private String origin;
	
	private String destination;
	
	private String frequency;
	
	public UserSubscription(String email, String origin, String destination, String frequency) {
		super();
		this.email = email;
		this.origin = origin;
		this.destination = destination;
		this.frequency = frequency;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	
}
