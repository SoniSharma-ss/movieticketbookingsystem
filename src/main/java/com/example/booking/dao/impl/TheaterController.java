/**
 * 
 */
package com.example.booking.dao.impl;

import java.util.List;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.core.TheaterEntity;
import com.example.booking.service.TheaterService;

import lombok.extern.log4j.Log4j2;


/**
 * @author sharsoni
 *
 */
@Log4j2
@RestController
@RequestMapping("theater")
public class TheaterController {

	@Autowired
	private TheaterService theaterService;

	@PostMapping("add")
	public ResponseEntity<Boolean> addTheater(@RequestBody TheaterEntity theater) {

		log.info("Received Request to add new theater: " + theater);

		return ResponseEntity.ok(theaterService.addTheater(theater));
	}

	
	@GetMapping("all")
	public ResponseEntity<String> getAllTheater() {

		log.info("Received Request to get All theaters");

		return ResponseEntity.ok(theaterService.getAllTheater());
	}
	
	@GetMapping("{id}")
	public ResponseEntity<TheaterEntity> getTheater(@PathVariable(name = "id") @Min(value = 1, message = "Theater Id Cannot be -ve") long id) {

		log.info("Received Request to get theater: " + id);

		return ResponseEntity.ok(theaterService.getTheater(id));
	}
}