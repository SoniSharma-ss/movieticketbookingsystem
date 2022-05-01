package com.example.booking.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.booking.core.ShowsEntity;

/**
 * @author sharsoni
 *
 */
@Repository
public interface ShowsRepository extends RepositoryType<ShowsEntity>,JpaRepository<ShowsEntity, Long>,JpaSpecificationExecutor<ShowsEntity>  {

}