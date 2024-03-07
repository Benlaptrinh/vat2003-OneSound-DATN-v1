package com.project.shopapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.SongDAO;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class SongController {

	@Autowired
	SongDAO songDAO;

	@GetMapping("Song/getall")
	public Page<Song> getSong(Pageable pageable) {
		return songDAO.findAll(pageable);
	}

	@GetMapping("Song")
	public List<Song> getAll() {
		return songDAO.findAll();
	}

	@GetMapping("Song/getbyid/{id}")
	public Song getSongById(@PathVariable Long id) {
		return songDAO.findById(id).get();
	}

	@GetMapping("Song/getSongByName")
	public Page<Song> getSongByName(@RequestParam String Name, Pageable pageable) {
		return songDAO.findByNameIgnoreCase(Name, pageable);
	}

	@GetMapping("Songs/albumId/{albumId}")
	public ResponseEntity<List<Song>> getSongByAlbumId(@PathVariable Long albumId) {
		List<Song> song = songDAO.findSongsByAlbumId(albumId);
		return ResponseEntity.ok(song);
	}

	@GetMapping("/Song/Name/{name}")
	public List<Song> getSongByName1(@PathVariable("name") String name) {
		return songDAO.findByName(name);
	}

	@PostMapping("Song/create")
	public ResponseEntity<Song> createAuthor(@RequestBody Song songRequest) {
		// // Validate author data before saving
		Song song = new Song();
		// Album al = new Album();
		song.setName(songRequest.getName());
		song.setImage(songRequest.getImage());
		song.setPath(songRequest.getPath());
		song.setRelease(songRequest.getRelease());
		// song.setLyrics(songRequest.getLyrics());
		// // Cài đặt logic khác nếu cần

		songDAO.save(song);
		return ResponseEntity.status(HttpStatus.CREATED).body(song);
	}

	@PutMapping("Song/update/{id}")
	public Song updateSong(@PathVariable Long id, @RequestBody Song Song) {
		return songDAO.save(Song);
	}

	@DeleteMapping("Song/delete/{id}")
	public ResponseEntity<?> delSong(@PathVariable Long id) {
		songDAO.deleteById(id);
		Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}

	@GetMapping("/Song/Youtubeid/{Youtube_id}")
	public Song getSongByName11(@PathVariable("Youtube_id") String name) {
		return songDAO.findSongsByYoutube_id(name);
	}
}
