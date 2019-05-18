package com.fonix.FonixFlightOffers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonix.FonixFlightOffers.repository.CrawlerFeedRepository;
import com.fonix.FonixFlightOffers.repository.FlightRepository;
import com.fonix.FonixFlightOffers.repository.SubscriptionRepository;


/**
 * This is the service class 
 * which calls repository, gets the data
 * and perform the logical operation
 */
@Service
public class OfferService {
	
	@Autowired
	private CrawlerFeedRepository crawlerFeedRepository;
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
}
