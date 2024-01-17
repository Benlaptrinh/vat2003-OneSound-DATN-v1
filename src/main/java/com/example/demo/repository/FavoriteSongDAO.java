package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.FavoriteSongId;
import com.example.demo.entity.FavoriteSong;

public interface FavoriteSongDAO extends JpaRepository<FavoriteSong, FavoriteSongId> {

}
