package com.example.booking.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.booking.core.TheaterEntity;


/**
 * @author sharsoni
 *
 */
@Repository
public interface TheaterRepository extends RepositoryType<TheaterEntity>,JpaRepository<TheaterEntity, Long>,JpaSpecificationExecutor<TheaterEntity>  {

}