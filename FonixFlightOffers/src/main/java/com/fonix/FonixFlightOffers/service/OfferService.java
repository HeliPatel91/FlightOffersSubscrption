package com.fonix.FonixFlightOffers.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fonix.FonixFlightOffers.entity.CrawlerFeedEntity;
import com.fonix.FonixFlightOffers.entity.FlightDetailEntity;
import com.fonix.FonixFlightOffers.entity.SubscriptionEntity;
import com.fonix.FonixFlightOffers.exception.NoOfferExistsAsOfNow;
import com.fonix.FonixFlightOffers.model.Frequency;
import com.fonix.FonixFlightOffers.model.OfferDetail;
import com.fonix.FonixFlightOffers.model.OfferResponse;
import com.fonix.FonixFlightOffers.model.UserSubscription;
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
	
	@Autowired
	private EmailService emailService;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public OfferResponse getAllOffers()
	{
		//find all the offers
		List<CrawlerFeedEntity> listOfFeed= crawlerFeedRepository.findAll();
		
		//create the placeholder for returning response to crawler
		OfferResponse finalResponse = new OfferResponse();
		if(null != listOfFeed && !listOfFeed.isEmpty())
		{
			List<OfferDetail> offerDetails = new ArrayList<OfferDetail>();

			for(CrawlerFeedEntity feed:listOfFeed)
			{
				OfferDetail offerDetail = new OfferDetail();
				offerDetail.setPrice(feed.getPrice());
				offerDetail.setDate(formatter.format(feed.getDate()));
				offerDetail.setDestination(feed.getFlightDetail().getDestination());
				offerDetail.setFlightNumber(feed.getFlightDetail().getFlightNumber());
				offerDetail.setOrigin(feed.getFlightDetail().getOrigin());
				offerDetails.add(offerDetail);
			}
			finalResponse.setOfferDetails(offerDetails);
		}
		return finalResponse;
	}
	
	public CrawlerFeedEntity saveCrawlerFeed(OfferDetail offerDetail)
	{
		//save the feed from Crawler and return the feed_id
		CrawlerFeedEntity createFeed = new CrawlerFeedEntity();
		createFeed.setPrice(offerDetail.getPrice());
		createFeed.setDate(LocalDate.parse(offerDetail.getDate(), formatter));
		
		FlightDetailEntity flightMatched = flightRepository.findByOriginAndDestination(offerDetail.getOrigin(),offerDetail.getDestination());
		if(null == flightMatched)
		{
			FlightDetailEntity flightDetail = new FlightDetailEntity();
			flightDetail.setDestination(offerDetail.getDestination());
			flightDetail.setOrigin(offerDetail.getOrigin());
			flightDetail.setFlightNumber(offerDetail.getFlightNumber());
			createFeed.setFlightDetail(flightRepository.save(flightDetail));
		}
		else
			createFeed.setFlightDetail(flightMatched);
		return crawlerFeedRepository.save(createFeed);
	}
	
	public void subscribeUser(UserSubscription userSubscription)
	{
		//check if the user already subscribed
		Optional<SubscriptionEntity> alreadyExists = subscriptionRepository.findById(userSubscription.getEmail());
		//if subscribed , delete the previous entry and create new one
		if(null != alreadyExists && alreadyExists.isPresent())
		{
			subscriptionRepository.deleteById(userSubscription.getEmail());
		}
		else
		{
			//populate email and send
			emailService.populateMailForConfirmationAndSend(userSubscription.getEmail());
		}
		//save the user subscription information
		SubscriptionEntity subscribeUser = new SubscriptionEntity(userSubscription.getEmail(),userSubscription.getOrigin(),userSubscription.getDestination(),Frequency.valueOf(userSubscription.getFrequency().toUpperCase()));
		subscriptionRepository.save(subscribeUser);
	}
	
	public void checkAndSendMail(String origin, String destination, String email) throws NoOfferExistsAsOfNow
	{
		//check for the best offer when user subscribes and send the first offer email
		CrawlerFeedEntity bestFeed = findBestOffer(origin, destination);
		if(null != bestFeed && null != bestFeed.getFlightDetail())
		{
			//populate offer details and send mail
			populateOfferDetailAndSendMail(bestFeed, email);
		}
	}
	
	public CrawlerFeedEntity findBestOffer(String origin, String destination) throws NoOfferExistsAsOfNow
	{
		//get the flightDetailEntity which has crawler feed list as OneToMany relationship with flight_details and crawler_offers
		FlightDetailEntity flightMatched = flightRepository.findByOriginAndDestination(origin,destination);
		
		if(null != flightMatched)
		{
			List<CrawlerFeedEntity> feeds = crawlerFeedRepository.getByFlightDetails(flightMatched);
			//compareTo method overriden to get the highest price 
			Collections.sort(feeds);
			for(CrawlerFeedEntity feed: feeds)
			{
				if(feed != null && (LocalDate.now().isBefore(feed.getDate()) || LocalDate.now().equals(feed.getDate())))
				{
					return feed;
				}
			}
			return null;
		}
		else
		{
			throw new NoOfferExistsAsOfNow(origin, destination);
		}
	}
	
	public List<SubscriptionEntity> getAllUserSubscribedTo(String origin, String destination)
	{
		//used to find all the subscribed user to given origin and destination when crawler fed that origin and destination offer
		return subscriptionRepository.findUsersSubscribed(origin, destination);
	}
	
	public void populateOfferDetailAndSendMail(CrawlerFeedEntity bestFeed, String email)
	{
		//creation of offerDetail object which will be used for sending the mail
		OfferDetail offerDetail = new OfferDetail();
		offerDetail.setDate(formatter.format(bestFeed.getDate()));
		offerDetail.setDestination(bestFeed.getFlightDetail().getDestination());
		offerDetail.setOrigin(bestFeed.getFlightDetail().getOrigin());
		offerDetail.setFlightNumber(bestFeed.getFlightDetail().getFlightNumber());
		offerDetail.setPrice(bestFeed.getPrice());
		emailService.populateOfferMailAndSend(email, offerDetail);
		//update the mail sent date for the user to now
		SubscriptionEntity toUpdate = subscriptionRepository.findById(email).get();
		toUpdate.setMailSentDate(LocalDate.now());
		subscriptionRepository.save(toUpdate);
	}
	
	public boolean checkDateIsValid(String date)
	{
		if(LocalDate.now().isAfter(LocalDate.parse(date, formatter)))
			return false;
		return true;
	}

}
