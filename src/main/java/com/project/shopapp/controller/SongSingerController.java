package com.project.shopapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.AlbumService;
import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongSinger;
import com.project.shopapp.repository.SongSingerDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("${api.prefix}")
public class SongSingerController {

	@Autowired
    SongSingerDAO SongSingerService;

    @GetMapping("SongSinger/getall")
    public List<SongSinger> getAlbum() {
        return SongSingerService.findAll();
    }

    // @PostMapping("/SongSinger/create")
    // public SongSinger createAlbum(@RequestBody SongSingerId SongSingerId) {
    // return SongSingerService.addSongSinger(SongSingerId);

    // }
    @PostMapping("SongSinger/create")
    public SongSinger createAlbum(@RequestBody SongSinger SongSingerId) {
        return SongSingerService.save(SongSingerId);
    }
    
    @PutMapping("SongSinger/update")
    public SongSinger updateAlbum(@RequestBody SongSinger SongSingerId) {
        return SongSingerService.save(SongSingerId);
    }

    @DeleteMapping("SongSinger/delete-by-singer/{id}")
    public void deleteBySinger(@PathVariable("id") Long SongSingerId) {
    	List<SongSinger> ss=SongSingerService.findBySingerId(SongSingerId);
        SongSingerService.deleteAll(ss);
    }
    
    @DeleteMapping("SongSinger/delete-by-song/{id}")
    public void deleteBySong(@PathVariable("id") Long SongSingerId) {
        SongSingerService.deleteBySongId(SongSingerId);
    }

    
    // Xóa singer album theo album id
//    @DeleteMapping("SongSinger/deleteByAlbumId/{id}")
//    public void deleteByAlbumId(@PsathVariable("id") Long AlbumId) {
//        SongSingerService.removeSongSinger(AlbumId);
//    }

}