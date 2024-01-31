package com.project.shopapp.Service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.AlbumService;
import com.project.shopapp.entity.Album;
import com.project.shopapp.repository.AlbumDAO;

@Service
public class AlbumServiceImlp implements AlbumService {

    private final AlbumDAO albumDAO;

    @Autowired
    public AlbumServiceImlp(AlbumDAO albumDAO) {
        this.albumDAO = albumDAO;
    }

    @Override
    public Page<Album> findAllAlbums(Pageable pageable) {
        return albumDAO.findAll(pageable);
    }

}
