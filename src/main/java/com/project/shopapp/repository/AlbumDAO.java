package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Album;

public interface AlbumDAO extends JpaRepository<Album, Long> {

}