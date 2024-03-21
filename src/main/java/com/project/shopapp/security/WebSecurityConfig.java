
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
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
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
//
//    private final JwtTokenFilter jwtTokenFilter;
//
//    @Value("${api.prefix}")
//    private String apiPrefix;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http
//                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
//                .authorizeHttpRequests(requests -> {
//                    requests
//                            // ------------------------users--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//
//                            .requestMatchers(
//                                    String.format("%s/users/register", apiPrefix),
//                                    String.format("%s/users/login", apiPrefix),
//                                    String.format("https://www.googleapis.com/youtube/v3/search/**", apiPrefix),
//                                    String.format("%s/users/feed", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(GET,
//                                    String.format("%s/users**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(GET,
//                                    String.format("%s/users/email/**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(GET,
//                                    String.format("%s/users/resetPassword/token/**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(GET,
//                                    String.format("%s/users/forgotPassword/**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(GET,
//                                    String.format("%s/users/update/pass/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(GET,
//                                    String.format("%s/users/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/users**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/users**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/users**", apiPrefix))
//                            .hasAnyRole(Role.USER)
//
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------Genre--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//
//                            .requestMatchers(GET,
//                                    String.format("%s/Genre**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(GET,
//                                    String.format("%s/Genre/**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/Genre**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/Genre/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/Genre/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------Author-------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//
//                            .requestMatchers(GET,
//                                    String.format("%s/Author**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/Author**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/Author/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/Author/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------Singer--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//
//                            .requestMatchers(GET,
//                                    String.format("%s/Singer**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/Singer**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/Singer/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/Singer/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------Role--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/Role/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/Role/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(GET,
//                                    String.format("%s/Role**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(POST,
//                                    String.format("%s/Role/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------album---------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/album/**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/album**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/album/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/album**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------SONG--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
////                            .requestMatchers(GET,
////                                    String.format("%s/Song/**", apiPrefix))
////                            .permitAll()
////
////                            .requestMatchers(POST,
////                                    String.format("%s/Song/**", apiPrefix))
////                            .hasAnyRole(Role.ADMIN)
////
////                            .requestMatchers(DELETE,
////                                    String.format("%s/Song/**", apiPrefix))
////                            .hasAnyRole(Role.ADMIN)
////
////                            .requestMatchers(PUT,
////                                    String.format("%s/Song/**", apiPrefix))
////                            .hasAnyRole(Role.ADMIN)
//
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------SONG-GENRE--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/SongGenre/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/SongGenre**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/SongGenre/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/SongGenre**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------SONG-SINGER--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/SongSinger/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/SongSinger**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/SongSinger/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/SongSinger**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------SONG-AUTHOR--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/SongAuthor/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/SongAuthor**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/SongAuthor/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/SongAuthor**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------SINGER-ALBUM--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/singerAlbum/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/singerAlbum**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/singerAlbum/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/singerAlbum**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------FAVORITE-ALBUM--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/favoriteAlbum/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/favoriteAlbum**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/favoriteAlbum/**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/favoriteAlbum**", apiPrefix))
//                            .hasAnyRole(Role.ADMIN)
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------FAVORITE-SONG--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/favoriteSong/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/favoriteSong**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/favoriteSong/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/favoriteSong**", apiPrefix))
//                            .permitAll()
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------FAVORITE-YOUTUBE--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/favoriteYoutube/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/favoriteYoutube**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/favoriteYoutube/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/favoriteYoutube**", apiPrefix))
//                            .permitAll()
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------PLAYLIST--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/Playlist/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/Playlist**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/Playlist/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/Playlist**", apiPrefix))
//                            .permitAll()
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------PLAYLIST-SONG--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/PlaylistSong/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/PlaylistSong**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/PlaylistSong/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/PlaylistSong**", apiPrefix))
//                            .permitAll()
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------PLAYLIST-SONG--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/PlaylistYoutube/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(POST,
//                                    String.format("%s/PlaylistYoutube**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(DELETE,
//                                    String.format("%s/PlaylistYoutube/**", apiPrefix))
//                            .permitAll()
//
//                            .requestMatchers(PUT,
//                                    String.format("%s/PlaylistYoutube**", apiPrefix))
//                            .permitAll()
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------STATICTICAL--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/statictical/**", apiPrefix))
//                            .permitAll()                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------VISIT--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/count/**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/record/**", apiPrefix))
//                            .permitAll()
//                            // ------------------------YOUTUBE--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(GET,
//                                    String.format("%s/youtube/**", apiPrefix))
//                            .permitAll()
//                            .requestMatchers(POST,
//                                    String.format("%s/youtube/**", apiPrefix))
//                            .permitAll()
//                            // ------------------------REST--------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//                            .requestMatchers(POST,
//                                    String.format("%s/rest/**", apiPrefix))
//                            .permitAll()
//                            // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^//
//                            // ------------------------album---------------------//
//                            // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv//
//
//                            .anyRequest().authenticated();
//                })
//                .csrf(AbstractHttpConfigurer::disable);
//        http
//                .oauth2Login()
//                .loginPage("http://localhost:4200/onesound/signin")
////                .defaultSuccessUrl("http://localhost:4200/onesound/home", true)
//                .defaultSuccessUrl("/api/v1/users/oauth2/login/success", true)
//                .failureUrl("/auth/login/error")
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .and()
//                .exceptionHandling()
//                .accessDeniedPage("/403")
//                .and()
//                .csrf(AbstractHttpConfigurer::disable);
//        http.cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
//                CorsConfiguration configuration = new CorsConfiguration();
//                configuration.setAllowedOrigins(List.of("*"));
//                configuration.setAllowedMethods(
//                        Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
//                configuration.setAllowedHeaders(
//                        Arrays.asList("authorization", "content-type", "x-auth-token"));
//                configuration.setExposedHeaders(List.of("x-auth-token"));
//                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//                source.registerCorsConfiguration("/**", configuration);
//                httpSecurityCorsConfigurer.configurationSource(source);
//            }
//        });
//
//        return http.build();
//    }
//
//}


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