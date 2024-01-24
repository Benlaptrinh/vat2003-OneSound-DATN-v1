package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Singer;

/**
 * SingerDAO
 */
public interface SingerDAO extends JpaRepository<Singer, Long> {

}
