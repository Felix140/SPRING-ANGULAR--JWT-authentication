package com.auth.jwt.backend.config;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//? Creo qui un filtro HTTP che intercetta la richiesta e valida la JWT

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

    //* CHECK autorizzazione HEADER
    String header = request.getHeader(HttpHeaders.AUTHORIZATION);
    if(header != null){

        String[] authElements = header.split(" ");

        if(authElements.length == 2 && "Bearer".equals(authElements[0])) {

            try { //* Valida il token, che corrisponderà ad authElements[1]

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(userAuthProvider.validateToken(authElements[1]));

            } catch (RuntimeException e) {

                SecurityContextHolder.clearContext();
                throw e;

            }
        }
    }
    //Alla fine continua con la FILTER CHAIN
    filterChain.doFilter(request, response);
    }
}