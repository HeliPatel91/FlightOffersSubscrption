package com.fonix.FonixFlightOffers.model;

/**
 * This is the model class of
 * Offers which will be used by save API for crawler
 */
public class OfferDetail {
	
	private String origin;
	
	private int flightNumber;
	
	private String destination;
	
	private long price;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}
}
