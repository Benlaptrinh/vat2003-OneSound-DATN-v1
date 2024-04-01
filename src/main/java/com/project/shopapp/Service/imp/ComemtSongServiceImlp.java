
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

    //

    @Override
    public List<CommentSong> findBySongIdAndRepCommentIdIsNull(Long songId) {
        return comemtSongDao.findBySongIdAndRepCommentIdIsNull(songId);
    }

    @Override
    public CommentSong addComment(CommentSong comment, Long songId, Long accountId) {

        // Thiết lập mối quan hệ với Account (User)
        Account user = AccountDAO.findById(accountId).get();

        // Thiết lập mối quan hệ với Song
        Song song = SongDAO.findById(songId).get();

        comment.setSong(song);
        comment.setUser(user);

        return comemtSongDao.save(comment);
    }

    // @Override
    // public CommentSong addComment1(CommentSongDTO CommentSongDTO) {
    // CommentSong commentSong = new CommentSong();

    // Optional<Account> optionalAccount =
    // AccountDAO.findById(CommentSongDTO.getUser());

    // if (optionalAccount.isPresent()) {
    // commentSong.setUser(optionalAccount.get());
    // } else {

    // throw new RuntimeException("Account not found with ID: " +
    // CommentSongDTO.getUser());
    // }

    // Optional<Song> optionalSong = SongDAO.findById(CommentSongDTO.getSong());
    // if (optionalSong.isPresent()) {
    // commentSong.setSong(optionalSong.get());
    // } else {

    // throw new RuntimeException("Song not found with ID: " +
    // CommentSongDTO.getSong());
    // }

    // commentSong.setLikeDate(CommentSongDTO.getLikeDate());
    // commentSong.setText(CommentSongDTO.getText());
    // commentSong.setActive(CommentSongDTO.isActive());
    // commentSong.setRepCommentId(CommentSongDTO.getRepCommentId());

    // return comemtSongDao.save(commentSong);

    // }

    @Override
    public CommentSong addComment1(CommentSongDTO commentSongDTO) {
        CommentSong commentSong = new CommentSong();

        // Thiết lập mối quan hệ với Account (User)
        Account user = AccountDAO.findById(commentSongDTO.getUser())
                .orElseThrow(() -> new RuntimeException("Account not found with ID: " + commentSongDTO.getUser()));
        commentSong.setUser(user);

        // Thiết lập mối quan hệ với Song
        Song song = SongDAO.findById(commentSongDTO.getSong())
                .orElseThrow(() -> new RuntimeException("Song not found with ID: " + commentSongDTO.getSong()));
        commentSong.setSong(song);

        // Thiết lập các trường khác
        commentSong.setLikeDate(commentSongDTO.getLikeDate());
        commentSong.setText(commentSongDTO.getText());
        commentSong.setActive(commentSongDTO.isActive());
        commentSong.setRepCommentId(commentSongDTO.getRepCommentId());

        return comemtSongDao.save(commentSong);
    }

}