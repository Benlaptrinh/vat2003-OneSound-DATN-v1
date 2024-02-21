
package com.project.shopapp.controller;

import com.project.shopapp.entity.Singer;
import com.project.shopapp.utils.UpdateUserDTO;
import com.project.shopapp.utils.thongbao;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.Service.PasswordResetTokenService;
import com.project.shopapp.Service.imp.AccountServiceImlp;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.PasswordResetToken;
import com.project.shopapp.entity.Genre;
import com.project.shopapp.entity.UserLoginDTO;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.TokenRepositoryDAO;
import com.project.shopapp.utils.LoginResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/users")
@CrossOrigin(origins = "*")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PasswordResetTokenService PasswordResetTokenService;

    @Autowired
    private AccountServiceImlp AccountServiceImlp;

    @Autowired
    private AccountDAO AccountDAO;

    @Autowired
    private TokenRepositoryDAO TokenRepositoryDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody Account Account,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            accountService.createAccount(Account);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody Account Account,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            accountService.createAccountadmin(Account);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = accountService.login(
                    userLoginDTO.getEmail(),
                    userLoginDTO.getPassword());
            return ResponseEntity.ok(LoginResponse.builder()
                    .token(token)
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @PostMapping("/details")
    public ResponseEntity<Account> getUserDetails(
            @RequestHeader("Authorization") String authorizationHeader) {
        try {
            String extractedToken = authorizationHeader.substring(7);
            Account user = accountService.getUserDetailsFromToken(extractedToken);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public List<Account> getAllUser() {
        return accountService.getAllAccount();
    }

    @GetMapping("/passwordResetTokens") // Đặt tên phản ánh chức năng của API endpoint
    public List<PasswordResetToken> getAllPasswordResetTokens() {
        return PasswordResetTokenService.getAllPasswordResetToken();
    }

    @GetMapping("/page")
    public Page<Account> getAllSingers(Pageable pageable) {
        return accountService.getAllAccount(pageable);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUsere(@PathVariable long id) {
        Account employeeToDelete = accountService.getAccountById(id);
        accountService.deleteAccount(employeeToDelete.getId());
        Map<String, Boolean> response = Map.of("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getUser(@PathVariable Long id) {
        Account employee = accountService.getAccountById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/forgotPassword/{mail}")
    public ResponseEntity<?> forgotPassword(@PathVariable String mail) {
        try {
            Optional<Account> userOptional = AccountDAO.findByEmail(mail);

            if (userOptional.isPresent()) {
                Account user = userOptional.get();
                AccountServiceImlp.sendEmail(user);
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.badRequest().body("Không tìm thấy mail " + mail);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/resetPassword/token/{token}")
    public ResponseEntity<?> resetPasswordForm(@PathVariable String token) {
        PasswordResetToken reset = TokenRepositoryDAO.findByToken(token);

        if (reset == null || AccountServiceImlp.hasExipred(reset.getExpiryDateTime())) {
            return ResponseEntity.badRequest().body("Invalid or expired token");
        }

        try {
            return ResponseEntity.ok(reset.getAccount().getEmail());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/resetPassword/quenmk")
    public ResponseEntity<Account> getUserDetails2(@RequestBody Account Account) {
        try {
            Account user = accountService.getAccountByEmail(Account.getEmail());
            accountService.quenmk(user);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkIfUserExistsByEmail(@PathVariable String email) {
        try {
            // boolean accountExists = accountService.getAccountByEmail(email);
            Account account = accountService.getAccountByEmail(email);
            boolean accountExists = account != null;

            return ResponseEntity.ok(accountExists);
        } catch (Exception e) {
            // Log the exception for debugging purposes
            e.printStackTrace();

            // Return false in case of an error
            return ResponseEntity.ok(false);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody Account updatedAccount,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            accountService.updateAccount(id, updatedAccount);
            return ResponseEntity.ok("User updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/Account")
    public Page<Account> getAllAccounts(Pageable pageable) {
        return accountService.getAllAccount(pageable);
    }

    // <<<<<<< HEAD
    // @PutMapping("/details/{userId}")
    // public ResponseEntity<Account> updateUserDetails(
    // @PathVariable Long userId,
    // @RequestBody UpdateUserDTO updatedUserDTO) {
    // try {
    // Account updatedUser = accountService.updateAccount(userId, updatedUserDTO);
    // return ResponseEntity.ok().build();
    // } catch (Exception e) {
    // return ResponseEntity.badRequest().build();
    // =======

    @PutMapping("/update/admin/{id}")
    public ResponseEntity<?> updateUserr(
            @PathVariable Long id,
            @Valid @RequestBody Account updatedAccount,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            accountService.updateAccountadmin(id, updatedAccount);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUserr(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserDTO UpdateUserDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        try {
            accountService.updateAccount(id, UpdateUserDTO);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PutMapping("/update/pass/{email}")
    public ResponseEntity<?> updatepassuser(
            @PathVariable String email,
            @RequestBody UpdateUserDTO UpdateUserDTO) {

        try {
            Account updatedAccount = accountService.UpdatePassUser(email, UpdateUserDTO);
            return ResponseEntity.ok(updatedAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/mess/{mess}/{email}")
    public ResponseEntity<?> messmail(
            @PathVariable String mess,
            @PathVariable String email) {

        try {
            return ResponseEntity
                    .ok(thongbao.builder().message(AccountServiceImlp.sendCustomEmail(email, mess)).build());

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
