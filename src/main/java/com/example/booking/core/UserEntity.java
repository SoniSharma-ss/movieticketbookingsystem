package com.example.booking.core;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

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
@Table(name = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "mobile", nullable = false)
	private String mobile;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<TicketEntity> ticketEntities;

	@Override
	public String toString() {
		final int maxLen = 10;
		return "UserEntity [id=" + id + ", name=" + name + ", mobile=" + mobile + ", ticketEntities="
				+ (ticketEntities != null ? ticketEntities.subList(0, Math.min(ticketEntities.size(), maxLen)) : null)
				+ "]";
	}

	
}