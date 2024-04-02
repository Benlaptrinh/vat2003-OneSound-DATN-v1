package com.project.shopapp.Service;

import java.util.Date;

public interface HistoryListenServeice {

    void addHistory(Long songId, Long userId, Date playDate);
}
