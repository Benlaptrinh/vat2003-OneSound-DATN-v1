package com.example.demo.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Service.AccountService;
import com.example.demo.entity.Account;
import com.example.demo.entity.Role;
import com.example.demo.repository.AccountDAO;
import com.example.demo.repository.RoleDAO;

@Service
public class AccountServiceImlp implements AccountService {

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private RoleDAO RoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account createAccount(Account account) {
        if (AccountDAO.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        Role userRole = RoleDAO.findById(6L).orElseThrow();
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        account.setAccountRole(userRole);

        // Save the account only if the email doesn't exist
        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);
        return savedAccount;
    }

    @Override
    public Account updateAccount(Account account) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccount'");
    }

    @Override
    public void deleteAccount(Long accountId) {
        // TODO Auto-generated method stub
        AccountDAO.deleteById(accountId);
    }

    @Override
    public Account getAccountById(Long accountId) {
        // TODO Auto-generated method stub
        return AccountDAO.findById(accountId).orElse(null);
    }

    @Override
    public Account getAccountByEmail(String email) {
        // TODO Auto-generated method stub
        return AccountDAO.findByEmail(email).orElseThrow();
    }

    @Override
    public List<Account> getAllAccount() {
        // TODO Auto-generated method stub
        return AccountDAO.findAll();
    }

    @Override
    public Page<Account> getAllAccount(Pageable pageable) {
        return AccountDAO.findAll(pageable);
    }

}
