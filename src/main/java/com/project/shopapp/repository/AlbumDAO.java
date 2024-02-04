package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Album;

public interface AlbumDAO extends JpaRepository<Album, Long> {
    List<Album> findByTitleContainingIgnoreCase(String title);

    Page<Album> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}