package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.composite.PlaylistYoutubeId;
import com.project.shopapp.entity.PlaylistYoutube;

public interface PlaylistYoutubeDao extends JpaRepository<PlaylistYoutube, PlaylistYoutubeId> {
    @Modifying
    @Query("DELETE FROM PlaylistYoutube py WHERE py.id.playlistId = :playlistId")
    void deleteAllYoutubesByPlaylistId(@Param("playlistId") Long playlistId);
}
