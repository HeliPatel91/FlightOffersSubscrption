package com.fonix.FonixFlightOffers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fonix.FonixFlightOffers.exception.NoOfferExistsAsOfNow;
import com.fonix.FonixFlightOffers.model.SubscribeResponse;
import com.fonix.FonixFlightOffers.model.UserSubscription;
import com.fonix.FonixFlightOffers.service.OfferService;

/**
 * This is the home controller to load 
 * the index.html which shows the form to subscribe for flight offers
 */
@Controller
public class HomeController {

	@Autowired
	private OfferService offerService;
	
	/*
	 * This is the API to render index.html
	 */
	@RequestMapping(value="/", method = RequestMethod.GET)
	public String home()
	{
		return "index.html";
	}
	
	/*
	 * This is the API to handle the subscribe request from web page
	 */
	@RequestMapping(value="/subscribe", method = RequestMethod.POST, produces = { "application/json" })
	public @ResponseBody SubscribeResponse home(@RequestBody UserSubscription userSubscription)
	{	
		SubscribeResponse subscribeResponse = new SubscribeResponse();
		if(userSubscription != null && userSubscription.getDestination() != null && userSubscription.getOrigin() != null && userSubscription.getEmail() != null && userSubscription.getFrequency() != null)
		{
			//save the user in the subscription_details table if already not exists else replace and send mail accordingly
			offerService.subscribeUser(userSubscription);
			try {
				//Note: This step should have after user confirms email id by going to confirmation URL 
				//get the best offer and send the first offer to user
				offerService.checkAndSendMail(userSubscription.getOrigin(), userSubscription.getDestination(), userSubscription.getEmail());
				//else return the subscription successful message
				subscribeResponse.setMessage("You have successfully subscribed");
			} 
			catch (NoOfferExistsAsOfNow e) {
				//if no offers exists for the travel user has searched then return the exception on user screen
				subscribeResponse.setMessage(e.getMessage());
			}
		}
		else
		{
			subscribeResponse.setMessage("Please provide all the details of the form");
		}
		return subscribeResponse;
	}
	
}
