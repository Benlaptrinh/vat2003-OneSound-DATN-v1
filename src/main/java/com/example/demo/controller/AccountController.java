package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.AccountService;

/**
 * AccountController
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("${api.prefix}")
public class AccountController {

    @Autowired
    private AccountService accountService;

    // @PostMapping
}