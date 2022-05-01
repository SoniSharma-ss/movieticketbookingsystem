/**
 * 
 */
package com.example.booking.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.core.MovieEntity;
import com.example.booking.core.ShowsEntity;
import com.example.booking.core.ShowsSeatsEntity;
import com.example.booking.core.TheaterEntity;
import com.example.booking.core.TheaterSeatsEntity;
import com.example.booking.dao.repository.MovieRepository;
import com.example.booking.dao.repository.ShowsRepository;
import com.example.booking.dao.repository.ShowsSeatsRepository;
import com.example.booking.dao.repository.TheaterRepository;
import com.example.booking.exception.DependencyException;
import com.example.booking.service.ShowsService;

import lombok.extern.log4j.Log4j2;


/**
 * @author sharsoni
 *
 */
@Log4j2
@Service
public class ShowServiceImpl implements ShowsService {

	@Autowired
	private ShowsRepository showsRepository;

	@Autowired
	private MovieRepository movieRepository;

	@Autowired
	private TheaterRepository theaterRepository;

	@Autowired
	private ShowsSeatsRepository showSeatsRepository;

	@Override
	public ShowsEntity addShow(ShowsEntity show) {

		Optional<MovieEntity> optionalMovie = movieRepository.findById(show.getMovie().getId());

		if (!optionalMovie.isPresent()) {
			throw new DependencyException("Movie Not Found with ID: " + show.getMovie().getId() + " to add New Show");
		}

		Optional<TheaterEntity> optionalTheater = theaterRepository.findById(show.getTheater().getId());

		if (!optionalTheater.isPresent()) {
			throw new DependencyException("Theater Not Found with ID: " + show.getMovie().getId() + " to add New Show");
		}

		log.info("Adding New Show: " + show);

		
		ShowsEntity showEntity = showsRepository.save(show);

		log.info("Successfully Added New Show [ID: " + showEntity.getId() + ", ShowDate: " + showEntity.getShowDate() + ", ShowTime: " + showEntity.getShowTime() + "]");

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

	
}