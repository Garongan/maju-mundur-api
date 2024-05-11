package com.enigma.majumundur.dto.response;

import java.util.List;

public record UserAccountResponse(
        String id,
        String username,
        List<String> roles
) {
}
