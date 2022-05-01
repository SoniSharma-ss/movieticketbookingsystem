package com.example.booking.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.booking.core.TheaterSeatsEntity;

/**
 * @author sharsoni
 *
 */
@Repository
public interface TheaterSeatsRepository extends RepositoryType<TheaterSeatsEntity>,JpaRepository<TheaterSeatsEntity, Long>,JpaSpecificationExecutor<TheaterSeatsEntity>  {

}