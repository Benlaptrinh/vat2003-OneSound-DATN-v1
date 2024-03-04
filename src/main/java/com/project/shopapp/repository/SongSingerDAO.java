package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.SongSinger;
import java.util.List;


public interface SongSingerDAO extends JpaRepository<SongSinger, SongSingerId> {
	
	List<SongSinger> findBySongId(Long songid);
	List<SongSinger> findBySingerId(Long singer);
	void deleteBySongId(long id);
}
