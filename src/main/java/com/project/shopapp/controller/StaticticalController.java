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
import com.project.shopapp.entity.ReportAccountByYear;
import com.project.shopapp.repository.AccountDAO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("${api.prefix}")
public class StaticticalController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountDAO accountDAO;

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

    @GetMapping("/statictical/get-user-by-create-date/{date1}/{date2}")
    public List<Account> getAllAccountBetweenCreatedDate(@PathVariable("date2") Long d1, @PathVariable("date2") Long d2)
            throws ParseException {

        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // Date d = dateFormat.parse(date);
        // Date date2 = new Date(date);

        Date date1 = new Date(d1);
        Date date2 = new Date(d1);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(date1);
        return accountService.getAllAccountBetweenCreatedDate(date1, date2);

    }

    @GetMapping("/statictical/get-count-user-by-create-date/{date1}/{date2}")
    public List<CountAccountDTO> getAllAccountByBetweenCreatedDate(@PathVariable("date1") Long d1,
            @PathVariable("date2") Long d2) throws ParseException {

        Date date1 = new Date(d1);
        Date date2 = new Date(d2);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return accountService.countAccountBetweenByDate(date1, date2);

    }

    @GetMapping("/statictical/get-user-by-month-of-create-date/{month}")
    public List<Account> getCountUserByMonth(@PathVariable("month") Integer month) {
        return accountService.countByCreatedByMonthOfCreateDate(month);
    }

    @GetMapping("/statictical/get-user-by-day-of-create-date/{day}")
    public List<Account> getCountUserByDay(@PathVariable("month") Integer day) {
        return accountService.countByCreatedByDayOfCreateDate(day);
    }

    @GetMapping("/statictical/get-user-by-year-of-create-date/{year}")
    public List<Account> getCountUserByYear(@PathVariable("month") Integer year) {
        return accountService.countByCreatedByYearOfCreateDate(year);
    }

    @GetMapping("/statictical/get-user-by-option-date/{day}/{month}/{year}")
    public List<Account> getUserByOptionDate(@PathVariable("day") Integer day, @PathVariable("month") Integer month,
            @PathVariable("year") Integer year) {
        System.out.println("-----------------------" + year);
        if (day != null && day == -1) {
            day = null;
        }
        if (month != null && month == -1) {
            month = null;
        }
        if (year != null && year == -1) {
            year = null;
        }
        return accountService.getUserByOptionDate(day, month, year);
    }

    @GetMapping("/statictical/get-count-user-by-year/{year}")
    public List<ReportAccountByYear> getMethodName(@PathVariable("year") Integer year) {
        List<Object[]> reportList = new ArrayList<>();
        List<ReportAccountByYear> rpList = new ArrayList<>();
        reportList = accountDAO.getCountAccountByYear(year);
        // for (ReportAccountByYear reportAccountByYear : reportList) {
        // System.out.println(reportAccountByYear.getMonth());
        // }

        for (Object[] obj : reportList) {
            Long count = Long.parseLong(String.valueOf(obj[0])); // Ép kiểu và chuyển đổi sang Long
            Integer month = (Integer) obj[1];

            ReportAccountByYear rp = new ReportAccountByYear();
            rp.setCount(count);
            rp.setMonth(month);

            rpList.add(rp);
        }
        return rpList;

    }

    @GetMapping("/statictical/get-month")
    public List<ReportAccountByYear> getMonth() {

        return accountDAO.getMonthOfCreateDate();
    }

}
