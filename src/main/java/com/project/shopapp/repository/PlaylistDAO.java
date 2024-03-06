package com.project.shopapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Playlist;

public interface PlaylistDAO extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findById(Long id);

    Optional<Playlist> findByName(String name);

}
