
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
// import org.springframework.web.filter.OncePerRequestFilter;

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
//             if (this.isBypassToken(request)) {
//                 filterChain.doFilter(request, response);
//                 return;
//             }
//             final String authHeader = request.getHeader("Authorization");

//             if (authHeader != null && authHeader.startsWith("Bearer ")) {

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

//             filterChain.doFilter(request, response);
//         } catch (Exception e) {
//             response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
//         }
//     }

//     private boolean isBypassToken(@NonNull HttpServletRequest request) {
//         final List<Pair<String, String>> byPassTokens = Arrays.asList(
//                 Pair.of(String.format("%s/Role", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/Genre/Genree", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/users", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/users/register", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/oauth2/login/google", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/users/login/oauth2", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/oauth2/get/info/google", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/users/login", apiPrefix), "POST"),
//                 Pair.of(String.format("%s/users/login/google", apiPrefix), "GET"),
//                 Pair.of(String.format("https://www.googleapis.com/youtube/v3/search/**", apiPrefix), "GET"),
//                 Pair.of(String.format("%s/users/feed", apiPrefix), "POST"));

//         String requestPath = request.getServletPath();
//         String requestMethod = request.getMethod();
//         if (requestPath.equals(
//                 String.format("%s/oauth2/login/google", apiPrefix))
//                 && requestMethod.equals("GET")) {
//             return true;
//         }

//         if (requestPath.equals("/login")
//                 && requestMethod.equals("GET")) {
//             // Allow access to %s/products
//             return true;
//         }

//         if (requestPath.contains(apiPrefix + "/emails/users")
//                 && requestMethod.equals("GET")) {
//             // Allow access to %s/product_images
//             return true;
//         }

//         if (requestPath.contains(apiPrefix + "/users/login/oauth2")
//                 && requestMethod.equals("POST")) {
//             // Allow access to %s/product_images
//             return true;
//         }
//         for (Pair<String, String> bypassToken : byPassTokens) {
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
import org.modelmapper.internal.Pair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.*;

import com.project.shopapp.entity.Account;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {
    @Value("/${api.prefix}")
    private String apiPrefix;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        try {
            if (this.isByPassToken(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            final String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
                return;
            }
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
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private boolean isByPassToken(@NonNull HttpServletRequest request) {
        final List<Pair<String, String>> byPassTokens = Arrays.asList(
                Pair.of(String.format("%s/roles", apiPrefix), "GET"),
                Pair.of(String.format("%s/users/login", apiPrefix), "POST"),
                Pair.of(String.format("%s/users/phone_number", apiPrefix), "GET"),
                Pair.of(String.format("%s/users/login/oauth2", apiPrefix), "POST"),
                Pair.of(String.format("%s/users/register", apiPrefix), "POST"),
                Pair.of(String.format("%s/oauth2/login/google", apiPrefix), "GET"),
                Pair.of(String.format("%s/oauth2/login/facebook", apiPrefix), "GET"),
                Pair.of(String.format("%s/users/login/google", apiPrefix), "GET"),
                Pair.of(String.format("%s/oauth2/get/info/google", apiPrefix), "GET"));

        String requestPath = request.getServletPath();
        String requestMethod = request.getMethod();

        if (requestPath.equals(String.format("%s/orders", apiPrefix))
                && requestMethod.equals("GET")) {
            // Allow access to %s/orders
            return true;
        }
        if (requestPath.equals(
                String.format("%s/oauth2/login/google", apiPrefix))
                && requestMethod.equals("GET")) {
            return true;
        }
        if (requestPath.equals(String.format("%s/categories", apiPrefix))
                && requestMethod.equals("GET")) {
            // Allow access to %s/categories
            return true;
        }
        if (requestPath.equals(String.format("%s/products", apiPrefix))
                && requestMethod.equals("GET")) {
            // Allow access to %s/products
            return true;
        }
        if (requestPath.equals("/login")
                && requestMethod.equals("GET")) {
            // Allow access to %s/products
            return true;
        }
        if (requestPath.equals(String.format("%s/users/get-provinces", apiPrefix))
                && requestMethod.equals("GET")) {
            return true;
        }
        if (requestPath.contains(String.format("%s/vnpay/payment-callback", apiPrefix))
                && requestMethod.equals("GET")) {
            return true;
        }
        if (requestPath.contains(String.format("%s/orders/get_delivered_orders/", apiPrefix))
                && requestMethod.equals("GET")) {
            return true;
        }
        if (requestPath.contains(String.format("%s/users/get-districts/", apiPrefix))
                && requestMethod.equals("GET")) {
            return true;
        }
        if (requestPath.contains(String.format("%s/users/get-communes/", apiPrefix))
                && requestMethod.equals("GET")) {
            return true;
        }
        if (requestPath.contains(apiPrefix + "/products/images/")
                && requestMethod.equals("GET")) {
            // Allow access to %s/product_images
            return true;
        }
        if (requestPath.contains(apiPrefix + "/emails/users")
                && requestMethod.equals("GET")) {
            // Allow access to %s/product_images
            return true;
        }
        if (requestPath.contains(apiPrefix + "/facebooks/users")
                && requestMethod.equals("GET")) {
            // Allow access to %s/product_images
            return true;
        }
        if (requestPath.contains(apiPrefix + "/users/phone")
                && requestMethod.equals("GET")) {
            // Allow access to %s/product_images
            return true;
        }
        if (requestPath.contains(apiPrefix + "/users/login/oauth2")
                && requestMethod.equals("POST")) {
            // Allow access to %s/product_images
            return true;
        }

        for (Pair<String, String> bypassToken : byPassTokens) {
            if (requestPath.contains(bypassToken.getLeft())
                    && requestMethod.equals(bypassToken.getRight())) {
                return true;
            }
        }
        return false;
    }
}
