package com.project.shopapp.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Playlists")
public class Playlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Account user_id;

	@JsonIgnore
	@OneToMany(mappedBy = "playlist")
	private List<PlaylistSong> playlistSong;

	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date likeDate = new Date();
}
