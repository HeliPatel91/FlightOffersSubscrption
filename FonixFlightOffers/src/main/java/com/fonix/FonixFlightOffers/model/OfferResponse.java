package com.fonix.FonixFlightOffers.model;

import java.util.List;

/**
 * This is the model class of
 * response which will be returned in
 * the API call
 */
public class OfferResponse {
	
	private List<OfferDetail> offerDetails;

	public List<OfferDetail> getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(List<OfferDetail> offerDetails) {
		this.offerDetails = offerDetails;
	}

}
