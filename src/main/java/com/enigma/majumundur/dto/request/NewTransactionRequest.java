package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record NewTransactionRequest(
        @NotNull
        @Min(value = -1, message = "minimum point is 0")
        Integer point,
        @NotNull(message = "customer account id is required")
        @NotBlank(message = "customer account id is required")
        String customerAccountId,
        @NotNull(message = "transaction date is required")
        @NotBlank(message = "transaction date is required")
        List<NewTransactionDetailRequest> details
) {
}
