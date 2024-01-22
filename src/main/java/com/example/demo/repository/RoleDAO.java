package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.SingerAlbumId;
import com.example.demo.entity.Role;
import com.example.demo.entity.SingerAlbum;

/**
 * RoleDAO
 */
public interface RoleDAO extends JpaRepository<Role, Long> {
    Optional<Role> findById(int i);
}