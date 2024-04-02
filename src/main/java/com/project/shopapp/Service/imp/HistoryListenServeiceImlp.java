package com.project.shopapp.Service.imp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.HistoryListenServeice;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.HistoryListen;
import com.project.shopapp.entity.ListeningStats;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.HistoryListenDAO;
import com.project.shopapp.repository.SongDAO;

@Service
public class HistoryListenServeiceImlp implements HistoryListenServeice {

    @Autowired
    private SongDAO songDao;
    @Autowired
    private AccountDAO userDao;

    @Autowired
    private HistoryListenDAO listenDAO;

    @Override
    public void addHistory(Long songId, Long userId, Date playDate) {
        Song s = songDao.findById(songId).get();
        Account u = userDao.findById(userId).get();
        LocalDate todayLocalDate = LocalDate.now();
        // Chuyển đổi từ LocalDate sang Date
        Date todayDate = Date.from(todayLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // ListeningStats playCount = playCountRepository.findBySongIdAndDateLis(songId,
        // playDate)
        // .orElse(new ListeningStats(0L, playDate, s));
        // playCount.setListens(playCount.getListens() + 1);
        // playCountRepository.save(playCount);
        HistoryListen hl = listenDAO.findBySongIdAndUserId(songId, userId).orElse(new HistoryListen(s, u, playDate));
        listenDAO.save(hl);

    }

}
