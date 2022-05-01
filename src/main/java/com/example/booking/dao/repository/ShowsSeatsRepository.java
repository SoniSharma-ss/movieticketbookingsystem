package com.example.booking.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.booking.core.ShowsSeatsEntity;


/**
 * @author sharsoni
 *
 */
@Repository
public interface ShowsSeatsRepository extends RepositoryType<ShowsSeatsEntity>,JpaRepository<ShowsSeatsEntity, Long>,JpaSpecificationExecutor<ShowsSeatsEntity>  {

}