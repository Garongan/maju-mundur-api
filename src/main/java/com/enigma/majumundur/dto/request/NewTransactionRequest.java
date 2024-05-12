package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record NewTransactionRequest(

        @NotBlank(message = "customer account id is required")
        String customerId,

        @NotBlank(message = "transaction date is required")
        List<NewTransactionDetailRequest> details
) {
}
