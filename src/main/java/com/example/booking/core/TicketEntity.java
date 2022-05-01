package com.example.booking.core;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author sharsoni
 *
 */
@Getter
@Setter
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "tickets")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Transactional
public class TicketEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "alloted_seats", nullable = false)
	private String allottedSeats;

	@Column(name = "amount", nullable = false)
	private double amount;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "booked_at", nullable = false)
	private Date bookedAt;

	@ManyToOne
	private UserEntity user;

	@ManyToOne
	private ShowsEntity show;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "show", cascade = CascadeType.ALL)
	private List<ShowsSeatsEntity> seats;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "TicketEntity [id=" + id + ", allottedSeats=" + allottedSeats + ", amount=" + amount + ", bookedAt="
				+ bookedAt + ", user=" + user + ", show=" + show + ", seats="
				+ (seats != null ? seats.subList(0, Math.min(seats.size(), maxLen)) : null) + "]";
	}

}