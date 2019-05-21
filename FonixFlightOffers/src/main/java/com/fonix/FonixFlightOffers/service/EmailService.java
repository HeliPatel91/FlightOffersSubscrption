package com.fonix.FonixFlightOffers.service;

import org.springframework.stereotype.Service;

import com.fonix.FonixFlightOffers.model.EmailContent;
import com.fonix.FonixFlightOffers.model.OfferDetail;

@Service
public class EmailService {
	
	//populate the confirmation email
	public void populateMailForConfirmationAndSend(String emailAddress)
	{
		EmailContent email = new EmailContent("",
				"This is the confimration email sent by Fonix flight offers. Please confirm by clicking the URL"
				, "Fonix flight offers confirmation", emailAddress);
		sendMail(email);
	}
	
	//populate offer email
	public void populateOfferMailAndSend(String emailAddress, OfferDetail offerDetail)
	{
		EmailContent email = new EmailContent(null,
				"We found cheapest offer for the flight " + offerDetail.getFlightNumber()+
				" with Origin "+offerDetail.getOrigin() +
				" and destination "+offerDetail.getDestination() + 
				" for the date "+offerDetail.getDate() +
				" for amount "+ offerDetail.getPrice()
				, "Best offer found", emailAddress);
		sendMail(email);
	}
	
	//send email
	public void sendMail(EmailContent email)
	{
		System.out.println("########################################");
		System.out.println("Sending an email to "+email.getToEmailAddress());
		System.out.println("Subject: "+email.getSubject());
		if(null != email.getConfirmationUrl())
			System.out.println("Body: "+email.getBody() +" Confirmation URL: "+email.getConfirmationUrl());
		else
			System.out.println("Body: "+email.getBody());
		System.out.println("########################################");
	}
}
