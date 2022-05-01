/**
 * 
 */
package com.example.booking.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.core.BookTicketRequest;
import com.example.booking.core.ShowsEntity;
import com.example.booking.core.ShowsSeatsEntity;
import com.example.booking.core.TicketEntity;
import com.example.booking.core.UserEntity;
import com.example.booking.dao.repository.ShowsRepository;
import com.example.booking.dao.repository.TicketRepository;
import com.example.booking.dao.repository.UserRepository;
import com.example.booking.exception.DependencyException;
import com.example.booking.service.TicketService;
import com.example.booking.util.DiscountRules;

import lombok.extern.log4j.Log4j2;


/**
 * @author sharsoni
 *
 */
@Log4j2
@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowsRepository showRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Override
	public TicketEntity bookTicket(BookTicketRequest bookTicketRequest) {

		UserEntity user = getUser( bookTicketRequest );
		ShowsEntity shows = getShows( bookTicketRequest );
		List<ShowsSeatsEntity> availableShowSeatsForBooking = getBookingSeats( bookTicketRequest, shows );

		//Create Ticket Details
		final TicketEntity ticketEntity =
				TicketEntity.builder()
						.user(user)
						.show(shows)
						.seats(availableShowSeatsForBooking)
						.build();

		double amount = 0.0; 
		final AtomicInteger count = new AtomicInteger();
		String allotedSeats = "";
		
		//Apply available discount on rates
		availableShowSeatsForBooking.stream().forEach(s-> DiscountRules.applyRateRule(s,count.incrementAndGet(),ticketEntity)
													);
		
		// Block the seat
		for (ShowsSeatsEntity seats : availableShowSeatsForBooking) {
			seats.setBooked(true); 
			seats.setBookedAt(new Date());
			seats.setTicket(ticketEntity);

			amount += seats.getRate();

			allotedSeats += seats.getSeatNumber() + " ";
		}

		ticketEntity.setAmount(amount);
		ticketEntity.setAllottedSeats(allotedSeats);

		if ((user.getTicketEntities()).isEmpty()) {
			user.setTicketEntities(new ArrayList<>());
		}

		user.getTicketEntities().add(ticketEntity);

		if ((shows.getTickets()).isEmpty()) {
			shows.setTickets(new ArrayList<>());
		}

		shows.getTickets().add(ticketEntity);

		log.info("Creating Booking for User: " + bookTicketRequest.getUserId() + " in Show: " + bookTicketRequest.getShowId() + " for Seats: " + allotedSeats);

		ticketRepository.save(ticketEntity);

		return ticketEntity;
	}



	private List<ShowsSeatsEntity> getBookingSeats(BookTicketRequest bookTicketRequest,ShowsEntity shows) {
		Set<String> requestedSeats = bookTicketRequest.getSeatsNumbers();

		log.info("Requested Bookings For Seats: " + requestedSeats + " of Show: " + bookTicketRequest.getShowId() + " by User: " + bookTicketRequest.getUserId());

		List<ShowsSeatsEntity> showsSeatsEntities = shows.getSeats();

		log.info("Total Number of Seats in Show: " + bookTicketRequest.getShowId() + " are " + showsSeatsEntities.size());

		showsSeatsEntities =
				showsSeatsEntities
						.stream()
						.filter(seat ->  !seat.isBooked()
								&& requestedSeats.contains(seat.getSeatNumber()))
						.collect(Collectors.toList());

		if (showsSeatsEntities.size() != requestedSeats.size()) {
			throw new DependencyException("Seats Not Available for Booking");
		}

		log.info("Seats: " + requestedSeats + " are avaialble in Show: " + bookTicketRequest.getShowId() + " for booking to User " + bookTicketRequest.getUserId());
		
		return showsSeatsEntities;
	}



	private ShowsEntity getShows(BookTicketRequest bookTicketRequest) {
		log.info("Searching Show by id: " + bookTicketRequest.getShowId());

		Optional<ShowsEntity> shows = showRepository.findById(bookTicketRequest.getShowId());

		if (!shows.isPresent()) {
			throw new DependencyException("Show Not Found with ID: " + bookTicketRequest.getUserId() + " to book ticket");
		}
		return shows.get();
	}



	@Override
	public TicketEntity getTicket(long id) {
		log.info("Searching Ticket by id: " + id);

		Optional<TicketEntity> ticketEntity = ticketRepository.findById(id);

		if (!ticketEntity.isPresent()) {
			log.error("Ticket not found for id: " + id);
			throw new EntityNotFoundException("Ticket Not Found with ID: " + id);
		}

		return ticketEntity.get();
	}
	
	private UserEntity getUser(BookTicketRequest bookTicketRequest) {
		log.info("Searching User by id: " + bookTicketRequest.getUserId());
		Optional<UserEntity> user = userRepository.findById(bookTicketRequest.getUserId());

		if (!user.isPresent()) {
			throw new DependencyException("User Not Found with ID: " + bookTicketRequest.getUserId() + " to book ticket");
		}
		return user.get();
	}

}