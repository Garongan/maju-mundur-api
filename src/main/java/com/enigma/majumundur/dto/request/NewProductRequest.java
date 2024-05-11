package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewProductRequest(
        @NotBlank(message = "Product name is required")
        String name,
        @NotNull(message = "Product price is required")
        @Min(value = 0, message = "Product price must be greater than 0")
        Long price,
        @NotNull(message = "Product stock is required")
        @Min(value = 0, message = "Product stock must be greater than 0")
        Integer stock,
        @NotBlank(message = "User account id merchant is required")
        String userAccountId
) {
}
