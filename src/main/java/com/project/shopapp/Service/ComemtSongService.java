
package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.dto.CommentSongDTO;
import com.project.shopapp.entity.CommentSong;

/**
 * ComemtSongService
 */
public interface ComemtSongService {
    List<CommentSong> findAll();

    List<CommentSong> findBySongId(Long songId);

    List<CommentSong> findBySongIdAndRepCommentId(Long songId, Long commentId);

    List<CommentSong> findBySongIdAndRepCommentIdIsNull(Long songId);

    CommentSong addComment(CommentSong comment, Long songId, Long accountId);

    CommentSong addComment1(CommentSongDTO CommentSongDTO);

}