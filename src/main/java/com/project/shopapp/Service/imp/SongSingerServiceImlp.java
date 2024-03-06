package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SongSingerService;
import com.project.shopapp.Service.SongSingerService;
import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.SongSinger;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.SongSingerDAO;
import com.project.shopapp.repository.SingerDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongSingerServiceImlp implements SongSingerService {

    private final SongSingerDAO SongSingerDao;

    @Autowired
    SingerDAO sdao;

    @Autowired
    SongDAO adao;

    @Autowired
    public SongSingerServiceImlp(SongSingerDAO SongSingerDao) {
        this.SongSingerDao = SongSingerDao;
    }

    @Override
    public List<SongSinger> getAllSongSingers() {
        // TODO Auto-generated method stub
        return SongSingerDao.findAll();
    }

    @Override
    public SongSinger addSongSinger(SongSingerId SongSingerId) {

        SongSinger SongSinger = new SongSinger();

        // Tìm kiếm Singer
        Singer s = sdao.findById(SongSingerId.getSingerId())
                .orElseThrow(() -> new EntityNotFoundException("Singer not found"));

        // Tìm kiếm Song
        Song a = adao.findById(SongSingerId.getSongId())
                .orElseThrow(() -> new EntityNotFoundException("Song not found"));

        SongSinger.setSong(a);
        SongSinger.setSinger(s);
        SongSinger.setId(SongSingerId);
        
        
        System.out.println("ĐÀU CHÓ: "+a+"ĐàU BÀO: "+s);
        return SongSingerDao.save(SongSinger);
    }

    @Override
    public void removeSongSinger(Long SongId) {

        // // Tìm kiếm Song
        // Song a = adao.findById(SongSingerId)
        // .orElseThrow(() -> new EntityNotFoundException("Song not found"));
        List<SongSinger> SongSinger = SongSingerDao.findBySongId(SongId);
        SongSingerDao.deleteAll(SongSinger);

    }

    @Override
    public void deleteBySongId(Long SongId) {
        SongSingerDao.deleteBySongId(SongId);
    }

}
