package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FavoriteAlbumId;
import com.project.shopapp.entity.FavoriteAlbum;

public interface FavoriteAlbumDAO extends JpaRepository<FavoriteAlbum, FavoriteAlbumId> {

}
