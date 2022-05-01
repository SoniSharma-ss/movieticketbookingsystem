package com.example.booking.exception;

public class PaymentException extends RuntimeException{

	private static final long serialVersionUID = 4L;

	private final String message;

	public PaymentException(String message) {
		super(message);
		this.message = message;
	}

}
