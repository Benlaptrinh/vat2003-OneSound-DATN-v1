// package com.project.shopapp.security;

// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.web.access.channel.ChannelProcessingFilter;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// import com.project.shopapp.entity.Role;

// import java.util.Arrays;
// import java.util.List;

// import static org.springframework.http.HttpMethod.*;

// @Configuration
// @EnableWebSecurity(debug = true)
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableWebMvc
// @RequiredArgsConstructor
// public class WebSecurityConfig {
//         private final JwtTokenFilter jwtTokenFilter;
//         @Value("${api.prefix}")
//         private String apiPrefix;

//         @Bean
//         public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//                 http
//                                 .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                                 .authorizeHttpRequests(requests -> {
//                                         requests
//                                                         // ------------------------users--------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

//                                                         .requestMatchers(
//                                                                         String.format("%s/users/register", apiPrefix),
//                                                                         String.format("%s/users/registerfb", apiPrefix),
//                                                                         String.format("%s/users/login", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/users**", apiPrefix))
//                                                         .permitAll()

//                                                         .anyRequest().authenticated();

//                                 })

//                                 .csrf(AbstractHttpConfigurer::disable);

//                 http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
//                         @Override
//                         public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
//                                 CorsConfiguration configuration = new CorsConfiguration();
//                                 configuration.setAllowedOrigins(List.of("*"));
//                                 configuration.setAllowedMethods(
//                                                 Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//                                 configuration.setAllowedHeaders(
//                                                 Arrays.asList("authorization", "content-type", "x-auth-token"));
//                                 configuration.setExposedHeaders(List.of("x-auth-token"));
//                                 UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                                 source.registerCorsConfiguration("/**", configuration);
//                                 httpSecurityCorsConfigurer.configurationSource(source);
//                         }

//                 });

//                 return http.build();
//         }
// }

package com.project.shopapp.security;

import com.project.shopapp.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {

        private final JwtTokenFilter jwtTokenFilter;

        @Value("${api.prefix}")
        private String apiPrefix;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

                http
                                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                                .authorizeHttpRequests(requests -> {
                                        requests
                                                        // ------------------------users--------------------//
                                                        // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

                                                        .requestMatchers(
                                                                        String.format("%s/users/register", apiPrefix),
                                                                        String.format("%s/users/registerfb", apiPrefix),
                                                                        String.format("%s/users/login", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(GET,
                                                                        String.format("%s/users**", apiPrefix))
                                                        .permitAll()

                                                        .anyRequest().authenticated();
                                })
                                .csrf(AbstractHttpConfigurer::disable);
                http
                                .oauth2Login()
                                .loginPage("http://localhost:4200/onesound/signin")
                                .defaultSuccessUrl("http://localhost:4200/onesound/home", true)
                                .and()
                                .logout()
                                .invalidateHttpSession(true)
                                .deleteCookies("JSESSIONID")
                                .clearAuthentication(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/login?logout")
                                .and()
                                .exceptionHandling()
                                .accessDeniedPage("/403")
                                .and()
                                .csrf(AbstractHttpConfigurer::disable);
                http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                        @Override
                        public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                                CorsConfiguration configuration = new CorsConfiguration();
                                configuration.setAllowedOrigins(List.of("*"));
                                configuration.setAllowedMethods(
                                                Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                                configuration.setAllowedHeaders(
                                                Arrays.asList("authorization", "content-type", "x-auth-token"));
                                configuration.setExposedHeaders(List.of("x-auth-token"));
                                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                                source.registerCorsConfiguration("/**", configuration);
                                httpSecurityCorsConfigurer.configurationSource(source);
                        }
                });

                return http.build();
        }

}
