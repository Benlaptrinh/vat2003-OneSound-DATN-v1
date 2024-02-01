package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SingerAlbumService;
import com.project.shopapp.composite.SingerAlbumId;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.SingerAlbum;
import com.project.shopapp.repository.AlbumDAO;
import com.project.shopapp.repository.SingerAlbumDAO;
import com.project.shopapp.repository.SingerDAO;

@Service
public class SingerAlbumServiceImlp implements SingerAlbumService {

    private final SingerAlbumDAO singerAlbumDao;

    @Autowired
    SingerDAO sdao;

    @Autowired
    AlbumDAO adao;

    @Autowired
    public SingerAlbumServiceImlp(SingerAlbumDAO singerAlbumDao) {
        this.singerAlbumDao = singerAlbumDao;
    }

    @Override
    public List<SingerAlbum> getAllSingerAlbums() {
        // TODO Auto-generated method stub
        return singerAlbumDao.findAll();
    }

    @Override
    public SingerAlbum addSingerAlbum(Long singerId, Long albumId) {
        SingerAlbum singerAlbum = new SingerAlbum();
        SingerAlbumId singerAlbumId = new SingerAlbumId(singerId, albumId);

        Singer s = new Singer();
        s = sdao.findById(singerId).get();

        Album a = new Album();
        a = adao.findById(albumId).get();

        singerAlbum.setAlbum(a);
        singerAlbum.setSinger(s);
        singerAlbum.setId(singerAlbumId);
        return singerAlbumDao.save(singerAlbum);
    }

}
