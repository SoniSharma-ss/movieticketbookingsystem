/**
 * 
 */
package com.example.booking.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.booking.core.ShowsEntity;
import com.example.booking.service.ShowsService;

import lombok.extern.log4j.Log4j2;

/**
 * @author sharsoni
 *
 */
@Log4j2
@RestController
@RequestMapping("show")
public class ShowController {

	@Autowired
	private ShowsService showService;

	
	@PostMapping("add")
	public ResponseEntity<ShowsEntity> addShow(@RequestBody ShowsEntity show) {

		log.info("Received Request to add new show: " + show);

		return ResponseEntity.ok(show);
	}

}