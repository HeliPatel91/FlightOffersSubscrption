package com.fonix.FonixFlightOffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fonix.FonixFlightOffers.model.OfferDetail;
import com.fonix.FonixFlightOffers.model.OfferResponse;
import com.fonix.FonixFlightOffers.service.OfferService;


/**
 * This is the controller used by crawler for get and save operation
 */
@RestController
public class OfferController {

	@Autowired
	private OfferService offerService;
	
	@RequestMapping(value = "/offers/get", method= RequestMethod.GET,produces = { "application/json"})
	public OfferResponse getAllBestOffers() throws Exception
	{
		return null;
	}
	
	@RequestMapping(value = "/offers/save", method= RequestMethod.POST,consumes = { "application/json"})
	public void getEmployeeSalary(@RequestBody OfferDetail offerDetail)
	{
		
	}
	
}
