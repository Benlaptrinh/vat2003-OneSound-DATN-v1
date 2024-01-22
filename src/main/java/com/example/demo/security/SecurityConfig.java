package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class SecurityConfig {

    private final UserRepository userRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return phoneNumber -> userRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Cannot find user with phone number = " + phoneNumber));
    }
}
