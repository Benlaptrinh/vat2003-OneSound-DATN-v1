package com.project.shopapp.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.project.shopapp.Service.AccountService;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CountAccountDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("${api.prefix}")
public class StaticticalController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/statictical/get-user-by-date/{sort}")
    public List<CountAccountDTO> getMethodName(@PathVariable("sort") int sort) {

        return accountService.countAccountByDate(sort);

    }

    @GetMapping("/statictical/get-user-by-id/{sort}")
    public List<CountAccountDTO> CountAccountById(@PathVariable("sort") int sort) {

        return accountService.countAccountByDate(sort);

    }

    @GetMapping("/statictical/get-user-by-create-date/{date}")
    public List<Account> getAllAccountByCreatedDate(@PathVariable("date") Long d) throws ParseException {

        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Date d = dateFormat.parse(date);
        // Date date2 = new Date(date);

        Date date = new Date(d);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date);
        return accountService.getAllAccountByCreatedDate(date);

    }

}
