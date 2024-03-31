
package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.CommentSong;

public interface ComemtSongDao extends JpaRepository<CommentSong, Long> {
    List<CommentSong> findBySongId(Long songId);

    List<CommentSong> findBySongIdAndRepCommentId(Long songId, Long commentId);

    List<CommentSong> findBySongIdAndId(Long songId, Long commentId);

    List<CommentSong> findBySongIdAndRepCommentIdIsNull(Long songId);

}