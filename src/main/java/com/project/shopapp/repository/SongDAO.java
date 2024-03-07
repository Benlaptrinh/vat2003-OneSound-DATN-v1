package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Song;

public interface SongDAO extends JpaRepository<Song, Long> {
	Page<Song> findByNameIgnoreCase(String id, Pageable pageable);

	List<Song> findByName(String id);

	@Query("SELECT s FROM Song s WHERE s.album.id = :albumId")
	List<Song> findSongsByAlbumId(@Param("albumId") Long albumId);

	@Query("SELECT s FROM Song s WHERE s.youtube_id = :youtube_id")
	Song findSongsByYoutube_id(@Param("youtube_id") String youtube_id);
}
