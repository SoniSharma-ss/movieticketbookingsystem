/**
 * 
 */
package com.example.booking.dao.impl;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.core.BookTicketRequest;
import com.example.booking.core.TicketEntity;
import com.example.booking.exception.PaymentException;
import com.example.booking.payment.PaymentGatewayCallable;
import com.example.booking.service.TicketService;

import lombok.extern.log4j.Log4j2;

/**
 * @author sharsoni
 *
 */
@Log4j2
@RestController
@RequestMapping("ticket")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	@PostMapping("book")
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public ResponseEntity<TicketEntity> bookTicket(@RequestBody BookTicketRequest bookTicketRequest) {

		log.info("Received Request to book ticket: " + bookTicketRequest);
		try{
			TicketEntity TicketEntity = ticketService.bookTicket(bookTicketRequest);
			//Payment gateway call
			String paymentResponse = PaymentGatewayCallable.callPaymentService(TicketEntity.getAmount());
			// if response is received
			return ResponseEntity.ok( TicketEntity );
		}
		catch(PaymentException ex) {
			throw ex;
		}
		
	}

	@GetMapping("{id}")
	public ResponseEntity<TicketEntity> getTicket(@PathVariable(name = "id") @Min(value = 1, message = "Ticket Id Cannot be -ve") long id) {

		log.info("Received Request to get ticket: " + id);

		return ResponseEntity.ok(ticketService.getTicket(id));
	}
}