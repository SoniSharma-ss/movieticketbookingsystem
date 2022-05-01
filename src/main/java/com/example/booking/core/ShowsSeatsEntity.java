package com.example.booking.core;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.transaction.Transactional;

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
@Table(name = "show_seats")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Transactional
public class ShowsSeatsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_number", nullable = false)
	private String seatNumber;

	@Column(name = "rate", nullable = false)
	private int rate;


	@Column(name = "is_booked", nullable = false)
	private boolean booked;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "booked_at")
	private Date bookedAt;

	@ManyToOne
	private ShowsEntity show;

	@ManyToOne(fetch = FetchType.EAGER)
	private TicketEntity ticket;

	@Override
	public String toString() {
		return "ShowsSeatsEntity [id=" + id + ", seatNumber=" + seatNumber + ", rate=" + rate + ", booked=" + booked
				+ ", bookedAt=" + bookedAt + ", show=" + show + ", ticket=" + ticket + "]";
	}
	
}