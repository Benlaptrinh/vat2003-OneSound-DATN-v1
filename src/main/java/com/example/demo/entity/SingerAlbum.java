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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SingerAlbum")
public class SingerAlbum {

	@Id
	@ManyToOne
	@JoinColumn(name = "singer_id")
	private Singer singer;

	@Id
	@ManyToOne
	@JoinColumn(name = "album_id")
	private Album album;
}
