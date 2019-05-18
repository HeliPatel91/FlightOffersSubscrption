package com.fonix.FonixFlightOffers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This is the customized exception
 * which will throw the error in UI if something goes wrong
 */
@ResponseStatus(code=HttpStatus.BAD_REQUEST)
public class SystemFailureException extends Exception{

	public SystemFailureException()
	{
		super("");
	}
}
