package com.project.shopapp.Service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SongSingerService;
import com.project.shopapp.Service.SongSingerService;
import com.project.shopapp.composite.SongSingerId;
import com.project.shopapp.dto.SingerDTO;
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
    SingerDAO singerdao;

    @Autowired
    SongDAO songdao;

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

    @Override
    public SongSinger createSongSinger(SongSingerId songSingerId) {
        SongSinger ss = new SongSinger();

        Song s = songdao.findById(songSingerId.getSongId()).get();
        Singer singer = singerdao.findById(songSingerId.getSingerId()).get();

        ss.setId(songSingerId);
        ss.setSinger(singer);
        ss.setSong(s);
        return SongSingerDao.save(ss);
    }

    @Override
    public Optional<SongSinger> findSongSingerBySongIdAndSingerId(Long songId, Long singerId) {
        return SongSingerDao.findBySongIdAndSingerId(songId, singerId);
    }

    public List<SingerDTO> findSingersBySongId(Long songId) {
        List<SongSinger> songSingers = SongSingerDao.findBySongId(songId);
        List<SingerDTO> singers = new ArrayList<>();
        for (SongSinger songSinger : songSingers) {
            Singer singer = songSinger.getSinger();
            SingerDTO singerDTO = new SingerDTO();
            singerDTO.setId(singer.getId());
            singerDTO.setFullname(singer.getFullname());
            singerDTO.setDescription(singer.getDescription());
            singerDTO.setImage(singer.getImage());
            singers.add(singerDTO);
        }
        return singers;
    }

    @Override
    public List<Long> findSingerIdsBySongId(Long songId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findSingerIdsBySongId'");
    }

}
