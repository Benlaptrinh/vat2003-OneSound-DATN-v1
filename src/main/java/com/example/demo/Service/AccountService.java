package com.example.demo.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.entity.Account;
import com.example.demo.entity.Role;

public interface AccountService {

    List<Account> getAllAccount();

    Account getUserDetailsFromToken(String token) throws Exception;

    Page<Account> getAllAccount(Pageable pageable);

    Account createAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Long accountId);

    Account getAccountById(Long accountId);

    Account getAccountByEmail(String email);

    String login(String mail, String password) throws Exception;

}
