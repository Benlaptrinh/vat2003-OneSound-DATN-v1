// package com.project.shopapp.security;

// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.NonNull;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.data.util.Pair;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.*;

// import com.project.shopapp.entity.Account;

// import java.io.IOException;
// import java.util.Arrays;
// import java.util.List;

// @Component
// @RequiredArgsConstructor
// public class JwtTokenFilter extends OncePerRequestFilter {
//     @Value("${api.prefix}")
//     private String apiPrefix;
//     private final UserDetailsService userDetailsService;
//     private final JwtTokenUtil jwtTokenUtil;

//     @Override
//     protected void doFilterInternal(@NonNull HttpServletRequest request,
//             @NonNull HttpServletResponse response,
//             @NonNull FilterChain filterChain)
//             throws ServletException, IOException {
//         try {
//             if (isBypassToken(request)) {
//                 filterChain.doFilter(request, response);
//                 return;
//             }
//             final String authHeader = request.getHeader("Authorization");
//             if (authHeader == null || !authHeader.startsWith("Bearer ")) {
//                 response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//                 return;
//             }
//             final String token = authHeader.substring(7);
//             final String phoneNumber = jwtTokenUtil.extractEmail(token);
//             if (phoneNumber != null
//                     && SecurityContextHolder.getContext().getAuthentication() == null) {
//                 Account userDetails = (Account) userDetailsService.loadUserByUsername(phoneNumber);
//                 if (jwtTokenUtil.validateToken(token, userDetails)) {
//                     UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                             userDetails,
//                             null,
//                             userDetails.getAuthorities());
//                     authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                     SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//                 }
//             }
//             filterChain.doFilter(request, response); // enable bypass
//         } catch (Exception e) {
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//         }

//     }

//     private boolean isBypassToken(@NonNull HttpServletRequest request) {
//         final List<Pair<String, String>> bypassTokens = Arrays.asList(
//                 Pair.of(String.format("%s/Singer", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/Singer", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/Singer/", apiPrefix), "DELETE"),
//                 Pair.of(String.format("%s/Singer/", apiPrefix), "PUT"),
//                 Pair.of(String.format("%s/Genre", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/Genre", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/Genre/", apiPrefix), "DELETE"),
//                 Pair.of(String.format("%s/Genre/", apiPrefix), "PUT"),
//                 // -----------------------Singer Album-----------------------
//                 Pair.of(String.format("%s/singerAlbum", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/singerAlbum/", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/singerAlbum/create", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/singerAlbum/", apiPrefix), "DELETE"),
//                 Pair.of(String.format("%s/singerAlbum/", apiPrefix), "PUT"),
//                 Pair.of(String.format("%s/singerAlbum/update", apiPrefix), "PUT"),

//                 // ----------------------End singer album---------------------
//                 Pair.of(String.format("%s/album", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/album", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/album/create", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/album/", apiPrefix), "DELETE"),
//                 Pair.of(String.format("%s/album/", apiPrefix), "PUT"),
//                 Pair.of(String.format("%s/album/update", apiPrefix), "PUT"),
//                 Pair.of(String.format("%s/users/register", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/users", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/users/details", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/users/login", apiPrefix), "POST"));
//         for (Pair<String, String> bypassToken : bypassTokens) {
//             if (request.getServletPath().contains(bypassToken.getFirst()) &&
//                     request.getMethod().equals(bypassToken.getSecond())) {
//                 return true;
//             }
//         }

//         return false;
//     }
// }

package com.project.shopapp.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.project.shopapp.entity.Account;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("${api.prefix}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            final String authHeader = request.getHeader("Authorization");
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                final String token = authHeader.substring(7);
                final String phoneNumber = jwtTokenUtil.extractEmail(token);
                if (phoneNumber != null
                        && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Account userDetails = (Account) userDetailsService.loadUserByUsername(phoneNumber);
                    if (jwtTokenUtil.validateToken(token, userDetails)) {
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }
}
