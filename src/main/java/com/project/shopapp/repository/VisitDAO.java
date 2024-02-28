package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Visit;

public interface VisitDAO extends JpaRepository<Visit, Long> {

}
