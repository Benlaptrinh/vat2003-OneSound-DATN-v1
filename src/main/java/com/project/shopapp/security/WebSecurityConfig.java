
package com.project.shopapp.security;

import com.project.shopapp.Service.imp.EmailServiceImlp;
import com.project.shopapp.dto.EmailDTO;
import com.project.shopapp.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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

        // private final JwtTokenFilter jwtTokenFilter;

        // private final EmailServiceImlp EmailServiceImlp;

        // @Value("${api.prefix}")
        // private String apiPrefix;

        // @Bean
        // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
        // Exception {
        // http
        // .csrf(AbstractHttpConfigurer::disable)
        // .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
        // .authorizeHttpRequests(requests -> {
        // requests
        // // ------------------------users--------------------//
        // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // .requestMatchers(
        // String.format("%s/users/register", apiPrefix),
        // String.format("%s/users/login", apiPrefix),
        // String.format("%s/users/login/oauth2**",
        // apiPrefix))
        // .permitAll()

        // .requestMatchers(PUT,
        // String.format("%s/users/update/pass/**",
        // apiPrefix))
        // .permitAll()

        // .requestMatchers(GET,
        // String.format("%s/users**", apiPrefix))
        // .permitAll()
        // .requestMatchers(GET,
        // String.format("%s/Role**", apiPrefix))
        // .permitAll()

        // .requestMatchers(String
        // .format("%s/oauth2/login/google", apiPrefix))
        // .permitAll()
        // .requestMatchers(String.format("%s/oauth2/login/facebook",
        // apiPrefix))
        // .permitAll()

        // .requestMatchers(String.format("%s/emails/users/**",
        // apiPrefix))
        // .permitAll()
        // .requestMatchers(String.format("%s/emails/users", apiPrefix))
        // .permitAll()

        // .requestMatchers(GET,
        // String.format("%s/users/email/**", apiPrefix))
        // .permitAll()

        // // .requestMatchers(
        // // String.format("%s/users/register", apiPrefix),
        // // String.format("%s/users/login", apiPrefix),
        // // String.format("https://www.googleapis.com/youtube/v3/search/**",
        // // apiPrefix),
        // // String.format("%s/users/feed", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(PUT,
        // // String.format("%s/users/update/pass/**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/users/checkactive",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(GET,
        // // String.format("%s/users**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(GET,
        // // String.format("%s/users/email/**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(GET,
        // // String.format("%s/users/resetPassword/token/**",
        // // apiPrefix))
        // // .permitAll()
        // // .requestMatchers(GET,
        // // String.format("%s/users/forgotPassword/**",
        // // apiPrefix))
        // // .permitAll()
        // // .requestMatchers(GET,
        // // String.format("%s/users/update/pass/**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(GET,
        // // String.format("%s/users/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/users**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/users**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/users/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/users**", apiPrefix))
        // // .hasAnyRole(Role.USER)

        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------Genre--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

        // // .requestMatchers(GET,
        // // String.format("%s/Genre**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(GET,
        // // String.format("%s/Genre/**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(POST,
        // // String.format("%s/Genre**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/Genre/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/Genre/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------Author-------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

        // // .requestMatchers(GET,
        // // String.format("%s/Author**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(POST,
        // // String.format("%s/Author**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/Author/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/Author/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------Singer--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

        // // .requestMatchers(GET,
        // // String.format("%s/Singer**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/Singer**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/Singer/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/Singer/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------Role--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/Role/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(DELETE,
        // // String.format("%s/Role/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(GET,
        // // String.format("%s/Role**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(POST,
        // // String.format("%s/Role/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------album---------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/album/**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(POST,
        // // String.format("%s/album**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/album/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/album**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------SONG--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // // .requestMatchers(GET,
        // // // String.format("%s/Song/**", apiPrefix))
        // // // .permitAll()
        // // //
        // // // .requestMatchers(POST,
        // // // String.format("%s/Song/**", apiPrefix))
        // // // .hasAnyRole(Role.ADMIN)
        // // //
        // // // .requestMatchers(DELETE,
        // // // String.format("%s/Song/**", apiPrefix))
        // // // .hasAnyRole(Role.ADMIN)
        // // //
        // // // .requestMatchers(PUT,
        // // // String.format("%s/Song/**", apiPrefix))
        // // // .hasAnyRole(Role.ADMIN)

        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------SONG-GENRE--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/SongGenre/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/SongGenre**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/SongGenre/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/SongGenre**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------SONG-SINGER--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/SongSinger/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/SongSinger**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/SongSinger/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/SongSinger**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------SONG-AUTHOR--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/SongAuthor/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/SongAuthor**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/SongAuthor/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/SongAuthor**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------SINGER-ALBUM--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/singerAlbum/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/singerAlbum**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/singerAlbum/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/singerAlbum**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // //
        // // ------------------------FAVORITE-ALBUM--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/favoriteAlbum/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/favoriteAlbum**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(DELETE,
        // // String.format("%s/favoriteAlbum/**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)

        // // .requestMatchers(PUT,
        // // String.format("%s/favoriteAlbum**", apiPrefix))
        // // .hasAnyRole(Role.ADMIN)
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // //
        // // ------------------------FAVORITE-SONG--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/favoriteSong/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/favoriteSong**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(DELETE,
        // // String.format("%s/favoriteSong/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(PUT,
        // // String.format("%s/favoriteSong**", apiPrefix))
        // // .permitAll()
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // //
        // // ------------------------FAVORITE-YOUTUBE--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/favoriteYoutube/**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/favoriteYoutube**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(DELETE,
        // // String.format("%s/favoriteYoutube/**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(PUT,
        // // String.format("%s/favoriteYoutube**",
        // // apiPrefix))
        // // .permitAll()
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------PLAYLIST--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/Playlist/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/Playlist**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(DELETE,
        // // String.format("%s/Playlist/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(PUT,
        // // String.format("%s/Playlist**", apiPrefix))
        // // .permitAll()
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // //
        // // ------------------------PLAYLIST-SONG--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/PlaylistSong/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/PlaylistSong**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(DELETE,
        // // String.format("%s/PlaylistSong/**", apiPrefix))
        // // .permitAll()

        // // .requestMatchers(PUT,
        // // String.format("%s/PlaylistSong**", apiPrefix))
        // // .permitAll()
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // //
        // // ------------------------PLAYLIST-SONG--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/PlaylistYoutube/**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(POST,
        // // String.format("%s/PlaylistYoutube**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(DELETE,
        // // String.format("%s/PlaylistYoutube/**",
        // // apiPrefix))
        // // .permitAll()

        // // .requestMatchers(PUT,
        // // String.format("%s/PlaylistYoutube**",
        // // apiPrefix))
        // // .permitAll()
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------STATICTICAL--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/statictical/**", apiPrefix))
        // // .permitAll() //
        // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------VISIT--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/count/**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(POST,
        // // String.format("%s/record/**", apiPrefix))
        // // .permitAll()
        // // // ------------------------YOUTUBE--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(GET,
        // // String.format("%s/youtube/**", apiPrefix))
        // // .permitAll()
        // // .requestMatchers(POST,
        // // String.format("%s/youtube/**", apiPrefix))
        // // .permitAll()
        // // // ------------------------REST--------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
        // // .requestMatchers(POST,
        // // String.format("%s/rest/**", apiPrefix))
        // // .permitAll()
        // // // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
        // // // ------------------------album---------------------//
        // // // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//

        // .anyRequest().authenticated();

        // })
        // .csrf(AbstractHttpConfigurer::disable)
        // .oauth2Login(Customizer.withDefaults());
        // http.cors(Customizer.withDefaults());
        // http.oauth2Login(oauth2 ->
        // oauth2.successHandler(authenticationSuccessHandler()));
        // http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
        // @Override
        // public void customize(CorsConfigurer<HttpSecurity>
        // httpSecurityCorsConfigurer) {
        // CorsConfiguration configuration = new CorsConfiguration();
        // configuration.setAllowedOrigins(List.of("*"));
        // configuration.setAllowedMethods(
        // Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        // configuration.setAllowedHeaders(Arrays.asList("Origin", "Authorization",
        // "content-type",
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

        // @Bean
        // public AuthenticationSuccessHandler authenticationSuccessHandler() {
        // return (request, response, authentication) -> {
        // Long id = 0L;
        // String type = "";

        // Object principal = authentication.getPrincipal();
        // if (principal instanceof OidcUser) {
        // OidcUser oidcUser = (OidcUser) principal;
        // String name = oidcUser.getFullName();
        // String email = oidcUser.getEmail();
        // String picture = oidcUser.getPicture();
        // EmailServiceImlp.createUser(EmailDTO.builder()
        // .email(email)
        // .name(name)
        // .picture(picture)
        // .build());

        // id = this.EmailServiceImlp.getUserByEmail(email).getId();
        // type = "email";
        // }

        // if (id != 0) {
        // response.sendRedirect("http://localhost:4200/onesound/home/users/update?id="
        // + id
        // + "&type=" + type);

        // } else {
        // response.sendRedirect("http://localhost:4200");

        // }
        // };
        // }

        // }

        private final JwtTokenFilter jwtTokenFilter;
        @Value("${api.prefix}")
        private String apiPrefix;

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                                .authorizeHttpRequests(requests -> requests
                                                .anyRequest().permitAll())
                                .csrf(AbstractHttpConfigurer::disable)
                                .cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                                        @Override
                                        public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                                                CorsConfiguration configuration = new CorsConfiguration();
                                                configuration.setAllowedOrigins(List.of("*"));
                                                configuration.setAllowedMethods(
                                                                Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE",
                                                                                "OPTIONS"));
                                                configuration.setAllowedHeaders(
                                                                Arrays.asList("authorization", "content-type",
                                                                                "x-auth-token"));
                                                configuration.setExposedHeaders(List.of("x-auth-token"));
                                                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                                                source.registerCorsConfiguration("/**", configuration);
                                                httpSecurityCorsConfigurer.configurationSource(source);
                                        }
                                });

                return http.build();
        }
}