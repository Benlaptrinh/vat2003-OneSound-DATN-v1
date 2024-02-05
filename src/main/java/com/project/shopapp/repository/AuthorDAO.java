package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Author;

public interface AuthorDAO extends JpaRepository<Author, Long> {

}
