/**
 * 
 */
package com.example.booking.service;

import com.example.booking.core.MovieEntity;

/**
 * @author sharsoni
 *
 */
public interface MovieService {

	boolean addMovie(MovieEntity movie);

	MovieEntity getMovie(long id);
}