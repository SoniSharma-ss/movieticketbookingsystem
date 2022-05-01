/**
 * 
 */
package com.example.booking.service;

import com.example.booking.core.UserEntity;

/**
 * @author sharsoni
 *
 */
public interface UserService {

	boolean addUser(UserEntity userDto);

	UserEntity getUser(long id);
}