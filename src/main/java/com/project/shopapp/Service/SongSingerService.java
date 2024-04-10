package com.project.shopapp.Service;

import java.util.List;
import java.util.Optional;

import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.dto.SingerDTO;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SongSinger;

public interface SongSingerService {

    List<SongSinger> getAllSongSingers();

    SongSinger createSongSinger(SongSingerId songSingerId);

    void removeSongSinger(Long SongSingerId);

    void deleteBySongId(Long albumId);

    Optional<SongSinger> findSongSingerBySongIdAndSingerId(Long songId, Long singerId);

    List<Long> findSingerIdsBySongId(Long songId);

    List<SingerDTO> findSingersBySongId(Long songId);

}
