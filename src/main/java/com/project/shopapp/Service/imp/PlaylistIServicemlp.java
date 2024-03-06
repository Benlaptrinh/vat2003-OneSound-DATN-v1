package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.PlaylistService;
import com.project.shopapp.entity.Playlist;
import com.project.shopapp.repository.PlaylistDAO;

@Service
public class PlaylistIServicemlp implements PlaylistService {

    @Autowired
    PlaylistDAO dao;

    @Override
    public List<Playlist> getAllPlaylist() {
        return dao.findAll();
    }

    @Override
    public Playlist createPlaylist(Playlist playlist) {
        return dao.save(playlist);
    }

    @Override
    public Playlist updatePlaylist(Long id, Playlist playlist) {
        Optional<Playlist> existingPlaylistOptional = dao.findById(id);
        Playlist existingPlaylist = existingPlaylistOptional.orElseThrow();
        existingPlaylist.setName(playlist.getName());
        return dao.save(existingPlaylist);
    }

    @Override
    public void deletePlaylist(Long id) {
        dao.deleteById(id);
    }

    @Override
    public Optional<Playlist> findByName(String name) {
        return dao.findByName(name);
    }

}
