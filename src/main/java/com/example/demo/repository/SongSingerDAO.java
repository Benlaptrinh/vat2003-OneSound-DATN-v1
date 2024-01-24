package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.SongSingerId;
import com.example.demo.entity.SongSinger;

public interface SongSingerDAO extends JpaRepository<SongSinger, SongSingerId> {

}
