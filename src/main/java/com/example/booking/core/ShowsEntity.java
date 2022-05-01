package com.example.booking.core;


import java.time.LocalDate;
import java.time.LocalTime;
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
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@EntityListeners(value = { AuditingEntityListener.class })
@Table(name = "shows")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Transactional
public class ShowsEntity extends EntityId{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "show_date", columnDefinition = "DATE", nullable = false)
	private LocalDate showDate;

	@Column(name = "show_time", columnDefinition = "TIME", nullable = false)
	private LocalTime showTime;

	@Column(name = "rate_multiplier", columnDefinition = "float(2,1) default 1.0", nullable = false)
	private float rateMultiplier;

	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name = "created_at")
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne
	private MovieEntity movie;

	@ManyToOne
	private TheaterEntity theater;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "show", cascade = CascadeType.ALL)
	private List<TicketEntity> tickets;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "show", cascade = CascadeType.ALL)
	private List<ShowsSeatsEntity> seats;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "ShowsEntity [id=" + id + ", showDate=" + showDate + ", showTime=" + showTime + ", rateMultiplier="
				+ rateMultiplier + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", movie=" + movie
				+ ", theater=" + theater + ", tickets="
				+ (tickets != null ? tickets.subList(0, Math.min(tickets.size(), maxLen)) : null) + ", seats="
				+ (seats != null ? seats.subList(0, Math.min(seats.size(), maxLen)) : null) + "]";
	}
	

}