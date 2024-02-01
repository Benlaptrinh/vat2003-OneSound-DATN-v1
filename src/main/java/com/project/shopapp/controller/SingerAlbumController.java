package com.project.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.AlbumService;
import com.project.shopapp.Service.SingerAlbumService;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SingerAlbum;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class SingerAlbumController {

    private final SingerAlbumService SingerAlbumService;

    @Autowired
    public SingerAlbumController(SingerAlbumService SingerAlbumService) {
        this.SingerAlbumService = SingerAlbumService;
    }

    @GetMapping("singerAlbum/getall")
    public List<SingerAlbum> getAlbum() {
        return SingerAlbumService.getAllSingerAlbums();
    }

    @PostMapping("singerAlbum/create")
    public SingerAlbum createAlbum(@RequestParam Long singerId, @RequestParam Long albumId) {
        return SingerAlbumService.addSingerAlbum(singerId, albumId);

    }
}
