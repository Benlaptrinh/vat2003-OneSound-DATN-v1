
package com.project.shopapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.ComemtSongService;
import com.project.shopapp.dto.CommentReplyDTO;
import com.project.shopapp.dto.CommentSongDTO;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.repository.AuthorDAO;
import com.project.shopapp.repository.ComemtSongDao;

/**
 * ComemtSongController
 */

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class ComemtSongController {

    @Autowired
    ComemtSongService ComemtSongService;

    public ComemtSongController(ComemtSongService ComemtSongService) {
        this.ComemtSongService = ComemtSongService;
    }

    @GetMapping("/comments")
    public List<CommentSong> findAllComments() {
        return ComemtSongService.findAll();
    }

    @GetMapping("/comments/{songId}")
    public List<CommentSong> findCommentsBySongId(@PathVariable Long songId) {
        return ComemtSongService.findBySongId(songId);
    }

    @GetMapping("/comments/{songId}/{commentId}/replies")
    public ResponseEntity<List<CommentSong>> findRepliesToComment(@PathVariable("songId") Long songId,
            @PathVariable("commentId") Long commentId) {
        List<CommentSong> replies = ComemtSongService.findBySongIdAndRepCommentId(songId, commentId);
        return ResponseEntity.ok().body(replies);
    }

    @GetMapping("/comments/{songId}/top")
    public ResponseEntity<List<CommentSong>> findTopLevelComments(@PathVariable Long songId) {
        List<CommentSong> comments = ComemtSongService.findBySongIdAndRepCommentIdIsNull(songId);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentSong> addComment(@RequestBody CommentSong comment, @RequestParam("songId") Long songId,
            @RequestParam("userId") Long userId) {
        try {
            CommentSong addedComment = ComemtSongService.addComment(comment, songId, userId);
            return ResponseEntity.ok(addedComment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }

    }
    // @PostMapping("/comments")
    // public ResponseEntity<CommentSong> addComment(@RequestBody CommentSongDTO
    // commentSongDTO) {
    // try {
    // CommentSong addedComment = ComemtSongService.addComment1(commentSongDTO);
    // return ResponseEntity.status(HttpStatus.CREATED).body(addedComment);
    // } catch (Exception e) {
    // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
    // .body(null); // Bạn có thể trả về một thông báo lỗi khác nếu cần thiết
    // }
    // }

}