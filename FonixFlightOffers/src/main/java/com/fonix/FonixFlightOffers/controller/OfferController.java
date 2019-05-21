package com.fonix.FonixFlightOffers.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fonix.FonixFlightOffers.entity.CrawlerFeedEntity;
import com.fonix.FonixFlightOffers.entity.FlightDetailEntity;
import com.fonix.FonixFlightOffers.entity.SubscriptionEntity;
import com.fonix.FonixFlightOffers.exception.NoOfferExistsAsOfNow;
import com.fonix.FonixFlightOffers.model.CrawlerRequest;
import com.fonix.FonixFlightOffers.model.Frequency;
import com.fonix.FonixFlightOffers.model.OfferResponse;
import com.fonix.FonixFlightOffers.service.OfferService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * This is the controller used by crawler for get and save operation
 */
@RestController
@Api(value="offers", description="Subscriber to crawler")
public class OfferController {

	@Autowired
	private OfferService offerService;
	
	/*
	 * This API is used by Crawler to get the list of all offers
	 */
	@RequestMapping(value = "/offers/get", method= RequestMethod.GET,produces = { "application/json"})
	@ApiOperation(value = "Get all the offers", response = OfferResponse.class)
	public OfferResponse getAllBestOffers() throws Exception
	{
		//retrieve all the offers
		//Note: I also have thought about returning only the best offers or offers by id but this looks more useful
		return offerService.getAllOffers();
	}
	
	/*
	 * This API is used by crawler to save new offers
	 */
	@RequestMapping(value = "/offers/save", method= RequestMethod.POST,consumes = { "application/json"}, produces = { "application/json" } )
	@ApiOperation(value = "Save offer details")
	public ResponseEntity<String> saveCrawlerFeed(@RequestBody CrawlerRequest crawlerFeed)
	{
		try
		{
			if(crawlerFeed != null && crawlerFeed.getOfferDetail() != null && offerService.checkDateIsValid(crawlerFeed.getOfferDetail().getDate()))
			{
				//save the offer received in the crawler_offers table
				CrawlerFeedEntity savedEntity = offerService.saveCrawlerFeed(crawlerFeed.getOfferDetail());

				//then find the best offer for this origin and destination
				CrawlerFeedEntity bestOffer = offerService.findBestOffer(crawlerFeed.getOfferDetail().getOrigin(), crawlerFeed.getOfferDetail().getDestination());

				//if the latest offer received by the crawler is the best offer then check user subscription and send mail
				if(bestOffer!= null)
				{
					if(bestOffer.getFeedId() == savedEntity.getFeedId())
					{
						//get all the users subscribed to this travel points by checking in the table subscription_details
						List<SubscriptionEntity> allUsers= offerService.getAllUserSubscribedTo(crawlerFeed.getOfferDetail().getOrigin(), crawlerFeed.getOfferDetail().getDestination());
						for(SubscriptionEntity user:allUsers)
						{
							if(user != null)
							{
								LocalDate now = LocalDate.now();
								LocalDate oneDayBehind = now.minusDays(1);
								LocalDate oneWeekBehind = now.minusWeeks(1);
								LocalDate oneMonthBehind = now.minusMonths(1);

								//send the mail if users last mail sent date is null which means when user subscribed there was no offer existed in the database
								//or send mail if the last mail sent date has passed the users subscribed frequency
								if(user.getMailSentDate() == null || user.getFrequency().equals(Frequency.UNCAPPED) ||
										(user.getFrequency().equals(Frequency.DAILY) && Period.between(user.getMailSentDate(),oneDayBehind).getDays() == 0) ||
										(user.getFrequency().equals(Frequency.WEEKLY) && Period.between(user.getMailSentDate(),oneWeekBehind).getDays() == 0) ||
										(user.getFrequency().equals(Frequency.MONTHLY) && Period.between(user.getMailSentDate(),oneMonthBehind).getMonths() == 0))
								{
									offerService.populateOfferDetailAndSendMail(bestOffer, user.getEmail());
								}
							}
						}
					}
				}
				else
				{
					System.out.println("No offer found for future date in the database to send mail");
				}
				System.out.println("Offer successfully saved");
				return new ResponseEntity<String>("Offer successfully saved!", HttpStatus.OK);
			}
			else
				return new ResponseEntity<String>("Please provide future offers", HttpStatus.BAD_REQUEST);
		}
		catch (NoOfferExistsAsOfNow e) {
			System.out.println("Something went wrong as this exception should not occur "+e.getStackTrace());
			return new ResponseEntity<String>("Something went wrong, please try after sometime.", HttpStatus.BAD_REQUEST);
		}
	}
	
}
