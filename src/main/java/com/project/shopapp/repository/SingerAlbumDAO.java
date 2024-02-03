package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SingerAlbumId;
import com.project.shopapp.entity.SingerAlbum;

public interface SingerAlbumDAO extends JpaRepository<SingerAlbum, SingerAlbumId> {

    List<SingerAlbum> findAllByAlbumId(Long albumId);

    void deleteByAlbumId(Long albumId);
}
