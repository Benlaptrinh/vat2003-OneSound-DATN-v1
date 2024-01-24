package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.SongGenre;

public interface SongGenreDAO extends JpaRepository<SongGenre, SongGenreId> {

}
