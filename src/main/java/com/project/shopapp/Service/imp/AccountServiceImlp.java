
package com.project.shopapp.Service.imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.project.shopapp.utils.UpdateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.AccountService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.CountAccountDTO;
import com.project.shopapp.entity.PasswordResetToken;
import com.project.shopapp.entity.Role;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.TokenRepositoryDAO;
import com.project.shopapp.security.JwtTokenUtil;

@Service
public class AccountServiceImlp implements AccountService {
    @Override
    public Account updateAccount(Long id, UpdateUserDTO updateUserDTO) {
        Account existingUser = AccountDAO.findById(id)
                .orElseThrow();

        // Check if the phone number is being changed and if it already exists for
        // another user
        String mail = updateUserDTO.getEmail();
        if (!existingUser.getEmail().equals(mail) &&
                AccountDAO.existsByEmail(mail)) {
            throw new DataIntegrityViolationException("Email  already exists");
        }

        if (updateUserDTO.getFullName() != null) {
            existingUser.setFullname(updateUserDTO.getFullName());
        }

        if (updateUserDTO.getAddress() != null) {
            existingUser.setAddress(updateUserDTO.getAddress());
        }

        if (updateUserDTO.getCreatedDate() != null) {
            existingUser.setCreatedDate(updateUserDTO.getCreatedDate());
        }
        if (updateUserDTO.getAvatar_url() != null) {
            existingUser.setAvatar_url(updateUserDTO.getAvatar_url());
        }

        if (updateUserDTO.isGender() != existingUser.isGender()) {
            existingUser.setGender(updateUserDTO.isGender());
        }

        if (updateUserDTO.getAccountRole() != existingUser.getAccountRole()) {
            existingUser.setAccountRole(updateUserDTO.getAccountRole());
        }

        if (updateUserDTO.getPassword() != null
                && !updateUserDTO.getPassword().isEmpty()) {

            String newPassword = updateUserDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(newPassword);
            existingUser.setPassword(encodedPassword);
        }

        return AccountDAO.save(existingUser);
    }

    @Autowired
    private AccountDAO AccountDAO;

    @Override
    public Account getAccountByEmail(String email) {
        return AccountDAO.findByEmail(email).orElseThrow();
    }

    @Autowired
    private com.project.shopapp.repository.RoleDAO RoleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TokenRepositoryDAO TokenRepositoryDAO;

    @Override
    public Account createAccount(Account account) {
        if (AccountDAO.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }
        if (account.getAccountRole() == null) {
            Role userRole = RoleDAO.findById(2L).orElseThrow();
            account.setAccountRole(userRole);
        }
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        // account.setAccountRole(userRole);

        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);
        return savedAccount;
    }

    @Override
    public Account createAccountadmin(Account account) {
        if (AccountDAO.existsByEmail(account.getEmail())) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        @SuppressWarnings("null")
        Role userRole = RoleDAO.findById(account.getAccountRole().getId()).orElseThrow();
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        account.setAccountRole(userRole);
        Account savedAccount = AccountDAO.save(account);
        System.out.println(savedAccount);

        return savedAccount;
    }

    @Override
    public Account quenmk(Account account) {
        String password = account.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        account.setPassword(encodedPassword);
        Account savedAccount = AccountDAO.save(account);
        return savedAccount;
    }

    @Override
    // public Account updateAccount(Long id, Account account) {
    public Account updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = AccountDAO.findById(id).orElse(null);
        if (updatedAccount.getEmail() != existingAccount.getEmail()) {
            Account other = AccountDAO.findByEmail(updatedAccount.getEmail()).orElse(null);
            if (other != null && other.getId() != existingAccount.getId()) {
                System.err.println("Đã có tài khoản đăng ký địa chỉ email này, vui lòng chọn email khác!");
                // Update the fields of the existing account with the provided values
                existingAccount.setFullname(updatedAccount.getFullname());
                existingAccount.setActive(updatedAccount.isActive());
                existingAccount.setAddress(updatedAccount.getAddress());
                existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
                existingAccount.setGender(updatedAccount.isGender());
                existingAccount.setBirthday(updatedAccount.getBirthday());
                existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
                existingAccount.setAccountRole(updatedAccount.getAccountRole());
                existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
            } else {
                existingAccount.setFullname(updatedAccount.getFullname());
                existingAccount.setActive(updatedAccount.isActive());
                existingAccount.setAddress(updatedAccount.getAddress());
                existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
                existingAccount.setGender(updatedAccount.isGender());
                existingAccount.setBirthday(updatedAccount.getBirthday());
                existingAccount.setPhonenumber(updatedAccount.getPhonenumber());
                existingAccount.setAccountRole(updatedAccount.getAccountRole());
                existingAccount.setPassword(passwordEncoder.encode(updatedAccount.getPassword()));
                existingAccount.setEmail(updatedAccount.getEmail());
                System.err.println("Đang chạy trường hợp 2");
            }
        } else {
            existingAccount.setFullname(updatedAccount.getFullname());
            existingAccount.setActive(updatedAccount.isActive());
            existingAccount.setAddress(updatedAccount.getAddress());
            existingAccount.setAvatar_url(updatedAccount.getAvatar_url());
            existingAccount.setGender(updatedAccount.isGender());
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
    public Account updateAccountadmin(Long id, Account account) {
        Account existingAccount = AccountDAO.findById(id).orElse(null);

        if (existingAccount == null) {
            throw new IllegalArgumentException("Account not found with id: " + id);
        }

        String newEmail = account.getEmail();
        if (newEmail != null && !newEmail.equals(existingAccount.getEmail()) && AccountDAO.existsByEmail(newEmail)) {
            throw new IllegalArgumentException("An account with this email already exists.");
        }

        // Update other fields if needed
        existingAccount.setFullname(account.getFullname());
        existingAccount.setEmail(newEmail);
        existingAccount.setAddress(account.getAddress());
        existingAccount.setAvatar_url(account.getAvatar_url());
        existingAccount.setGender(account.isGender());
        existingAccount.setPhone(account.getPhone());
        existingAccount.setActive(account.isActive());
        existingAccount.setAccountRole(account.getAccountRole());

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

        Account existingUser = optionalUser.get();

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

    public String sendEmail(Account user) {
        try {
            String resetLink = generateResetToken(user);
            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setTo(user.getEmail());
            msg.setSubject("RESET PASSWORD FOR ONESOUND ACCOUNT");
            msg.setText("Hello, This is a reset password mail from ONESOUND \n\n"
                    + "Please click on this link to Reset your Password :" + resetLink + ". \n\n"
                    + "Regards \n" + "ONESOUND");

            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }

    public String generateResetToken(Account user) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);

        PasswordResetToken existingToken = TokenRepositoryDAO.findByAccount(user);
        PasswordResetToken tokenToSave;

        if (existingToken == null) {
            tokenToSave = new PasswordResetToken();
        } else {
            tokenToSave = existingToken;
        }

        tokenToSave.setAccount(user);
        tokenToSave.setToken(uuid.toString());
        tokenToSave.setExpiryDateTime(expiryDateTime);

        PasswordResetToken savedToken = TokenRepositoryDAO.save(tokenToSave);
        if (savedToken != null) {
            String endpointUrl = "http://localhost:4200/onesound/quenmk2";
            return endpointUrl + "/" + savedToken.getToken();
        }

        return "";
    }

    public boolean hasExipred(LocalDateTime expiryDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return expiryDateTime.isAfter(currentDateTime);
    }

    @Override
    public List<CountAccountDTO> countAccountByDate(int index) {
        if (index == 0) {
            return AccountDAO.countByCreatedDateDESC();
        } else {
            return AccountDAO.countByCreatedDateAsc();
        }

    }

    @Override
    public List<Account> getAllAccountByCreatedDate(Date date) {
        return AccountDAO.getAllAccountByCreatedDate(date);
    }

    @Override
    public List<CountAccountDTO> countByCreatedById(int index) {
        return AccountDAO.countByCreatedById();
    }

    @Override
    public Account UpdatePassUser(String email, UpdateUserDTO UpdateUserDTO) {
        Account existingAccount = AccountDAO.findByEmail(email).orElse(null);

        if (existingAccount != null) {

            String password = UpdateUserDTO.getPassword();
            String encodedPassword = passwordEncoder.encode(password);
            existingAccount.setPassword(encodedPassword);

            Account savedAccount = AccountDAO.save(existingAccount);

            return savedAccount;
        } else {
            throw new IllegalArgumentException("Account not found for email: " + email);
        }
    }

}
