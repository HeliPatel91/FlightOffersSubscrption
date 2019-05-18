package com.fonix.FonixFlightOffers.model;

/**
 * This is the model class of
 * response which will be returned in
 * the API call
 */
public class OfferResponse {
	
	private OfferDetail offerDetail;

	public OfferDetail getOfferDetail() {
		return offerDetail;
	}

	public void setOfferDetail(OfferDetail offerDetail) {
		this.offerDetail = offerDetail;
	}

}
