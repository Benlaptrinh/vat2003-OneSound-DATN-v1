package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.GenreService;
import com.example.demo.Service.SingerService;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Singer;

/**
 * GenresController
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class GenresController {

    @Autowired
    private GenreService GenreService;

    public GenresController(GenreService GenreService) {
        this.GenreService = GenreService;
    }

    @GetMapping("/Genre")
    public List<Genre> getAllSingers() {
        return GenreService.getAllGenre();
    }

    @GetMapping("/Genre/Genree")
    public Page<Genre> getAllSingers(Pageable pageable) {
        return GenreService.getAllGenre(pageable);
    }

    @PostMapping("/Genre")
    public Genre createEmployee(@RequestBody Genre Genre) {
        System.out.println(Genre);
        return GenreService.createGenre(Genre);
    }

    @GetMapping("/Genre/{id}")
    public ResponseEntity<Genre> getSingerById(@PathVariable Long id) {
        Genre employee = GenreService.getGenreById(id);
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/Genre/{id}")
    public Genre updateEmployee(@PathVariable long id, @RequestBody Genre Genre) {
        return GenreService.updateGenre(id, Genre);
    }

    @DeleteMapping("/Genre/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        Genre employeeToDelete = GenreService.getGenreById(id);
        GenreService.deleteGenre(employeeToDelete.getId());
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}