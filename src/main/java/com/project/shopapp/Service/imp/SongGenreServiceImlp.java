package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SongGenreService;
import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Genre;
import com.project.shopapp.entity.SongGenre;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.SongGenreDAO;
import com.project.shopapp.repository.GenreDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongGenreServiceImlp implements SongGenreService {

    private final SongGenreDAO SongGenreDao;

    @Autowired
    GenreDAO sdao;

    @Autowired
    SongDAO adao;

    @Autowired
    public SongGenreServiceImlp(SongGenreDAO SongGenreDao) {
        this.SongGenreDao = SongGenreDao;
    }

    @Override
    public List<SongGenre> getAllSongGenres() {
        // TODO Auto-generated method stub
        return SongGenreDao.findAll();
    }

    @Override
    public SongGenre addSongGenre(SongGenreId SongGenreId) {

        SongGenre SongGenre = new SongGenre();

        // Tìm kiếm Genre
        Genre s = sdao.findById(SongGenreId.getGenreId())
                .orElseThrow(() -> new EntityNotFoundException("Genre not found"));

        // Tìm kiếm Song
        Song a = adao.findById(SongGenreId.getSongId())
                .orElseThrow(() -> new EntityNotFoundException("Song not found"));

        SongGenre.setSong(a);
        SongGenre.setGenre(s);
        SongGenre.setId(SongGenreId);

        return SongGenreDao.save(SongGenre);
    }

    @Override
    public void removeSongGenre(Long SongId) {
        // // Tìm kiếm Song
        // Song a = adao.findById(SongGenreId)
        // .orElseThrow(() -> new EntityNotFoundException("Song not found"));
        List<SongGenre> SongGenre = SongGenreDao.findBySongId(SongId);
        SongGenreDao.deleteAll(SongGenre);
    }

    @Override
    public void deleteBySongId(Long SongId) {
        SongGenreDao.deleteBySongId(SongId);
    }

}
