package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateProductRequest(
        @NotBlank(message = "Product id is required")
        @NotNull(message = "Product id is required")
        String id,
        @NotBlank(message = "Product name is required")
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product price is required")
        @Min(value = 0, message = "Product price must be greater than 0")
        Long price,
        @NotNull(message = "Product stock is required")
        @Min(value = 0, message = "Product stock must be greater than 0")
        Integer stock,
        @NotBlank(message = "Merchant id is required")
        @NotNull(message = "Merchant id is required")
        String userAccountId
) {
}
