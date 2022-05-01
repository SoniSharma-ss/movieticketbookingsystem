package com.example.booking.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.booking.core.TicketEntity;


/**
 * @author sharsoni
 *
 */
@Repository
public interface TicketRepository extends RepositoryType<TicketEntity>,JpaRepository<TicketEntity, Long>,JpaSpecificationExecutor<TicketEntity>  {

}