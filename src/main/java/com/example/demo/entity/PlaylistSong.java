package com.example.demo.entity;

import com.example.demo.composite.PlaylistSongId;

import jakarta.persistence.EmbeddedId;
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
@Table(name = "PlaylistSong")
public class PlaylistSong {
	
	@EmbeddedId
	private PlaylistSongId id;

	@ManyToOne
	@JoinColumn(name = "songId", insertable = false, updatable = false)
	private Song song;

	@ManyToOne
	@JoinColumn(name = "playlistId", insertable = false, updatable = false)
	private Playlist playlist;
}
