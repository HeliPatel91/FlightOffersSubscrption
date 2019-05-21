package com.fonix.FonixFlightOffers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is the customized exception
 * which will throw the error in UI if something goes wrong
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class NoOfferExistsAsOfNow extends Exception{
	
	public NoOfferExistsAsOfNow(String origin, String destination)
	{
		super("There is no offer exists for the flight origin "+origin+" and destination "+destination+". We will send offer when found.");
	}
}
