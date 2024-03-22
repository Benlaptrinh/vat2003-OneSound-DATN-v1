
package com.project.shopapp.controller;

import com.project.shopapp.entity.*;
import com.project.shopapp.repository.RoleDAO;
import com.project.shopapp.utils.UpdateUserDTO;
import com.project.shopapp.utils.localizationUtils;
import com.project.shopapp.utils.thongbao;

import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.Service.EmailService;
import com.project.shopapp.Service.PasswordResetTokenService;
import com.project.shopapp.Service.imp.AccountServiceImlp;
import com.project.shopapp.dto.MessageKeys;
import com.project.shopapp.dto.RegisterUserResponse;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.SingerDAO;
import com.project.shopapp.repository.TokenRepositoryDAO;
import com.project.shopapp.security.JwtTokenUtil;
import com.project.shopapp.utils.CheckSocialAccountResponse;
import com.project.shopapp.utils.LoginResponse;
import org.springframework.web.servlet.view.RedirectView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.time.LocalDateTime;
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
    private EmailService EmailService;

    @Autowired
    private TokenRepositoryDAO TokenRepositoryDAO;

    // @Autowired
    // private localizationUtils localizationUtils;

    @GetMapping("/login/oauth2")
    public ResponseEntity<?> loginOAuth2(
            @RequestParam("email") String email,
            @RequestParam("phone_number") String phoneNumber) {
        try {
            return ResponseEntity.ok(
                    CheckSocialAccountResponse.builder()
                            .message(this.accountService.loginByOAuth2(phoneNumber, email))
                            .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(LoginResponse.builder()
                    .message(e.getMessage())
                    .build());
        }
    }

    @GetMapping("/login/google")
    public Map<String, Object> currentUserGoogle(
            OAuth2AuthenticationToken oAuth2AuthenticationToken) {
        return oAuth2AuthenticationToken.getPrincipal().getAttributes();
    }

    @PostMapping("/feed")
    public ResponseEntity<?> hello1(@RequestBody FeedRequest request) {
        if (request.getEmail() == null || request.getContent() == null || request.getEmail().isEmpty()
                || request.getContent().isEmpty()) {
            return ResponseEntity.badRequest().body("Email and content cannot be empty");
        }

        // Set the recipient's email to the desired one
        String recipientEmail = "danghuutai2923@gmail.com";

        // Call the method with the sender's email and the fixed recipient's email
        AccountServiceImlp.sendEmailFedd(request.getEmail(), recipientEmail, request);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser1(
            @RequestBody Account Account,
            BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            if (Account.getFullname() == "" || Account.getFullname().length() <= 0) {
                if (Account.getFacebookAccountId() == 1) {
                    System.out.println(
                            "Account.getFacebookAccountId() == 1 <------------------------------------------------------------");
                } else {
                    if (Account.getGoogleAccountId() == 1) {
                        Email email = this.EmailService.getUserByEmail(Account.getEmail());

                        Account.setFullname(email.getName());
                    }
                }
            }

            if (!Account.getPassword().equals(Account.getPassword())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("k khop");
            }

            Account user = accountService.createAccount(Account);

            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);

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
                return ResponseEntity.ok(thongbao.builder().message(user.getEmail()).build());
            } else {
                return ResponseEntity.badRequest().body("Không tìm thấy mail " + mail);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/resetPassword/token/{token}")
    public ResponseEntity<?> resetPasswordForm(@PathVariable String token) {
        try {
            PasswordResetToken reset = TokenRepositoryDAO.findByToken(token);

            if (reset == null) {
                return ResponseEntity.badRequest().body("Token not found");
            }

            TokenRepositoryDAO.deleteById(reset.getId());
            return ResponseEntity.ok(thongbao.builder().message(reset.getAccount().getEmail()).build());

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update/pass/{email}")
    public ResponseEntity<?> updatepassuser(@PathVariable String email, @RequestBody UpdateUserDTO UpdateUserDTO) {
        try {

            Account updatedAccount = accountService.UpdatePassUser(email, UpdateUserDTO);
            return ResponseEntity.ok(updatedAccount);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
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

    // @GetMapping("/email")
    // public ResponseEntity<Account> getUserByEmail(@RequestParam String mail) {
    // Account account = accountService.getAccountByEmail(mail);
    // return ResponseEntity.ok(account);
    // }
    @GetMapping("/email/{email}")
    public ResponseEntity<Boolean> checkIfUserExistsByEmail(@PathVariable String email) {
        try {
            Account account = accountService.getAccountByEmail(email);
            boolean accountExists = account != null;

            return ResponseEntity.ok(accountExists);
        } catch (Exception e) {
            e.printStackTrace();
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

    @GetMapping("/users/getaccountByName/{title}")
    public Page<Account> getAlbumByTitle(@PathVariable String title, Pageable pageable) {
        return AccountDAO.findByFullnamePage(title, pageable);
    }

    @PostMapping("/checkactive")
    public ResponseEntity<?> hello1(@RequestBody UserIdDTO userIdDTO) {
        String id = userIdDTO.getEmail();
        Account a = accountService.getAccountByEmail(id);

        if (!isActiveAccount(a)) {
            System.out.println(a);

            return ResponseEntity.ok().body(a);
        } else {
            return ResponseEntity.ok().body(null);
        }
    }

    private boolean isActiveAccount(Account account) {
        return account.isActive();
    }
}
// <!-- <h2>Feedback Form</h2>
// <form (ngSubmit)="submitFeedback()" method="post">
// <label for="name">Your Name:</label>
// <input type="text" id="name" name="name" [(ngModel)]="email" required>

// <label for="reason">Your reason:</label>
// <input type="text" id="reason" name="reason" [(ngModel)]="reason" required>

// <label for="feedback">Feedback:</label>
// <textarea id="feedback" name="feedback" cols="30" rows="10"
// [(ngModel)]="feedbackContent" required></textarea>

// <button type="submit">Submit Feedback</button>
// </form> -->