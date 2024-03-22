
// package com.project.shopapp.security;

// import com.project.shopapp.Service.imp.EmailServiceImlp;
// import com.project.shopapp.dto.EmailDTO;
// import com.project.shopapp.entity.Role;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.Customizer;
// import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
// import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
// import org.springframework.security.oauth2.core.oidc.user.OidcUser;
// import org.springframework.security.oauth2.core.user.OAuth2User;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.web.access.channel.ChannelProcessingFilter;
// import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

// import java.util.Arrays;
// import java.util.List;

// import static org.springframework.http.HttpMethod.*;

// @Configuration
// @EnableMethodSecurity
// @EnableWebSecurity
// @EnableGlobalMethodSecurity(prePostEnabled = true)
// @EnableWebMvc
// @RequiredArgsConstructor
// public class WebSecurityConfig {

//         private final JwtTokenFilter jwtTokenFilter;

//         private final EmailServiceImlp EmailServiceImlp;

//         @Value("${api.prefix}")
//         private String apiPrefix;

//         @Bean
//         public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

//                 http
//                                 .csrf(AbstractHttpConfigurer::disable)

//                                 .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                                 .authorizeHttpRequests(requests -> {
//                                         requests
//                                                         // ------------------------users--------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

//                                                         .requestMatchers(
//                                                                         String.format("%s/users/register", apiPrefix),
//                                                                         String.format("%s/users/login", apiPrefix),
//                                                                         String.format("%s/users/login/oauth2**",
//                                                                                         apiPrefix),
//                                                                         String.format("%s/users/login/oauth2/**",
//                                                                                         apiPrefix),
//                                                                         String.format("https://www.googleapis.com/youtube/v3/search/**",
//                                                                                         apiPrefix),
//                                                                         String.format("%s/users/feed", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(String
//                                                                         .format("%s/oauth2/login/google", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(String
//                                                                         .format("%s/users/login/oauth2/**", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/users**", apiPrefix))
//                                                         .permitAll()
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/users/email/**", apiPrefix))
//                                                         .permitAll()
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/users/resetPassword/token/**",
//                                                                                         apiPrefix))
//                                                         .permitAll()
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/users/forgotPassword/**",
//                                                                                         apiPrefix))
//                                                         .permitAll()
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/users/update/pass/**",
//                                                                                         apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/users/**", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(POST,
//                                                                         String.format("%s/users**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(DELETE,
//                                                                         String.format("%s/users**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(PUT,
//                                                                         String.format("%s/users**", apiPrefix))
//                                                         .hasAnyRole(Role.USER)

//                                                         // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                                                         // ------------------------Genre--------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Genre**", apiPrefix))
//                                                         .permitAll()
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Genre/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)
//                                                         .requestMatchers(POST,
//                                                                         String.format("%s/Genre**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(PUT,
//                                                                         String.format("%s/Genre/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(DELETE,
//                                                                         String.format("%s/Genre/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)
//                                                         // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                                                         // ------------------------Author-------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Author**", apiPrefix))
//                                                         .permitAll()
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Author/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)
//                                                         .requestMatchers(POST,
//                                                                         String.format("%s/Author**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(PUT,
//                                                                         String.format("%s/Author/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(DELETE,
//                                                                         String.format("%s/Author/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                                                         // ------------------------Singer--------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Singer**", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Singer/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(POST,
//                                                                         String.format("%s/Singer**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(PUT,
//                                                                         String.format("%s/Singer/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(DELETE,
//                                                                         String.format("%s/Singer/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                                                         // ------------------------Role--------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Role/**", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(POST,
//                                                                         String.format("%s/Role**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(DELETE,
//                                                                         String.format("%s/Role/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Role**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Role/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                                                         // ------------------------album---------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/album/**", apiPrefix))
//                                                         .permitAll()
//                                                         .requestMatchers(POST,
//                                                                         String.format("%s/album**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)
//                                                         .requestMatchers(DELETE,
//                                                                         String.format("%s/album/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/album**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/album/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                                                         // ------------------------SONG--------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Song/**", apiPrefix))
//                                                         .permitAll()

//                                                         .requestMatchers(POST,
//                                                                         String.format("%s/Song**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(DELETE,
//                                                                         String.format("%s/Song/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Song**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         .requestMatchers(GET,
//                                                                         String.format("%s/Song/**", apiPrefix))
//                                                         .hasAnyRole(Role.ADMIN)

//                                                         // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                                                         // ------------------------album---------------------//
//                                                         // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

//                                                         .anyRequest().authenticated();
//                                 })
//                                 .csrf(AbstractHttpConfigurer::disable)
//                                 .oauth2Login(Customizer.withDefaults());
//                 http.cors(Customizer.withDefaults());
//                 http.oauth2Login(oauth2 -> oauth2.successHandler(authenticationSuccessHandler()));
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

//         @Bean
//         public AuthenticationSuccessHandler authenticationSuccessHandler() {
//                 return (request, response, authentication) -> {
//                         Long id = 0L;
//                         String type = "";

//                         OidcUser oidcUser = (OidcUser) authentication.getPrincipal();
//                         String name = oidcUser.getFullName();
//                         String email = oidcUser.getEmail();
//                         String picture = oidcUser.getPicture();
//                         EmailServiceImlp.createUser(EmailDTO.builder()
//                                         .email(email)
//                                         .name(name)
//                                         .picture(picture)
//                                         .build());

//                         id = this.EmailServiceImlp.getUserByEmail(email).getId();
//                         type = "email";

//                         // Thực hiện xử lý sau khi đăng nhập thành công, ví dụ: chuyển hướng
//                         if (id != 0) {
//                                 response.sendRedirect("http://localhost:4200?id=" + id + "&type=" + type);
//                         } else {
//                                 response.sendRedirect("http://localhost:4200");
//                         }
//                 };
//         }
// }

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

                                                        .requestMatchers(String
                                                                        .format("%s/oauth2/login/google", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String.format("%s/oauth2/login/facebook",
                                                                        apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String.format("%s/emails/users/**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String.format("%s/emails/users", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String.format("%s/facebooks/users/**",
                                                                        apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String.format("%s/facebooks/users", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String
                                                                        .format("%s/users/login/oauth2/**", apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String.format("%s/users/phone_number",
                                                                        apiPrefix))
                                                        .permitAll()
                                                        .requestMatchers(String.format("%s/users/phone/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/users/update-user**",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/users/changes-password/**",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/users/details/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/roles**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/users**", apiPrefix))
                                                        .hasRole(Role.ADMIN)

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/users/get-districts/**",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/users/get-provinces",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/users/get-communes/**",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/users/upload/image",
                                                                                        apiPrefix))
                                                        .hasAnyRole(Role.ADMIN, Role.USER)

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/categories**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/categories/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/categories/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/categories/**", apiPrefix))
                                                        .permitAll()
                                                        // ---------------------------------------------------------------------------------------------------------------------------------------------------
                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/comments/create_comment/**",
                                                                                        apiPrefix))
                                                        .hasAnyRole(Role.ADMIN, Role.USER)

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/comments/get_comments_by_id/**",
                                                                                        apiPrefix))
                                                        .permitAll()
                                                        // ---------------------------------------------------------------------------------------------------------------------------------------------------

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/products**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/products/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/products/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/products/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/products/images/**",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/products/images/detail/*",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/products/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/products/by-**", apiPrefix))
                                                        .permitAll()

                                                        // ---------------------------------------------------------------------------------------------------------------------------------------------------
                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/orders/list/**", apiPrefix))
                                                        .hasAnyRole(Role.USER, Role.ADMIN)

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .hasAnyRole(Role.USER, Role.ADMIN)

                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)

                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/orders/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)

                                                        .requestMatchers(
                                                                        HttpMethod.GET,
                                                                        String.format("%s/orders/get_orders_by_user_id/**",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(
                                                                        HttpMethod.GET,
                                                                        String.format("%s/orders/get_delivered_orders/**",
                                                                                        apiPrefix))
                                                        .permitAll()

                                                        // ---------------------------------------------------------------------------------------------------------------------------------------------------

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .hasAnyRole(Role.USER)

                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)

                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/order_details/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)

                                                        // ---------------------------------------------------------------------------------------------------------------------------------------------------

                                                        .requestMatchers(HttpMethod.POST,
                                                                        String.format("%s/coupons/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)

                                                        .requestMatchers(HttpMethod.GET,
                                                                        String.format("%s/coupons/list**", apiPrefix))
                                                        .permitAll()

                                                        .requestMatchers(HttpMethod.PUT,
                                                                        String.format("%s/coupons/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)

                                                        .requestMatchers(HttpMethod.DELETE,
                                                                        String.format("%s/coupons/**", apiPrefix))
                                                        .hasAnyRole(Role.ADMIN)

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
                                configuration.setAllowedHeaders(Arrays.asList("Origin", "Authorization", "content-type",
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

                        // Thực hiện xử lý sau khi đăng nhập thành công, ví dụ: chuyển hướng
                        if (id != 0) {
                                response.sendRedirect("http://localhost:4200/onesound/home/users/update?id=" + id
                                                + "&type=" + type);

                        } else {
                                response.sendRedirect("http://localhost:4200");

                        }
                };
        }

}
