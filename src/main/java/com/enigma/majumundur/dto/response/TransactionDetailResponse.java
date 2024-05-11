package com.enigma.majumundur.dto.response;

public record TransactionDetailResponse(
        String id,
        Integer quantity,
        Long price,
        String productId
        ) {
}
