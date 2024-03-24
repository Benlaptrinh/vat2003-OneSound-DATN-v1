package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.PasswordResetToken;

public interface TokenRepositoryDAO extends JpaRepository<PasswordResetToken, Integer> {
    PasswordResetToken findByToken(String token);

    PasswordResetToken findByAccount(Account account);

    @Procedure(name = "DeletePasswordResetToken")
    void DeletePasswordResetToken(@Param("TokenId") int TokenId);
}
