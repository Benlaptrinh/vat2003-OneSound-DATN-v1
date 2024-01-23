package com.example.demo.security;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {

        @Value("${api.prefix}")
        private String apiPrefix;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(requests -> {
                                        requests
                                                        .requestMatchers(
                                                                        String.format("%s/users/register", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(GET,
                                                                        String.format("%s/Role**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(POST,
                                                                        String.format("%s/Singer**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(GET,
                                                                        String.format("%s/Genre**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(GET,
                                                                        String.format("%s/Singer/**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(GET,
                                                                        String.format("%s/Role/**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(POST,
                                                                        String.format("%s/Role**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(DELETE,
                                                                        String.format("%s/Role/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(GET,
                                                                        String.format("%s/users/**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(GET,
                                                                        String.format("%s/users**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(POST,
                                                                        String.format("%s/users**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(POST,
                                                                        String.format("%s/users/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(PUT,
                                                                        String.format("%s/users**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(PUT,
                                                                        String.format("%s/users/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(DELETE,
                                                                        String.format("%s/users/**", apiPrefix))
                                                        .permitAll()

                                                        .anyRequest().authenticated();
                                })

                ;
                return http.build();
        }

}
