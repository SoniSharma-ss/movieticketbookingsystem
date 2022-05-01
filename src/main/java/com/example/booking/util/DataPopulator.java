/**
 * 
 */
package com.example.booking.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.core.MovieEntity;
import com.example.booking.core.ShowsEntity;
import com.example.booking.core.ShowsSeatsEntity;
import com.example.booking.core.TheaterEntity;
import com.example.booking.core.TheaterSeatsEntity;
import com.example.booking.core.UserEntity;
import com.example.booking.dao.repository.MovieRepository;
import com.example.booking.dao.repository.ShowsRepository;
import com.example.booking.dao.repository.ShowsSeatsRepository;
import com.example.booking.dao.repository.TheaterRepository;
import com.example.booking.dao.repository.TheaterSeatsRepository;
import com.example.booking.dao.repository.TicketRepository;
import com.example.booking.dao.repository.UserRepository;

import com.example.booking.enums.MovieLanguage;


import lombok.extern.log4j.Log4j2;


/**
 * @author sharsoni
 *
 */
@Log4j2
@RestController
@RequestMapping(value="/initialize")
public class DataPopulator {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ShowsRepository showsRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TicketRepository ticketRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private ShowsSeatsRepository showSeatsRepository;

	@Autowired
	private TheaterSeatsRepository theaterSeatsRepository;

	@GetMapping("/generate")
	public ResponseEntity<String> generate() {

		log.info("Deleting Data from Shows, Movies and Theaters");

		userRepository.deleteAllInBatch();
		ticketRepository.deleteAllInBatch();

		showSeatsRepository.deleteAllInBatch();
		theaterSeatsRepository.deleteAllInBatch();

		showsRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();
		theaterRepository.deleteAllInBatch();
		movieRepository.deleteAllInBatch();

		log.info("Adding user");

		UserEntity userEntity = UserEntity.builder().name("sharsoni").mobile("1234567890").build();
		userRepository.save(userEntity);

		log.info("Adding Theater");
		
		TheaterEntity pvrDelhi = addTheater("PVR", "Delhi", "Rajori Garden");
		TheaterEntity pvrGurugram = addTheater("PVR", "Gurugram", "MG Road");
		TheaterEntity carnivalDelhi = addTheater("Carnival", "Delhi", "Subhash Garden");

		log.info("Adding Movie");
		MovieEntity spidermanMovie = addMovie("Spiderman", MovieLanguage.ENGLISH, LocalDate.now());
		MovieEntity batmanMovie = addMovie("Batman", MovieLanguage.ENGLISH, LocalDate.now().minusDays(1));

		
		log.info("Adding Shows");
		List<ShowsEntity> showEntities = new ArrayList<>();

		showEntities.add(addShow(LocalDate.now(), LocalTime.NOON, 1.0f, pvrDelhi, spidermanMovie));
		showEntities.add(addShow(LocalDate.now(), LocalTime.NOON, 1.1f, pvrGurugram, spidermanMovie));
		showEntities.add(addShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.0f, pvrDelhi, spidermanMovie));
		showEntities.add(addShow(LocalDate.now().plusDays(1), LocalTime.NOON, 1.4f, pvrGurugram, spidermanMovie));
		showEntities.add(addShow(LocalDate.now(), LocalTime.NOON, 1.2f, carnivalDelhi, spidermanMovie));
		showEntities.add(addShow(LocalDate.now(), LocalTime.NOON.plusHours(1), 1.5f, carnivalDelhi, batmanMovie));

		showsRepository.saveAll(showEntities);

		log.debug("Added " + showEntities.size() + " Shows");

		return ResponseEntity.ok(showEntities.size() + " Shows Added");
	}

	private ShowsEntity addShow(LocalDate showDate, LocalTime showTime, float multiplier, TheaterEntity theaterEntity, MovieEntity movieEntity) {

		ShowsEntity showEntity =
				ShowsEntity.builder()
						.showDate(showDate)
						.showTime(showTime)
						.rateMultiplier(multiplier)
						.theater(theaterEntity)
						.movie(movieEntity)
						.build();

		theaterEntity.getShows().add(showEntity);
		movieEntity.getShows().add(showEntity);
		showEntity.setSeats(generateShowSeats(theaterEntity.getSeats(), showEntity));

		for (ShowsSeatsEntity seatsEntity : showEntity.getSeats()) {
			seatsEntity.setShow(showEntity);
		}

		return showEntity;

	}

	private List<ShowsSeatsEntity> generateShowSeats(List<TheaterSeatsEntity> theaterSeatsEntities, ShowsEntity showEntity) {

		List<ShowsSeatsEntity> showSeatsEntities = new ArrayList<>();

		for (TheaterSeatsEntity theaterSeatsEntity : theaterSeatsEntities) {

			ShowsSeatsEntity showSeatsEntity =
					ShowsSeatsEntity.builder()
							.seatNumber(theaterSeatsEntity.getSeatNumber())
							.rate((int) (theaterSeatsEntity.getRate() * showEntity.getRateMultiplier()))
							.build();

			showSeatsEntities.add(showSeatsEntity);
		}

		return showSeatsRepository.saveAll(showSeatsEntities);
	}

	private TheaterEntity addTheater(String name, String city, String address) {

		TheaterEntity theaterEntity = 
				TheaterEntity.builder()
						.name(name)
						.city(city)
						.address(address)
						.seats(new ArrayList<>())
						.shows(new ArrayList<>())
						.build();

		theaterEntity.getSeats().addAll(addTheaterSeats());

		for (TheaterSeatsEntity seatsEntity : theaterEntity.getSeats()) {
			seatsEntity.setTheater(theaterEntity);
		}

		theaterEntity = theaterRepository.save(theaterEntity);

		return theaterEntity;
	}

	private List<TheaterSeatsEntity> addTheaterSeats() {

		List<TheaterSeatsEntity> seats = new ArrayList<>();

		seats.add(addTheaterSeat("1A", 100));
		seats.add(addTheaterSeat("1B", 100));
		seats.add(addTheaterSeat("1C", 100));
		seats.add(addTheaterSeat("1D", 100));
		seats.add(addTheaterSeat("1E", 100));


		seats = theaterSeatsRepository.saveAll(seats);

		return seats;
	}

	private TheaterSeatsEntity addTheaterSeat(String seatNumber, int rate) {
		return TheaterSeatsEntity.builder().seatNumber(seatNumber).rate(rate).build();
	}

	private MovieEntity addMovie(String name, MovieLanguage language,LocalDate releaseDate) {
		MovieEntity movieEntity =
				MovieEntity.builder()
						.name(name)
						.language(language)
						.releaseDate(releaseDate)
						.shows(new ArrayList<>())
						.build();

		movieEntity = movieRepository.save(movieEntity);

		return movieEntity;
	}
}