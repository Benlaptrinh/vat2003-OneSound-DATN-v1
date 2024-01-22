package com.example.demo.Service.imp;

import org.springframework.stereotype.Service;

import com.example.demo.Service.AccountService;
import com.example.demo.entity.Account;

@Service
public class AccountServiceImlp implements AccountService {

    @Override
    public Account createAccount(Account account) {
        // TODO Auto-generated method stub
        System.out.println(account);
        return account;
    }

    @Override
    public Account updateAccount(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccount'");
    }

    @Override
    public void deleteAccount(Long accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }

    @Override
    public Account getAccountById(Long accountId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountById'");
    }

    @Override
    public Account getAccountByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAccountByEmail'");
    }

}
