package com.project.shopapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.project.shopapp.entity.Singer;

public interface SingerService {
    List<Singer> getAllSingers();

    Singer getSingerById(Long id);

    Singer createSinger(Singer singer);

    Singer updateSinger(Long id, Singer singer);

    void deleteSinger(Long id);

    Page<Singer> getAllSingers(Pageable pageable);

}
