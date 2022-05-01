/**
 * 
 */
package com.example.booking.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.core.MovieEntity;
import com.example.booking.dao.repository.MovieRepository;
import com.example.booking.exception.BookingTechException;
import com.example.booking.exception.DuplicateRecordException;
import com.example.booking.service.MovieService;

import lombok.extern.log4j.Log4j2;


/**
 * @author sharsoni
 *
 */
@Log4j2
@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	@Override
	public boolean addMovie(MovieEntity movie) {

		if (movieRepository.existsByNameAndLanguage(movie.getName(), movie.getLanguage())) {
			throw new DuplicateRecordException("Movie Already Exists with Name: " + movie.getName() + " in Language: " + movie.getLanguage());
		}

		log.info("Adding New Movie: " + movie);
		try 
		{
			if( movieRepository.save(movie) != null ) 
			{
				log.info("Added New Movie [id: " + movie.getId() + ", Name: " + movie.getName() + ", Language: " + movie.getLanguage() + "]");
				return true;
			}

		}
		catch(Exception ex) {
			throw new BookingTechException("Exception while saving movie details " + ex.getMessage());
		}
		
		return false;
	}

	@Override
	public MovieEntity getMovie(long id) {
		log.info("Searching Movie by id: " + id);

		Optional<MovieEntity> movieEntity = movieRepository.findById(id);

		if (movieEntity.isPresent()) {
			log.error("Movie not found for id: " + id);
			throw new EntityNotFoundException("Movie Not Found with ID: " + id);
		}

		return movieEntity.get();
	}

}