
package com.project.shopapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.UserLoginDTO;
import com.project.shopapp.utils.LoginResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.prefix}/users")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

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

    @GetMapping("/email")
    public ResponseEntity<Account> getUserByEmail(@RequestParam String mail) {
        Account account = accountService.getAccountByEmail(mail);
        return ResponseEntity.ok(account);
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

    @PutMapping("/update/{id}")
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

}
