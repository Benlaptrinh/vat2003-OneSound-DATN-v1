package com.example.demo.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FavoriteSongs")
public class FavoriteGenre {

	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date likeDate = new Date();

	@Id
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account user;

	@Id
	@ManyToOne
	@JoinColumn(name = "GenreID", nullable = false)
	private Genre genre;

	@Embeddable
	public static class FavoriteGenreId implements Serializable {
		private static final long serialVersionUID = 1L;

		private Long user;
		private Long genre;

		public FavoriteGenreId() {
		}

		public FavoriteGenreId(Long user, Long genre) {
			this.user = user;
			this.genre = genre;
		}

		// getters, setters, and equals/hashCode methods
	}
}
