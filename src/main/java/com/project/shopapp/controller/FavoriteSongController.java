package com.project.shopapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.FavoriteSongService;
import com.project.shopapp.Service.PlaylistSongService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.FavoriteSong;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.Song;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class FavoriteSongController {

    @Autowired
    FavoriteSongService FavoriteSongService;

    public FavoriteSongController(FavoriteSongService FavoriteSongService) {
        this.FavoriteSongService = FavoriteSongService;
    }

    @GetMapping("/FavoriteSong")
    public List<FavoriteSong> getAllFavoriteSong() {
        return FavoriteSongService.getAllFavoriteSong();
    }

    @PostMapping("/FavoriteSong")
    public ResponseEntity<?> addFavoriteSong(@RequestBody Map<String, Long> requestBody) {
        try {
            Long accountId = requestBody.get("accountId");
            Long songId = requestBody.get("songId");

            Account account = new Account();
            account.setId(accountId);

            Song song = new Song();
            song.setId(songId);
            FavoriteSongService.addFavoriteSong(account, song);

            return ResponseEntity.ok("Song added to FavoriteSong successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to add song to the FavoriteSong.");
        }
    }

    @DeleteMapping("/FavoriteSong")
    public ResponseEntity<?> removeFavoriteSong(@RequestBody Map<String, Long> requestBody) {
        try {
            Long accountId = requestBody.get("accountId");
            Long songId = requestBody.get("songId");

            FavoriteSongService.removeFavoriteSong(accountId, songId);
            return ResponseEntity.ok("Song removed from FavoriteSong successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove song from FavoriteSong.");
        }
    }

    @PostMapping("/FavoriteSong/isSongLiked")
    public ResponseEntity<?> isSongLikedByUser(@RequestBody Map<String, Long> requestBody) {
        try {
            Long accountId = requestBody.get("accountId");
            Long songId = requestBody.get("songId");

            FavoriteSong favoriteSong = FavoriteSongService.getFavoriteSongByUserAndSong(accountId, songId);

            if (favoriteSong != null) {
                return ResponseEntity.ok(favoriteSong);
            } else {
                return ResponseEntity.ok(null);
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error checking if the song is liked by the user.");
        }
    }

    @GetMapping("/FavoriteSong/{userId}")
    public ResponseEntity<List<FavoriteSong>> getFavoriteSongsByUserId(@PathVariable Long userId) {
        List<FavoriteSong> favoriteSongs = FavoriteSongService.getAllLikedSongsByUser(userId);

        if (favoriteSongs.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(favoriteSongs);
        }
    }

}
