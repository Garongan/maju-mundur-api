package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record NewTransactionRequest(

        @Min(value = -1, message = "minimum point is 0")
        Integer point,

        @NotBlank(message = "customer account id is required")
        String customerId,

        @NotBlank(message = "transaction date is required")
        List<NewTransactionDetailRequest> details
) {
}
