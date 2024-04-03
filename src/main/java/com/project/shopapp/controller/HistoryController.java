package com.project.shopapp.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.HistoryListenServeice;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${api.prefix}")
public class HistoryController {

    @Autowired
    private HistoryListenServeice historyListenServeice;

    @PostMapping("/listen/add/{songId}/{userId}")
    public ResponseEntity<?> addHis(@PathVariable("songId") Long songId, @PathVariable("userId") Long userId) {
        LocalDate todayLocalDate = LocalDate.now();
        // Chuyển đổi từ LocalDate sang Date
        Date todayDate = Date.from(todayLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        historyListenServeice.addHistory(songId, userId, todayDate);
        return ResponseEntity.ok().build();
    }

}
