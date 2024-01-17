package com.example.demo.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Songs")
public class Song {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String image;
	private String path;

	@Lob
	private String lyrics;

	@ManyToOne
	@JoinColumn(name = "album_id")
	private Album album;

	@JsonIgnore
	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	private List<SongSinger> songSingers;

	@JsonIgnore
	@OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
	private List<SongGenre> songGenres;
}
