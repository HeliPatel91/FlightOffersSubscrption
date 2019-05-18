package com.fonix.FonixFlightOffers.entity;

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
public class CrawlerFeedEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="feed_id")
	private int crawlerId;
	
	@ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "flight_id")
    private FlightDetailEntity flightDetail;
	
	@Column(name = "date")
	private Date date;
	
	@Column(name = "price")
	private long price;

	public FlightDetailEntity getFlightDetail() {
		return flightDetail;
	}

	public void setFlightDetail(FlightDetailEntity flightDetail) {
		this.flightDetail = flightDetail;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public int getCrawlerId() {
		return crawlerId;
	}

	public void setCrawlerId(int crawlerId) {
		this.crawlerId = crawlerId;
	}
	
}
