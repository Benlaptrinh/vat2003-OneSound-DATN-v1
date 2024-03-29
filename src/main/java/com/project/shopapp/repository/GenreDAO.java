package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Genre;

/**
 * GenreDAO
 */
public interface GenreDAO extends JpaRepository<Genre, Long> {
	List<Genre> findByNameContainingIgnoreCase(String title);

	@Query("select COUNT(s) from Genre s")
	List<Integer> staticGenres();

}