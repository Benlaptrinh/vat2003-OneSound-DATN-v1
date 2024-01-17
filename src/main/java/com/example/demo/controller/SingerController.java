package com.example.demo.controller;

import com.example.demo.entity.Singer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.Service.SingerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping
    public List<Singer> getAllSingers() {
        return singerService.getAllSingers();
    }

    @GetMapping("/singers")
    public Page<Singer> getAllSingers(Pageable pageable) {
        return singerService.getAllSingers(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Singer> getSingerById(@PathVariable Long id) {
        Singer singer = singerService.getSingerById(id);
        return ResponseEntity.ok(singer);
    }

    @PostMapping
    public ResponseEntity<Singer> createSinger(@RequestBody Singer singer) {
        Singer createdSinger = singerService.createSinger(singer);
        return new ResponseEntity<>(createdSinger, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Singer> updateSinger(@PathVariable Long id, @RequestBody Singer singer) {
        Singer updatedSinger = singerService.updateSinger(id, singer);
        return ResponseEntity.ok(updatedSinger);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSinger(@PathVariable Long id) {
        singerService.deleteSinger(id);
        return ResponseEntity.noContent().build();
    }

}
