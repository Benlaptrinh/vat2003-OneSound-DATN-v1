package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FavoriteSongId;
import com.project.shopapp.entity.FavoriteSong;

public interface FavoriteSongDAO extends JpaRepository<FavoriteSong, FavoriteSongId> {

}
