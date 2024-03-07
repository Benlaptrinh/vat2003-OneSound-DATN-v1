package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.Song;

public interface Songservice {
    List<Song> getAllSong();

    Song findSongsByYoutube_id(String Youtube_id);

}
