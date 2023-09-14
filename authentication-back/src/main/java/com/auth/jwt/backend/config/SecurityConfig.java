package com.auth.jwt.backend.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

//? Qui si configura SPRING SECURITY
@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //* Creo la SECURITY FILTER CHAIN
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecure) throws Exception {

        //?Disabilito il CSRF
        httpSecure.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(costumizer -> costumizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //* qui indico di trovarmi in una STATELESS APP
                .authorizeHttpRequests((requests)->requests.requestMatchers(HttpMethod.POST, "/login", "/register").permitAll() //* rendo pubblico l'endpoint "/login
                        .anyRequest().authenticated() //* Tutti gli altri endpoint sono protetti da Autenticazione
                );

        return httpSecure.build();
    }
}
