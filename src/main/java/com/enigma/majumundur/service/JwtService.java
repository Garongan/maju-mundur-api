package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.response.JwtClaims;
import com.enigma.majumundur.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    Boolean verifyToken(String token);
    JwtClaims getClaimsByToken(String token);
}
