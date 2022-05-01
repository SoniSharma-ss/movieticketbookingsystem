/**
 * 
 */
package com.example.booking.core;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author sharsoni
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class BookTicketRequest {

	@NotEmpty(message = "Select atleast 1 Seat to Book")
	private Set<String> seatsNumbers;

	@Min(value = 1, message = "User is Invalid")
	private long userId;

	@Min(value = 1, message = "Show is Invalid")
	private long showId;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "BookTicketRequest [seatsNumbers=" + (seatsNumbers != null ? toString(seatsNumbers, maxLen) : null)
				+ ", userId=" + userId + ", showId=" + showId + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}
	
	
}