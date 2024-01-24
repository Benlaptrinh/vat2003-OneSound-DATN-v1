package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.FavoriteSingerId;
import com.example.demo.entity.FavoriteSinger;

public interface FavoriteSingerDAO extends JpaRepository<FavoriteSinger, FavoriteSingerId> {

}
