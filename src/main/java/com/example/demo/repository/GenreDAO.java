package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Genre;

/**
 * GenreDAO
 */
public interface GenreDAO extends JpaRepository<Genre, Long> {

}