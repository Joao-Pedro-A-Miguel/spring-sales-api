package com.pedro.salesapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String auth = request.getHeader("Authorization");

        if (auth != null && auth.startsWith("Bearer ")) {

            try {

                String token = auth.substring(7);

                String email = jwtService.getEmail(token);
                String role = jwtService.getRole(token);

                var authToken =
                        new UsernamePasswordAuthenticationToken(
                                email,
                                null,
                                List.of(new SimpleGrantedAuthority(role))
                        );

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authToken);

            } catch (Exception ex) {

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

                response.setContentType("application/json");

                Map<String, String> erro = new HashMap<>();

                erro.put("erro", "Token inválido ou expirado");

                new ObjectMapper()
                        .writeValue(response.getOutputStream(), erro);

                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}