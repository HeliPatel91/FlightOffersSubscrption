package com.fonix.FonixFlightOffers.model;

/**
 * This is the model class of
 * Offers which will be used by save API for crawler
 */
public class OfferDetail {
	
	private String origin;
	
	private String flightNumber;
	
	private String destination;
	
	private long price;
	
	private String date;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin.toUpperCase();
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination.toUpperCase();
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
