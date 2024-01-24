package com.project.shopapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

    boolean existsByEmail(String email);

}
