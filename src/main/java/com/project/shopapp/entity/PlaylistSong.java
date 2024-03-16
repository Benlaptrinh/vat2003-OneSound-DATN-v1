package com.project.shopapp.entity;

import com.project.shopapp.composite.PlaylistSongId;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    @Temporal(TemporalType.DATE)
    @Column(name = "Createdate")
    private Date likeDate = new Date();
}
