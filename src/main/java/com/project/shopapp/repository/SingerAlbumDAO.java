package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SingerAlbumId;
import com.project.shopapp.entity.SingerAlbum;

public interface SingerAlbumDAO extends JpaRepository<SingerAlbum, SingerAlbumId> {

}
