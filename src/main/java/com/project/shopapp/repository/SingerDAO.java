package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.shopapp.entity.Singer;

public interface SingerDAO extends JpaRepository<Singer, Long> {

    Singer findByFullnameIgnoreCaseContaining(String firstName);

    @Query("select s from Singer s join s.singerAlbums sa join sa.album a where a.id = :albumId")
    List<Singer> findAllSingerByAlbumId(Long albumId);

}