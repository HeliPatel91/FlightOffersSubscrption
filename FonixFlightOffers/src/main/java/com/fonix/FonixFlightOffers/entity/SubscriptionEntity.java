package com.fonix.FonixFlightOffers.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * This is the entity class of
 * table subscription_details which holds all the users subscription data
 */
@Entity
@Table(name="subscription_details")
public class SubscriptionEntity {

	@Id
	@Column(name="email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_id", referencedColumnName = "flight_id")
    private FlightDetailEntity flightDetail;

	@Column(name="frequency")
	private String frequency;

	@Column(name="mail_sent_date")
	private Date mailSentDate;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public FlightDetailEntity getFlightDetail() {
		return flightDetail;
	}

	public void setFlightDetail(FlightDetailEntity flightDetail) {
		this.flightDetail = flightDetail;
	}

	public Date getMailSentDate() {
		return mailSentDate;
	}

	public void setMailSentDate(Date mailSentDate) {
		this.mailSentDate = mailSentDate;
	}
	
}
