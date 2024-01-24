package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.SongGenreId;

import com.example.demo.entity.SongGenre;

public interface SongGenreDAO extends JpaRepository<SongGenre, SongGenreId>{

}
