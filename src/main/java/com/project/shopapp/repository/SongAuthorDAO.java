package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SongAuthorId;
import com.project.shopapp.entity.SongAuthor;

public interface SongAuthorDAO extends JpaRepository<SongAuthor, SongAuthorId> {

}
