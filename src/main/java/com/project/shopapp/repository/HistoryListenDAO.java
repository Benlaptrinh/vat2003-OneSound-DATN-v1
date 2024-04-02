package com.project.shopapp.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.HistoryListen;
import com.project.shopapp.entity.ListeningStats;

/**
 * HistoryListenDAO
 */
public interface HistoryListenDAO extends JpaRepository<HistoryListen, Long> {
    @Query("SELECT ls FROM HistoryListen ls WHERE ls.song.id = :songId AND ls.user.id = :userId")
    Optional<HistoryListen> findBySongIdAndUserId(@Param("songId") Long songId, @Param("userId") Long userId);

}