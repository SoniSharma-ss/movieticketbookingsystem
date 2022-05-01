package com.example.booking.exception;

import lombok.Getter;

/**
 * @author sharsoni
 *
 */
@Getter
public class BookingTechException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private final String message;

	public BookingTechException(String message) {
		super(message);
		this.message = message;
	}

}
