// package com.project.shopapp.controller;

// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.BindingResult;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.project.shopapp.entity.Account;

// import jakarta.validation.Valid;

// @CrossOrigin(origins = "http://localhost:4200")
// @RestController
// @RequestMapping("${api.prefix}")
// public class test {

//     @PostMapping("/testdangnhap")
//     public ResponseEntity<?> createUser() {
//         if (result.hasErrors()) {
//             Map<String, String> errors = new HashMap<>();
//             for (FieldError error : result.getFieldErrors()) {
//                 errors.put(error.getField(), error.getDefaultMessage());
//             }
//             return ResponseEntity.badRequest().body(errors);
//         }
//         try {
//             accountService.createAccount(Account);
//             return ResponseEntity.ok().build();
//         } catch (Exception e) {
//             return ResponseEntity.badRequest().body(e.getMessage());
//         }
//     }
// }
