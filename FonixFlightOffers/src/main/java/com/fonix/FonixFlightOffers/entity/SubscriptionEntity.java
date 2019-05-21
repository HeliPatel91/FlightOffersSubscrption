package com.fonix.FonixFlightOffers.entity;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fonix.FonixFlightOffers.model.Frequency;

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
	
	@Column(name = "origin")
	private String origin;
	
	@Column(name = "destination")
	private String destination;

	@Column(name="frequency")
    @Enumerated
	private Frequency frequency;

	@Column(name="mail_sent_date")
	private LocalDate mailSentDate;
	
	public SubscriptionEntity() {}
	
	public SubscriptionEntity(String email, String origin, String destination, Frequency frequency) {
		super();
		this.email = email;
		this.origin = origin;
		this.destination = destination;
		this.frequency = frequency;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Frequency getFrequency() {
		return frequency;
	}

	public void setFrequency(Frequency frequency) {
		this.frequency = frequency;
	}

	public LocalDate getMailSentDate() {
		return mailSentDate;
	}

	public void setMailSentDate(LocalDate mailSentDate) {
		this.mailSentDate = mailSentDate;
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

}
