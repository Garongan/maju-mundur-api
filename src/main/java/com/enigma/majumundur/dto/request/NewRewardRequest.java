package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;

public record NewRewardRequest(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Point required is required")
        Integer pointRequired,

        @NotBlank(message = "Stock is required")
        Integer stock
) {
}
