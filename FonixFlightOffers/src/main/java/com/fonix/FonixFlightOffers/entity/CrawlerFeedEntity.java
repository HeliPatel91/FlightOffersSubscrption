package com.fonix.FonixFlightOffers.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * This is the entity class to 
 * store all the crawler feeds
 */
@Entity
@Table(name = "crawler_offers")
public class CrawlerFeedEntity implements Comparable<CrawlerFeedEntity>{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="feed_id")
	private int feedId;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private FlightDetailEntity flightDetail;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "price")
	private Long price;

	public FlightDetailEntity getFlightDetail() {
		return flightDetail;
	}

	public void setFlightDetail(FlightDetailEntity flightDetail) {
		this.flightDetail = flightDetail;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public int getFeedId() {
		return feedId;
	}

	public void setFeedId(int feedId) {
		this.feedId = feedId;
	}

	@Override
	public int compareTo(CrawlerFeedEntity o) {
		return price.compareTo(o.getPrice());
	}
	
}
