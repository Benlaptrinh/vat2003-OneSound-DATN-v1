package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.utils.UpdateUserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.UserLoginDTO;

public interface AccountService {

    List<Account> getAllAccount();

    Account getUserDetailsFromToken(String token) throws Exception;

    Page<Account> getAllAccount(Pageable pageable);

    Account createAccount(Account account);

    Account createAccountadmin(Account account);

    Account quenmk(Account account);

    Account updateAccount(Long id, Account account);

    Account updateAccount(Long id, UpdateUserDTO updateUserDTO);

    Account updateAccountadmin(Long id, Account account);

    void deleteAccount(Long accountId);

    Account getAccountById(Long accountId);

    Account getAccountByEmail(String email);

    String login(String mail, String password) throws Exception;

    boolean existsByEmail(String email);

    Account UpdatePassUser(String email, UpdateUserDTO UpdateUserDTO);

}
