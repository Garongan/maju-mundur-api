package com.enigma.majumundur.dto.response;

public record CustomerResponse(
        String id,
        String name,
        String address,
        String phone,
        Integer pointTransaction
) {
}
