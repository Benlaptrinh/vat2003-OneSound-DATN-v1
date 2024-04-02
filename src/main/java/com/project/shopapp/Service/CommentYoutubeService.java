package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;

public interface CommentYoutubeService {
    List<CommentYoutube> findAll();

    // List<CommentYoutube> findByYoutubeId(Long YoutubeId);

}
