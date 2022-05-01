/**
 * 
 */
package com.example.booking.service;

import com.example.booking.core.BookTicketRequest;
import com.example.booking.core.TicketEntity;

/**
 * @author sharsoni
 *
 */
public interface TicketService {

	TicketEntity bookTicket(BookTicketRequest bookTicketRequest);

	TicketEntity getTicket(long id);
}