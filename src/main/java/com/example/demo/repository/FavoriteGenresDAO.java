package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.FavoriteGenresId;
import com.example.demo.entity.FavoriteGenre;

public interface FavoriteGenresDAO extends JpaRepository<FavoriteGenre, FavoriteGenresId>{

}
