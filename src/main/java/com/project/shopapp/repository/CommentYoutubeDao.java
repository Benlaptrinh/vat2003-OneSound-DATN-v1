package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;

public interface CommentYoutubeDao extends JpaRepository<CommentYoutube, Long> {
    List<CommentYoutube> findByYoutubeId(String youtubeId);

}
