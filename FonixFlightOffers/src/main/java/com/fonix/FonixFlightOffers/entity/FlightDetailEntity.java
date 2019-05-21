package com.fonix.FonixFlightOffers.entity;

import java.util.SortedSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

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

	@Column(name= "flight_number")
	private String flightNumber;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "flightDetail", cascade = CascadeType.ALL)
	@SortNatural
	private SortedSet<CrawlerFeedEntity> feeds;

	
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

	public SortedSet<CrawlerFeedEntity> getFeeds() {
		return feeds;
	}

	public void setFeeds(SortedSet<CrawlerFeedEntity> feeds) {
		this.feeds = feeds;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	
}
