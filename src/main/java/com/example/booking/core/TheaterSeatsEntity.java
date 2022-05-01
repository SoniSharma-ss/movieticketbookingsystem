package com.example.booking.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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
@Table(name = "theater_seats")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Transactional
public class TheaterSeatsEntity extends EntityId{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "seat_number", nullable = false)
	private String seatNumber;

	@Column(name = "rate", nullable = false)
	private int rate;

	@ManyToOne(fetch = FetchType.EAGER)
	private TheaterEntity theater;

	@Override
	public String toString() {
		return "TheaterSeatsEntity [id=" + id + ", seatNumber=" + seatNumber + ", rate=" + rate + ", theater=" + theater
				+ "]";
	}
	
}