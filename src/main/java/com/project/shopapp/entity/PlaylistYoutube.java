package com.project.shopapp.entity;

import com.project.shopapp.composite.FavoriteYoutubeId;
import com.project.shopapp.composite.PlaylistYoutubeId;

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
@Table(name = "PlaylistYoutube")
public class PlaylistYoutube {

    @EmbeddedId
    private PlaylistYoutubeId id;

    @ManyToOne
    @JoinColumn(name = "youtubeId", insertable = false, updatable = false)
    private Youtube youtube;

    @ManyToOne
    @JoinColumn(name = "playlistId", insertable = false, updatable = false)
    private Playlist playlist;
}
