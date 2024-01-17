package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.SingerAlbumId;
import com.example.demo.entity.SingerAlbum;

public interface SingerAlbumDAO extends JpaRepository<SingerAlbum, SingerAlbumId> {

}
