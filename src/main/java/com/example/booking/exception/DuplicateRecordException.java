/**
 * 
 */
package com.example.booking.exception;

import lombok.Getter;

/**
 * @author sharsoni
 *
 */
@Getter
public class DuplicateRecordException extends RuntimeException {

	private static final long serialVersionUID = 2L;

	private final String message;

	public DuplicateRecordException(String message) {
		super(message);
		this.message = message;
	}

}