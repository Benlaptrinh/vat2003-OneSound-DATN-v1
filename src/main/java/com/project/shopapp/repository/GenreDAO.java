package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Genre;

/**
 * GenreDAO
 */
public interface GenreDAO extends JpaRepository<Genre, Long> {

}