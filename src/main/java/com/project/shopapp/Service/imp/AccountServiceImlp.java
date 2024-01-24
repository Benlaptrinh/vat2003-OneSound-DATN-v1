
package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Role;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.security.JwtTokenUtil;

@Service
public class AccountServiceImlp implements AccountService {

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private com.project.shopapp.repository.RoleDAO RoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public Account createAccount(Account account) {
        if (AccountDAO.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        Role userRole = RoleDAO.findById(2L).orElseThrow();
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        account.setAccountRole(userRole);

        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);
        return savedAccount;
    }

    @Override
    public Account updateAccount(Long id, Account account) {
        Account existingAccount = AccountDAO.findById(id).orElse(null);

        if (existingAccount == null) {
            throw new IllegalArgumentException("Account not found with id: " + id);
        }

        // Check if the email is being updated to an existing email
        String newEmail = account.getEmail();
        if (!newEmail.equals(existingAccount.getEmail()) && AccountDAO.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        // Update other fields if needed
        existingAccount.setFullname(account.getFullname());
        existingAccount.setEmail(newEmail);
        existingAccount.setAddress(account.getAddress());
        existingAccount.setAvatar_url(account.getAvatar_url());
        existingAccount.setGender(account.isGender());

        // Update other fields as needed

        Account updatedAccountEntity = AccountDAO.save(existingAccount);
        return updatedAccountEntity;
    }

    @Override
    public void deleteAccount(Long accountId) {
        AccountDAO.deleteById(accountId);
    }

    @Override
    public Account getAccountById(Long accountId) {
        return AccountDAO.findById(accountId).orElse(null);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return AccountDAO.findByEmail(email).orElseThrow();
    }

    @Override
    public List<Account> getAllAccount() {
        return AccountDAO.findAll();
    }

    @Override
    public Page<Account> getAllAccount(Pageable pageable) {
        return AccountDAO.findAll(pageable);
    }

    @Override
    public String login(String mail, String password) throws Exception {
        Optional<Account> optionalUser = AccountDAO.findByEmail(mail);

        if (optionalUser.isEmpty()) {
            throw new Exception("Invalid phone mail / password");
        }

        Account existingUser = optionalUser.get();
        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Wrong phone number or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                mail, password,
                existingUser.getAuthorities());

        authenticationManager.authenticate(authenticationToken);

        return jwtTokenUtil.generateToken(existingUser);

    }

    @Override
    public Account getUserDetailsFromToken(String token) throws Exception {
        if (jwtTokenUtil.isTokenExpired(token)) {
            throw new Exception("Token is expired");
        }
        String Email = jwtTokenUtil.extractEmail(token);
        Optional<Account> user = AccountDAO.findByEmail(Email);

        if (user.isPresent()) {
            return user.get();
        } else {
            throw new Exception("User not found");
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        return AccountDAO.existsByEmail(email);
    }

}
