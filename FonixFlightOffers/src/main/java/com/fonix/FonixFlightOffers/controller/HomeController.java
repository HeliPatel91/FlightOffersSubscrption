package com.fonix.FonixFlightOffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fonix.FonixFlightOffers.model.UserSubscription;
import com.fonix.FonixFlightOffers.service.OfferService;

/**
 * This is the home controller to load 
 * the index.html which shows the form to subscribe for flight offers
 */
@Controller
public class HomeController {

	@Autowired
	private OfferService offferService;
	
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home()
	{
		return "index.html";
	}
	
	/*
	 * This is the API to handle the subscribe request from web page
	 */
	@RequestMapping(value="/subscribe", method = RequestMethod.POST)
	public ResponseEntity<String> home(@RequestBody UserSubscription userSubscription)
	{
		System.out.println("inside subscribe method "+userSubscription.getEmail());
		return new ResponseEntity<String>("Successfully subscribed!", HttpStatus.OK);
	}
	
}
