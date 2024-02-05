
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
        if(account.getAccountRole()==null) {
        Role userRole = RoleDAO.findById(2L).orElseThrow();
        account.setAccountRole(userRole);
        }
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        

        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);
        return savedAccount;
    }

    @Override
    public Account updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = AccountDAO.findById(id).orElse(null);
        if(updatedAccount.getEmail()!=existingAccount.getEmail()) {
        	Account other=AccountDAO.findByEmail(updatedAccount.getEmail()).orElse(null);
        	if(other!=null && other.getId()!=existingAccount.getId()) {
        		System.err.println("Đã có tài khoản đăng ký địa chỉ email này, vui lòng chọn email khác!");
        		// Update the fields of the existing account with the provided values
                existingAccount.setFullname(updatedAccount.getFullname());
                existingAccount.setActive(updatedAccount.isActive());
                existingAccount.setAddress(updatedAccount.getAddress());
                existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
                existingAccount.setGender(updatedAccount.getGender());
                existingAccount.setBirthday(updatedAccount.getBirthday());
                existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
                existingAccount.setAccountRole(updatedAccount.getAccountRole());
                existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
        	}
        	else {
        		existingAccount.setFullname(updatedAccount.getFullname());
                existingAccount.setActive(updatedAccount.isActive());
                existingAccount.setAddress(updatedAccount.getAddress());
                existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
                existingAccount.setGender(updatedAccount.getGender());
                existingAccount.setBirthday(updatedAccount.getBirthday());
                existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
                existingAccount.setAccountRole(updatedAccount.getAccountRole());
                existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
                existingAccount.setEmail(updatedAccount.getEmail());
                System.err.println("Đang chạy trường hợp 2");
        	}
        }
        else {
        	existingAccount.setFullname(updatedAccount.getFullname());
            existingAccount.setActive(updatedAccount.isActive());
            existingAccount.setAddress(updatedAccount.getAddress());
            existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
            existingAccount.setGender(updatedAccount.getGender());
            existingAccount.setBirthday(updatedAccount.getBirthday());
            existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
            existingAccount.setAccountRole(updatedAccount.getAccountRole());
            existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
            existingAccount.setEmail(updatedAccount.getEmail());
            System.err.println("Đang chạy trường hợp 3");
        }
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
