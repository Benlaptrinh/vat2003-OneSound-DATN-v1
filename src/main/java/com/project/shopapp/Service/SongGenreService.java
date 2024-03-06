package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.composite.SongAuthorId;
import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongAuthor;
import com.project.shopapp.entity.SongGenre;

public interface SongGenreService {

    void deleteBySongId(Long albumId);

	void removeSongGenre(Long SongId);

	List<SongGenre> getAllSongGenres();

	SongGenre addSongGenre(SongGenreId SongGenreId);
}
