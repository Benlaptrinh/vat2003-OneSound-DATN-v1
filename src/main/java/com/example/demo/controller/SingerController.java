package com.example.demo.controller;

import com.example.demo.entity.Singer;
import com.example.demo.exception.RessourceNotFoundException;
import com.example.demo.repository.SingerDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.Service.SingerService;
import com.example.demo.Service.UploadService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class SingerController {

    @Autowired
    private SingerService singerService;

    @Autowired
    UploadService uploadService;

    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @GetMapping("/Singer")
    public List<Singer> getAllSingers() {
        return singerService.getAllSingers();
    }

    @GetMapping("/Singer/Singers")
    public Page<Singer> getAllSingers(Pageable pageable) {
        return singerService.getAllSingers(pageable);
    }

    @GetMapping("/Singer/{id}")
    public ResponseEntity<Singer> getSingerById(@PathVariable Long id) {
        Singer employee = singerService.getSingerById(id);
        return ResponseEntity.ok(employee);
    }

    // @PostMapping("/Singer")
    // public Singer createEmployee(@RequestBody Singer Singer) {
    // System.out.println(Singer);
    // return singerService.createSinger(Singer);
    // }

    // @PostMapping("/Singer")
    // @ResponseStatus(HttpStatus.CREATED)
    // public Singer createSinger(@RequestPart("imageFile") MultipartFile imageFile,
    // @RequestPart("singer") Singer singer) {
    // return singerService.createSinger(singer, imageFile);
    // }

    @PostMapping("/Singer")
    @ResponseStatus(HttpStatus.CREATED)
    public Singer createSinger(@RequestPart("imageFile") MultipartFile imageFile,
            @RequestPart("singer") Singer singer) {
        // Lưu trữ file hình ảnh
        File imagePath = uploadService.save(imageFile, "images");

        // Gán đường dẫn file hình ảnh vào đối tượng Singer
        singer.setImage(imagePath.toString());

        // Tiếp tục xử lý đối tượng Singer và lưu vào cơ sở dữ liệu
        return singerService.createSinger(singer);
    }

    @PutMapping("/Singer/{id}")
    public Singer updateEmployee(@PathVariable long id, @RequestBody Singer Singer) {
        return singerService.updateSinger(id, Singer);
    }

    @DeleteMapping("/Singer/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        Singer employeeToDelete = singerService.getSingerById(id);
        singerService.deleteSinger(employeeToDelete.getId());
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
