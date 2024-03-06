package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SongAuthorService;
import com.project.shopapp.composite.SongAuthorId;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Author;
import com.project.shopapp.entity.SongAuthor;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.SongAuthorDAO;
import com.project.shopapp.repository.AuthorDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongAuthorServiceImlp implements SongAuthorService {

    private final SongAuthorDAO SongAuthorDao;

    @Autowired
    AuthorDAO sdao;

    @Autowired
    SongDAO adao;

    @Autowired
    public SongAuthorServiceImlp(SongAuthorDAO SongAuthorDao) {
        this.SongAuthorDao = SongAuthorDao;
    }

    @Override
    public List<SongAuthor> getAllSongAuthors() {
        // TODO Auto-generated method stub
        return SongAuthorDao.findAll();
    }

    @Override
    public SongAuthor addSongAuthor(SongAuthorId SongAuthorId) {

        SongAuthor SongAuthor = new SongAuthor();

        // Tìm kiếm Author
        Author s = sdao.findById(SongAuthorId.getAuthorId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        // Tìm kiếm Song
        Song a = adao.findById(SongAuthorId.getSongId())
                .orElseThrow(() -> new EntityNotFoundException("Song not found"));

        SongAuthor.setSong(a);
        SongAuthor.setAuthor(s);
        SongAuthor.setId(SongAuthorId);

        return SongAuthorDao.save(SongAuthor);
    }

    @Override
    public void removeSongAuthor(Long SongId) {

        // // Tìm kiếm Song
        // Song a = adao.findById(SongAuthorId)
        // .orElseThrow(() -> new EntityNotFoundException("Song not found"));
        List<SongAuthor> SongAuthor = SongAuthorDao.findBySongId(SongId);
        SongAuthorDao.deleteAll(SongAuthor);

    }

    @Override
    public void deleteBySongId(Long SongId) {
        SongAuthorDao.deleteBySongId(SongId);
    }

}
