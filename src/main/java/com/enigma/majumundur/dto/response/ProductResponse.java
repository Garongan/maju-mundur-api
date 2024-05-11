package com.enigma.majumundur.dto.response;

public record ProductResponse(
        String id,
        String name,
        Long price,
        Integer stock,
        MerchantResponse merchant
) {
}
