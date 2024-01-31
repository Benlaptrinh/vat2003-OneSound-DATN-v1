package com.project.shopapp.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.shopapp.entity.Album;

public interface AlbumService {
    Page<Album> findAllAlbums(Pageable pageable);
}
