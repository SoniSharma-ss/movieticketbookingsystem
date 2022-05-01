package com.example.booking.core;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.example.booking.enums.MovieLanguage;
import com.example.booking.util.CommonStringUtil;

import lombok.AccessLevel;
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
@Table(name = "movies")
@NoArgsConstructor
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Transactional
public class MovieEntity extends EntityId{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "language", nullable = false)
	private MovieLanguage language;

	@Column(name = "release_date", columnDefinition = "DATE", nullable = false)
	private LocalDate releaseDate;

	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<ShowsEntity> shows = new ArrayList<>();

	@Override
	public String toString() {
		final int maxLen = 10;
		return "MovieEntity [id=" + id + ", name=" + name + ", language=" + language + ", releaseDate=" + releaseDate
				+ ", shows=" + (shows != null ? CommonStringUtil.toString(shows, maxLen) : null) + "]";
	}
	
	
} 
