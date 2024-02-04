package com.project.shopapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Genre;

public interface AlbumService {

    Page<Album> findAllAlbums(Pageable pageable);

    Album getAlbumById(Long id);

    Album createAlbum(Album Album);

    Album updateAlbum(Long id, Album Album);

    void deleteAlbum(Long id);

    List<Album> findByTitleContaining(String title);

    Page<Album> findByTitleContainingIgnoreCase(String title, Pageable pageable);
}
