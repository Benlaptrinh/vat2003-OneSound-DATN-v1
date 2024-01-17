package com.example.demo.Service;

import com.example.demo.entity.Singer;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SingerService {
    List<Singer> getAllSingers();

    Singer getSingerById(Long id);

    Singer createSinger(Singer singer);

    Singer updateSinger(Long id, Singer singer);

    void deleteSinger(Long id);

    Page<Singer> getAllSingers(Pageable pageable);

}
