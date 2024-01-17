package com.example.demo.entity;

import jakarta.persistence.Entity;
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
@Table(name = "SongSinger")
public class SongSinger {
	@Id
	@ManyToOne
	@JoinColumn(name = "song_id")
	private Song song;

	@Id
	@ManyToOne
	@JoinColumn(name = "singer_id")
	private Singer singer;
}
