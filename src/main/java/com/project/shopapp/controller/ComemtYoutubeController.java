package com.project.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.ComemtSongService;
import com.project.shopapp.Service.CommentYoutubeService;
import com.project.shopapp.entity.CommentSong;
import com.project.shopapp.entity.CommentYoutube;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class ComemtYoutubeController {
    @Autowired
    CommentYoutubeService CommentYoutubeService;

    public ComemtYoutubeController(CommentYoutubeService CommentYoutubeService) {
        this.CommentYoutubeService = CommentYoutubeService;
    }

    @GetMapping("/Comemtyt")
    public List<CommentYoutube> findAllComments() {
        return CommentYoutubeService.findAll();
    }

    @GetMapping("/Comemtyt/{youtubeid}")
    public List<CommentYoutube> findCommentsBySongId(@PathVariable Long youtubeid) {
        return CommentYoutubeService.findByYoutubeId(youtubeid);
    }

}
