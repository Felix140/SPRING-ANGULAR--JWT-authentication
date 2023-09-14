package com.auth.jwt.backend.config;

import com.auth.jwt.backend.dto.UserDto;
import com.auth.jwt.backend.entities.User;
import com.auth.jwt.backend.exceptions.AppException;
import com.auth.jwt.backend.mappers.UserMapper;
import com.auth.jwt.backend.repo.UserRepo;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

//? Questa classe CREA e VALIDA la JWT
@RequiredArgsConstructor
@Component
public class UserAuthProvider {

    private final UserRepo userRepo;
    private final UserMapper userMapper;

    //? Attenzione a questa annotation, non è di LOMBOK!
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    //? Servirà per evitare di avere la SECRET-KEY accessibile nella JVM
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //* CREO IL TOKEN
    public String createToken(UserDto userDto) {

        Date now = new Date();
        Date validazione = new Date(now.getTime() + 3_600_000); //* Valido per UN'ORA

        return JWT.create()
                .withIssuer(userDto.getLogin())
                .withIssuedAt(now)
                .withExpiresAt(validazione)
                .withClaim("firstName", userDto.getFirstName())
                .withClaim("lastName", userDto.getLastName())
                .sign(Algorithm.HMAC256(secretKey));
    }

    //* VALIDO E DECODIFICO IL TOKEN
    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        //creo un user DTO
        UserDto user =  UserDto.builder()
                .login(decoded.getIssuer())
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm).build();

        DecodedJWT decoded = verifier.verify(token);

        User user = userRepo.findByLogin(decoded.getIssuer())
                .orElseThrow(() -> new AppException("Utente sconosciuto", HttpStatus.NOT_FOUND));

                return new UsernamePasswordAuthenticationToken(userMapper.toUserDto(user), null, Collections.emptyList());
    }

}
