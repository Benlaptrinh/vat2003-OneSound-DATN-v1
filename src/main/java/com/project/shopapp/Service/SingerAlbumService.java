package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.SingerAlbum;

public interface SingerAlbumService {

    List<SingerAlbum> getAllSingerAlbums();

    SingerAlbum addSingerAlbum(Long singerId, Long albumId);

}
