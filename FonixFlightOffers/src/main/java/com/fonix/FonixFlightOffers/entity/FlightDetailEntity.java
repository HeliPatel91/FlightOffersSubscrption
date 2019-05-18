package com.fonix.FonixFlightOffers.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This is the entity class of
 * table flight_details which holds 
 * flight details
 */
@Entity
@Table(name = "flight_details")
public class FlightDetailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="flight_id")
	private int id;
	
	@Column(name = "origin")
	private String origin;
	
	@Column(name = "destination")
	private String destination;

	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "flightDetail", cascade = CascadeType.ALL)
	private List<CrawlerFeedEntity> feeds;
	
	@OneToOne(mappedBy = "flightDetail")
	private SubscriptionEntity subscriber;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public List<CrawlerFeedEntity> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<CrawlerFeedEntity> feeds) {
		this.feeds = feeds;
	}
	
}
