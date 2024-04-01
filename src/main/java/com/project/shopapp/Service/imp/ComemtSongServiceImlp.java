
package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.ComemtSongService;
import com.project.shopapp.dto.CommentSongDTO;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.ComemtSongDao;
import com.project.shopapp.repository.SongDAO;

@Service
public class ComemtSongServiceImlp implements ComemtSongService {

    @Autowired
    private ComemtSongDao comemtSongDao;

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private SongDAO SongDAO;

    @Override
    public List<CommentSong> findAll() {
        return comemtSongDao.findAll();

    }

    @Override
    public List<CommentSong> findBySongId(Long songId) {
        return comemtSongDao.findBySongId(songId);
    }

    @Override
    public List<CommentSong> findBySongIdAndRepCommentId(Long songId, Long commentId) {
        return comemtSongDao.findBySongIdAndRepCommentId(songId, commentId);
    }

    @Override
    public List<CommentSong> findBySongIdAndRepCommentIdIsNull(Long songId) {
        return comemtSongDao.findBySongIdAndRepCommentIdIsNull(songId);
    }

    @Override
    public CommentSong addComment(CommentSong comment, Long songId, Long accountId) {

        Account user = AccountDAO.findById(accountId).get();

        Song song = SongDAO.findById(songId).get();

        comment.setSong(song);
        comment.setUser(user);

        return comemtSongDao.save(comment);
    }

    @Override
    public CommentSong findById(Long commentId) {
        Optional<CommentSong> commentOptional = comemtSongDao.findById(commentId);
        return commentOptional.orElse(null);
    }

    @Override
    public CommentSong updateComment(CommentSong comment) {
        return comemtSongDao.save(comment);
    }

    @Override
    public void deleteComment(Long commentId) {
        comemtSongDao.deleteById(commentId);
    }

    @Override
    public void DeleteRelatedComments(Long commentId) {
        comemtSongDao.DeleteRelatedComments(commentId);
    }

    @Override
    public boolean isCommentBelongsToUser(Long commentId, Long userId) {
        CommentSong comment = comemtSongDao.findById(commentId).orElse(null);
        if (comment != null && comment.getUser().getId().equals(userId)) {
            return true;
        }
        return false;
    }

}