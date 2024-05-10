package com.enigma.majumundur.dto.response;

import java.util.List;

public record JwtClaims(String userAccountId, List<String> roles) {
}
