package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.ComemtSongService;
import com.project.shopapp.Service.CommentYoutubeService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;
import com.project.shopapp.repository.CommentYoutubeDao;

@Service
public class CommentYoutubeServiceImlp implements CommentYoutubeService {

    @Autowired
    CommentYoutubeDao dao;

    @Override
    public List<CommentYoutube> findAll() {
        return dao.findAll();
    }

    // @Override
    // public List<CommentYoutube> findByYoutubeId(Long YoutubeId) {
    // return dao.findAllById(YoutubeId);
    // }

}
