package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.SongSinger;

public interface SongSingerDAO extends JpaRepository<SongSinger, SongSingerId> {

}
