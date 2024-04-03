package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.ComemtSongService;
import com.project.shopapp.Service.CommentYoutubeService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Youtube;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.CommentYoutubeDao;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.YoutubeDAO;

@Service
public class CommentYoutubeServiceImlp implements CommentYoutubeService {

    @Autowired
    CommentYoutubeDao dao;

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private YoutubeDAO YoutubeDAO;

    @Override
    public List<CommentYoutube> findAll() {
        return dao.findAll();
    }

    @Override
    public List<CommentYoutube> findByYoutubeId(String YoutubeId) {
        return dao.findByYoutube_Id(YoutubeId);
    }

    @Override
    public List<CommentYoutube> findBySongIdAndRepCommentIdIsNull(String youtube_id) {
        return dao.findByYoutubeIdAndRepCommentIdIsNull(youtube_id);

    }

    @Override
    public List<CommentYoutube> findBySongIdAndRepCommentId(String youtube_id, Long commentId) {

        return dao.findByYoutubeIdAndRepCommentId(youtube_id, commentId);
    }

    @Override
    public CommentYoutube addComment(CommentYoutube comment, String songId, Long accountId) {

        Account user = AccountDAO.findById(accountId).get();

        Youtube song = YoutubeDAO.findById(songId).get();

        comment.setYoutube(song);
        comment.setUser(user);

        return dao.save(comment);
    }

    @Override
    public CommentYoutube findById(Long commentId) {
        Optional<CommentYoutube> commentOptional = dao.findById(commentId);
        return commentOptional.orElse(null);
    }

    @Override
    public CommentYoutube updateComment(CommentYoutube comment) {
        return dao.save(comment);
    }

    @Override
    public void DeleteRelatedComments(Long commentId) {
        dao.DeleteRelatedCommentsYoutube(commentId);
    }

    @Override
    public boolean isCommentBelongsToUser(Long commentId, Long userId) {
        CommentYoutube comment = dao.findById(commentId).orElse(null);
        if (comment != null && comment.getUser().getId().equals(userId)) {
            return true;
        }
        return false;
    }
}
