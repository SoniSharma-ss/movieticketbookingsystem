/**
 * 
 */
package com.example.booking.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.booking.core.UserEntity;
import com.example.booking.dao.repository.UserRepository;
import com.example.booking.exception.DuplicateRecordException;
import com.example.booking.service.UserService;

import lombok.extern.log4j.Log4j2;


/**
 * @author sharsoni
 *
 */
@Log4j2
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean addUser(UserEntity userEntity) {

		if (userRepository.existsByMobile(userEntity.getMobile())) {
			throw new DuplicateRecordException("User Already Exists with Mobile: " + userEntity.getMobile());
		}

		log.info("Adding New User: " + userEntity);

		userEntity = userRepository.save(userEntity);

		log.info("Added New User [id: " + userEntity.getId() + ", Mobile: " + userEntity.getMobile() + "]");

		return true;
	}

	@Override
	public UserEntity getUser(long id) {

		log.info("Searching User by id: " + id);

		Optional<UserEntity> userEntity = userRepository.findById(id);

		if (!userEntity.isPresent()) {
			log.error("User not found for id: " + id);
			throw new EntityNotFoundException("User Not Found with ID: " + id);
		}

		return userEntity.get();
	}

}