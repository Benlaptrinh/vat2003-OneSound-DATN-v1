package com.example.demo.Service.imp;

import com.example.demo.Service.SingerService;
import com.example.demo.entity.Singer;
import com.example.demo.repository.SingerDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerDAO singerRepository;

    @Override
    public List<Singer> getAllSingers() {
        return singerRepository.findAll();
    }

    @Override
    public Singer getSingerById(Long id) {
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    public Singer createSinger(Singer singer) {
        return singerRepository.save(singer);
    }

    @Override
    public Singer updateSinger(Long id, Singer singer) {
        Singer employeeToUpdate = singerRepository.findById(id).orElse(null);
        employeeToUpdate.setFullname(singer.getFullname());
        employeeToUpdate.setDescription(singer.getDescription());
        employeeToUpdate.setImage(singer.getImage());
        return singerRepository.save(employeeToUpdate); // Handle not found case
    }

    @Override
    public void deleteSinger(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public Page<Singer> getAllSingers(org.springframework.data.domain.Pageable pageable) {
        return singerRepository.findAll(pageable);

    }

}
