
package com.project.shopapp.security;

import lombok.RequiredArgsConstructor;
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.project.shopapp.Service.imp.EmailServiceImlp;
import com.project.shopapp.dto.EmailDTO;
import com.project.shopapp.entity.Role;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    private final EmailServiceImlp EmailServiceImlp;
    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests

                            .requestMatchers(
                                    String.format("%s/users/register", apiPrefix),
                                    String.format("%s/users/login", apiPrefix),
                                    String.format("%s/users/login/oauth2**",
                                            apiPrefix))
                            .permitAll()

                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/users**", apiPrefix))
                            .permitAll()
                            .requestMatchers(HttpMethod.GET,
                                    String.format("%s/Role**", apiPrefix))
                            .permitAll()

                            .requestMatchers(String
                                    .format("%s/oauth2/login/google", apiPrefix))
                            .permitAll()
                            .requestMatchers(String.format("%s/oauth2/login/facebook",
                                    apiPrefix))
                            .permitAll()

                            .requestMatchers(String.format("%s/emails/users/**",
                                    apiPrefix))
                            .permitAll()
                            .requestMatchers(String.format("%s/emails/users", apiPrefix))
                            .permitAll()

                            .anyRequest().authenticated();

                })
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(Customizer.withDefaults());
        http.cors(Customizer.withDefaults());
        http.oauth2Login(oauth2 -> oauth2.successHandler(authenticationSuccessHandler()));
        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
            @Override
            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                CorsConfiguration configuration = new CorsConfiguration();
                configuration.setAllowedOrigins(List.of("*"));
                configuration.setAllowedMethods(
                        Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                configuration.setAllowedHeaders(Arrays.asList("Origin", "Authorization",
                        "content-type",
                        "x-auth-token"));
                configuration.setExposedHeaders(List.of("x-auth-token"));
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                source.registerCorsConfiguration("/**", configuration);
                httpSecurityCorsConfigurer.configurationSource(source);
            }
        });
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return (request, response, authentication) -> {
            Long id = 0L;
            String type = "";

            Object principal = authentication.getPrincipal();
            if (principal instanceof OidcUser) {
                OidcUser oidcUser = (OidcUser) principal;
                String name = oidcUser.getFullName();
                String email = oidcUser.getEmail();
                String picture = oidcUser.getPicture();
                EmailServiceImlp.createUser(EmailDTO.builder()
                        .email(email)
                        .name(name)
                        .picture(picture)
                        .build());

                id = this.EmailServiceImlp.getUserByEmail(email).getId();
                type = "email";
            }

            if (id != 0) {
                response.sendRedirect("http://localhost:4200/onesound/home/users/update?id="
                        + id
                        + "&type=" + type);

            } else {
                response.sendRedirect("http://localhost:4200");

            }
        };
    }

}

// package com.project.shopapp.security;

// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import
// org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import
// org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
// import org.springframework.security.web.SecurityFilterChain;
// import
// org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import
// org.springframework.security.web.access.channel.ChannelProcessingFilter;
// import
// org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// import java.util.Arrays;
// import java.util.List;

// import static org.springframework.http.HttpMethod.*;

// @Configuration
// @EnableWebSecurity(debug = true)
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableWebMvc
// @RequiredArgsConstructor
// public class WebSecurityConfig {
// private final JwtTokenFilter jwtTokenFilter;
// @Value("${api.prefix}")
// private String apiPrefix;

// @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
// Exception {
// http
// .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
// .authorizeHttpRequests(requests -> requests
// .anyRequest().permitAll())
// .csrf(AbstractHttpConfigurer::disable)
// .cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
// @Override
// public void customize(CorsConfigurer<HttpSecurity>
// httpSecurityCorsConfigurer) {
// CorsConfiguration configuration = new CorsConfiguration();
// configuration.setAllowedOrigins(List.of("*"));
// configuration.setAllowedMethods(
// Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE",
// "OPTIONS"));
// configuration.setAllowedHeaders(
// Arrays.asList("authorization", "content-type",
// "x-auth-token"));
// configuration.setExposedHeaders(List.of("x-auth-token"));
// UrlBasedCorsConfigurationSource source = new
// UrlBasedCorsConfigurationSource();
// source.registerCorsConfiguration("/**", configuration);
// httpSecurityCorsConfigurer.configurationSource(source);
// }
// });

// return http.build();
// }
// }