package com.pedro.salesapi.config;

import com.pedro.salesapi.security.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)
            throws Exception {

        return http

                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/auth/**")
                        .permitAll()

                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        )
                        .permitAll()

                        .requestMatchers("/admin/**")
                        .hasRole("DONO")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/produtos/**",
                                "/clientes/**"
                        )
                        .hasAnyRole("GERENTE", "DONO")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/produtos/**",
                                "/clientes/**"
                        )
                        .hasAnyRole("GERENTE", "DONO")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/produtos/**",
                                "/clientes/**"
                        )
                        .hasAnyRole("GERENTE", "DONO")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/produtos/**",
                                "/clientes/**",
                                "/pedidos/**"
                        )
                        .hasAnyRole("CLIENTE", "GERENTE", "DONO")

                        .requestMatchers(
                                HttpMethod.POST,
                                "/pedidos/**"
                        )
                        .hasAnyRole("CLIENTE", "GERENTE", "DONO")

                        .requestMatchers("/gerente/**")
                        .hasAnyRole("GERENTE", "DONO")

                        .anyRequest()
                        .authenticated()
                )

                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)

                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}