package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.PlaylistSongId;
import com.project.shopapp.entity.PlaylistSong;

public interface PlaylistSongDAO extends JpaRepository<PlaylistSong, PlaylistSongId> {

}
