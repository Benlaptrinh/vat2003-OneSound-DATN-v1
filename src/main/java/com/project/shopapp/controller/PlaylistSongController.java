package com.project.shopapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.PlaylistService;
import com.project.shopapp.Service.PlaylistSongService;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.entity.PlaylistSong;
import com.project.shopapp.entity.Song;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class PlaylistSongController {

    @Autowired
    private PlaylistSongService PlaylistSongService;

    public PlaylistSongController(PlaylistSongService PlaylistSongService) {
        this.PlaylistSongService = PlaylistSongService;
    }

    @GetMapping("/PlaylistSong")
    public List<PlaylistSong> getAllPlaylistSong() {
        return PlaylistSongService.getAllPlaylistSong();
    }

    @GetMapping("/PlaylistSong/{playlistId}/{songId}")
    public ResponseEntity<?> findSongInPlaylist(@PathVariable Long playlistId,
            @PathVariable Long songId) {
        try {
            PlaylistSong playlistSong = PlaylistSongService.findSongFromPlaylist(playlistId, songId);

            if (playlistSong != null) {
                return ResponseEntity.ok(playlistSong);
            } else {

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Song not found in the playlist.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to find song in playlist.");
        }
    }

    @GetMapping("/PlaylistSong/playlist/{playlistId}")
    public ResponseEntity<?> getAllSongsInPlaylist(@PathVariable Long playlistId) {
        try {
            List<PlaylistSong> songsInPlaylist = PlaylistSongService.findSongsByPlaylistId(playlistId);

            if (!songsInPlaylist.isEmpty()) {
                return ResponseEntity.ok(songsInPlaylist);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No songs found in the playlist.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to get songs in the playlist.");
        }
    }

    @PostMapping("/PlaylistSong")
    public ResponseEntity<?> addSongToPlaylist1(@RequestBody Map<String, Long> requestBody) {
        try {
            Long playlistId = requestBody.get("playlist");
            Long songId = requestBody.get("song");

            Playlist playlist = new Playlist();
            playlist.setId(playlistId);

            Song song = new Song();
            song.setId(songId);

            PlaylistSongService.addSongToPlaylist(playlist, song);

            return ResponseEntity.ok("Song added to playlist successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add song to the playlist.");
        }
    }

    @DeleteMapping("/PlaylistSong/{playlistId}/{songId}")
    public ResponseEntity<?> removeSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        try {
            PlaylistSongService.removeSongFromPlaylist(playlistId, songId);
            return ResponseEntity.ok("Song removed from playlist successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove song from playlist.");
        }
    }

    @DeleteMapping("/PlaylistSong/{playlistId}")
    public ResponseEntity<?> removeAllSongsFromPlaylist(@PathVariable Long playlistId) {
        try {
            PlaylistSongService.removeAllSongsFromPlaylist(playlistId);

            return ResponseEntity.ok("All songs removed from playlist successfully.");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to remove songs from playlist.");
        }
    }

}
