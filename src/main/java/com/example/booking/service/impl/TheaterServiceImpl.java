/**
 * 
 */
package com.example.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.core.TheaterEntity;
import com.example.booking.core.TheaterSeatsEntity;
import com.example.booking.dao.repository.TheaterRepository;
import com.example.booking.dao.repository.TheaterSeatsRepository;
import com.example.booking.service.TheaterService;

import lombok.extern.log4j.Log4j2;

/**
 * @author sharsoni
 *
 */
@Log4j2
@Service
public class TheaterServiceImpl implements TheaterService {

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;

	@Override
	public boolean addTheater(TheaterEntity theaterEntity) {
		log.info("Adding New Theater: " + theaterEntity);

		theaterEntity.getSeats().addAll(getTheaterSeats());

		for (TheaterSeatsEntity seatsEntity : theaterEntity.getSeats()) {
			seatsEntity.setTheater(theaterEntity);
		}

		theaterEntity = theaterRepository.save(theaterEntity);

		log.info("Added New User [id: " + theaterEntity.getId() + ", Name: " + theaterEntity.getName() + "]");

		return true;
	}
	
	@Override
	public TheaterEntity getTheater(long id) {
		log.info("Searching Theater by id: " + id);

		Optional<TheaterEntity> theaterEntity = theaterRepository.findById(id);

		if (!theaterEntity.isPresent()) {
			log.error("Theater not found for id: " + id);
			throw new EntityNotFoundException("Theater Not Found with ID: " + id);
		}

		return theaterEntity.get();
	}

	@Override
	//public List<TheaterEntity> getAllTheater() {
	public String getAllTheater() {
		log.info("Fetch All Theater ");

		List<TheaterEntity> theatersList = theaterRepository.findAll();

		if (theatersList.isEmpty()) {
			log.error("Theaters not found ");
			throw new EntityNotFoundException("Theater Not Found ");
		}

		log.info("Theater Details:="+theatersList.toString());
		return theatersList.toString();
	}
	
	
	//Default configuration based on assumption
	private List<TheaterSeatsEntity> getTheaterSeats() {

		List<TheaterSeatsEntity> seats = new ArrayList<>();

		seats.add(getTheaterSeat("1A", 100));
		seats.add(getTheaterSeat("1B", 100));
		seats.add(getTheaterSeat("1C", 100));
		seats.add(getTheaterSeat("1D", 100));
		seats.add(getTheaterSeat("1E", 100));

		seats = theaterSeatsRepository.saveAll(seats);

		log.info("Added " + seats.size() + " Seats for New Theater");

		return seats;
	}

	private TheaterSeatsEntity getTheaterSeat(String seatNumber, int rate) {
		return TheaterSeatsEntity.builder().seatNumber(seatNumber).rate(rate).build();
	}

	

	

}