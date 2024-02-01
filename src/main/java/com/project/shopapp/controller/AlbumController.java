package com.project.shopapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.AlbumService;
import com.project.shopapp.entity.Album;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("album/getall")
    public Page<Album> getAlbum(Pageable pageable) {
        return albumService.findAllAlbums(pageable);
    }

    @GetMapping("album/getbyid/{id}")
    public Album getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id);
    }

    @PostMapping("album/create")
    public Album createAlbum(@RequestBody Album album) {
        return albumService.createAlbum(album);

    }

    @PutMapping("album/{id}")
    public Album updateAlbum(@PathVariable Long id, @RequestBody Album album) {

        return albumService.updateAlbum(id, album);
    }

    @DeleteMapping("album/delete/{id}")
    public ResponseEntity<?> delAlbum(@PathVariable Long id) {
        albumService.deleteAlbum(id);
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
