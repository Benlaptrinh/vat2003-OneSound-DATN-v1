package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Singer;

public interface SingerDAO extends JpaRepository<Singer, Long> {

}
