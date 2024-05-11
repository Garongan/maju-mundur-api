package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record NewTransactionDetailRequest(

        @Min(value = 1, message = "minimum quantity is 1")
        Integer quantity,

        @Min(value = 0, message = "minimum price is 0")
        Long price,

        @NotBlank(message = "product id is required")
        String productId,

        @NotBlank(message = "reward id is required")
        String rewardId
) {
}
