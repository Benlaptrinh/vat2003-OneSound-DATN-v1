
package com.project.shopapp.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.UserLoginDTO;

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
    public ResponseEntity<?> createUser(@Valid @RequestBody com.project.shopapp.entity.Account Account,
            BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }
        try {

            System.out.println("createUser " + Account);
            accountService.createAccount(Account);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = accountService.login(userLoginDTO.getEmail(), userLoginDTO.getPassword());
            return ResponseEntity.ok().build();
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
            return (ResponseEntity<Account>) ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("")
    public List<Account> getAllUser() {
        return accountService.getAllAccount();
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

}
