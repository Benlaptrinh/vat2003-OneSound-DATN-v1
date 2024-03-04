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
import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongGenre;
import com.project.shopapp.entity.SongGenre;
import com.project.shopapp.repository.SongGenreDAO;
import com.project.shopapp.repository.SongGenreDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("${api.prefix}")
public class SongGenreController {

	@Autowired
    SongGenreDAO SongGenreService;

    @GetMapping("SongGenre/getall")
    public List<SongGenre> getAlbum() {
        return SongGenreService.findAll();
    }

    // @PostMapping("/SongGenre/create")
    // public SongGenre createAlbum(@RequestBody SongGenreId SongGenreId) {
    // return SongGenreService.addSongGenre(SongGenreId);

    // }
    @PostMapping("SongGenre/create")
    public SongGenre createSongGenre(@RequestBody SongGenre SongGenreId) {
        return SongGenreService.save(SongGenreId);
    }
    
    @PutMapping("SongGenre/update")
    public SongGenre updateSongGenre(@RequestBody SongGenre SongGenreId) {
        return SongGenreService.save(SongGenreId);
    }

    @DeleteMapping("SongGenre/delete-by-Genre/{id}")
    public void deleteByGenre(@PathVariable("id") Long SongGenreId) {
    	List<SongGenre> ss=SongGenreService.findByGenreId(SongGenreId);
        SongGenreService.deleteAll(ss);
    }
    
    @DeleteMapping("SongGenre/delete-by-song/{id}")
    public void deleteBySong(@PathVariable("id") Long SongGenreId) {
        SongGenreService.deleteBySongId(SongGenreId);
    }
    
    @GetMapping("SongGenre/get-by-song/{id}")
    public List<SongGenre> getBySongGenre(@PathVariable("id") Long SongGenreId) {
    	List<SongGenre> ss=SongGenreService.findBySongId(SongGenreId);
        return ss;
    }

    
    // Xóa Genre album theo album id
//    @DeleteMapping("SongGenre/deleteByAlbumId/{id}")
//    public void deleteByAlbumId(@PsathVariable("id") Long AlbumId) {
//        SongGenreService.removeSongGenre(AlbumId);
//    }

}
