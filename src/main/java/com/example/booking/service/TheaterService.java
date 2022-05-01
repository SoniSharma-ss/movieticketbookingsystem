/**
 * 
 */
package com.example.booking.service;

import java.util.List;

import com.example.booking.core.TheaterEntity;

/**
 * @author sharsoni
 *
 */
public interface TheaterService {

	boolean addTheater(TheaterEntity theaterDto);

	TheaterEntity getTheater(long id);

	String getAllTheater();
}