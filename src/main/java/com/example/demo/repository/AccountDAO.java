package com.example.demo.repository;

/**
 * AccountDAO
 */

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Account;

public interface AccountDAO extends JpaRepository<Account, Long> {
    Optional<Account> findByEmail(String email);

}
