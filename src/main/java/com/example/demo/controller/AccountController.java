package com.example.demo.controller;

import com.example.demo.Service.AccountService;
import com.example.demo.Service.GenreService;
import com.example.demo.Service.RoleService;
import com.example.demo.entity.Account;
import com.example.demo.entity.Genre;
import com.example.demo.entity.Role;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.prefix}/users")
@CrossOrigin(origins = "http://localhost:4200")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody Account Account, BindingResult result) {
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

}
