package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.FavoriteAlbumId;
import com.example.demo.entity.FavoriteAlbum;

public interface FavoriteAlbumDAO extends JpaRepository<FavoriteAlbum, FavoriteAlbumId>  {

}
