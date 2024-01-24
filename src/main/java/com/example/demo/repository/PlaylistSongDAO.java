package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.PlaylistSongId;
import com.example.demo.entity.PlaylistSong;

public interface PlaylistSongDAO extends JpaRepository<PlaylistSong, PlaylistSongId> {

}
