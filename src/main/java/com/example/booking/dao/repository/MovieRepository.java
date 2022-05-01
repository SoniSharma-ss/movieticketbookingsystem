package com.example.booking.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.booking.core.MovieEntity;
import com.example.booking.enums.MovieLanguage;


/**
 * @author sharsoni
 *
 */
@Repository
public interface MovieRepository extends RepositoryType<MovieEntity>,JpaRepository<MovieEntity, Long>,JpaSpecificationExecutor<MovieEntity> {

	boolean existsByNameAndLanguage(String name, MovieLanguage language);
	MovieEntity findByName(String name);
	MovieEntity findByNameAndLanguage(String name, MovieLanguage language);
	
}