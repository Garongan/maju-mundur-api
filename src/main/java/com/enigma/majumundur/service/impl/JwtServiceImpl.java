package com.enigma.majumundur.service.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.response.JwtClaims;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.service.JwtService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    private final String JWT_SECRET;
    private final String JWT_ISSUER;
    private final Long JWT_EXPIRATION;

    public JwtServiceImpl(
            @Value("${maju-mundur-api.jwt.secret}") String JWT_SECRET,
            @Value("${maju-mundur-api.jwt.issuer}") String JWT_ISSUER,
            @Value("${maju-mundur-api.jwt.expiration}") Long JWT_EXPIRATION) {
        this.JWT_SECRET = JWT_SECRET;
        this.JWT_ISSUER = JWT_ISSUER;
        this.JWT_EXPIRATION = JWT_EXPIRATION;
    }

    @Override
    public String generateToken(UserAccount userAccount) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            return JWT.create()
                    .withSubject(userAccount.getId())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now().plusSeconds(JWT_EXPIRATION))
                    .withClaim("roles",
                            userAccount.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .withIssuer(JWT_ISSUER)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, StatusMessage.ERROR_CREATING_TOKEN);
        }
    }

    @Override
    public Boolean verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            algorithm.verify(JWT.require(algorithm).build().verify(parseToken(token)));
            return true;
        } catch (Exception e) {
            log.error("Invalid JWT Signature/Claims: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public JwtClaims getClaimsByToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            DecodedJWT decodedJWT = JWT.require(algorithm).withIssuer(JWT_ISSUER).build().verify(parseToken(token));
            return new JwtClaims(decodedJWT.getSubject(), decodedJWT.getClaim("roles").asList(String.class));
        } catch (JWTCreationException e) {
            log.error("Invalid JWT Signature/Claims: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, StatusMessage.ERROR_CLAIMS_TOKEN);
        }
    }

    private String parseToken(String token) {
        return token.replace("Bearer ", "");
    }
}
