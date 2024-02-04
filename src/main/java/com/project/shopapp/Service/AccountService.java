package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.utils.UpdateUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.shopapp.entity.Account;

public interface AccountService {

    List<Account> getAllAccount();

    Account getUserDetailsFromToken(String token) throws Exception;

    Page<Account> getAllAccount(Pageable pageable);

    Account createAccount(Account account);

    Account updateAccount(Long id, Account account);

    Account updateAccount(Long id, UpdateUserDTO updateUserDTO);


    void deleteAccount(Long accountId);

    Account getAccountById(Long accountId);

    Account getAccountByEmail(String email);

    String login(String mail, String password) throws Exception;

    boolean existsByEmail(String email);

}
