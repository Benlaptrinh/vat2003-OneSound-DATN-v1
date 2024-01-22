package com.example.demo.Service;

import com.example.demo.entity.Account;

public interface AccountService {

    Account createAccount(Account account);

    Account updateAccount(Account account);

    void deleteAccount(Long accountId);

    Account getAccountById(Long accountId);

    Account getAccountByEmail(String email);
}
