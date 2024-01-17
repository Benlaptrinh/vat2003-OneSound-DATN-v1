package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SongGenres")
public class SongGenre {

	@Id
	@ManyToOne
	@JoinColumn(name = "song_id")
	private Song song;

	@Id
	@ManyToOne
	@JoinColumn(name = "genre_id")
	private Genre genre;
}
