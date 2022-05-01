/**
 * 
 */
package com.example.booking.dao.impl;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.core.MovieEntity;
import com.example.booking.service.MovieService;

import lombok.extern.log4j.Log4j2;


/**
 * @author sharsoni
 *
 */
@Log4j2
@RestController
@RequestMapping("movie")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@PostMapping("add")
	public ResponseEntity<Boolean> addMovie(@RequestBody MovieEntity movie) {

		log.info("Received Request to add new movie: " + movie);

		return ResponseEntity.ok(movieService.addMovie(movie ));
	}

	@GetMapping("{id}")
	public ResponseEntity<MovieEntity> getmovie(@PathVariable(name = "id") @Min(value = 1, message = "Movie Id Cannot be -ve") long id) {

		log.info("Received Request to get movie: " + id);

		return ResponseEntity.ok(movieService.getMovie(id));
	}
}