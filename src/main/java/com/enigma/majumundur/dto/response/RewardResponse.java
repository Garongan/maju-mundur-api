package com.enigma.majumundur.dto.response;

public record RewardResponse(
        String id,
        String name,
        Integer pointRequired,
        Integer stock
) {
}
