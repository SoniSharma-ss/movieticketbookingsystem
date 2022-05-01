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
public class DependencyException extends RuntimeException {

	private static final long serialVersionUID = 3L;

	private final String message;

	public DependencyException(String message) {
		super(message);
		this.message = message;
	}

}