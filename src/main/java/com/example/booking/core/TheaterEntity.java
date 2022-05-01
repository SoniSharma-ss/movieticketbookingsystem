package com.example.booking.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.example.booking.util.CommonStringUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * @author sharsoni
 *
 */

@Getter
@Setter
@Entity
@Table(name = "theaters")
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Transactional
public class TheaterEntity extends EntityId{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;


	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "address", nullable = false)
	private String address;

	@OneToMany(fetch = FetchType.EAGER,mappedBy = "theater", cascade = CascadeType.ALL)
	private List<ShowsEntity> shows = new ArrayList<>();

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "theater", cascade = CascadeType.ALL)
	private List<TheaterSeatsEntity> seats = new ArrayList<>();

	@Override
	public String toString() {
		final int maxLen = 10;
		return "TheaterEntity [id=" + id + ", name=" + name + ", city=" + city + ", address=" + address + ", shows="
				+ (shows != null ? toString(shows, maxLen) : null) + ", seats="
				+ (seats != null ? CommonStringUtil.toString(seats, maxLen) : null) + "]";
	}

	private String toString(Collection<ShowsEntity> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<ShowsEntity> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			ShowsEntity show =iterator.next();
			builder.append(show.getId());
			builder.append(show.getMovie().toString() ); 
		}
		builder.append("]");
		return builder.toString();
	}
	
}