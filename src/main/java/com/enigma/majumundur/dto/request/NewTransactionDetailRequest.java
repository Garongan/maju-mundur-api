package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record NewTransactionDetailRequest(
        @NotNull(message = "quantity is required")
        @Min(value = 1, message = "minimum quantity is 1")
        Integer quantity,
        @NotNull(message = "price is required")
        @Min(value = 0, message = "minimum price is 0")
        Long price,
        String productId,
        String rewardId
) {
}
