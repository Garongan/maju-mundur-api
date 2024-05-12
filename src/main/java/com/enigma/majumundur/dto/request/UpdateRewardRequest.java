package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateRewardRequest(

        @NotBlank(message = "id is required")
        String id,

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "pointRequired is required")
        Integer pointRequired,

        @NotBlank(message = "stock is required")
        Integer stock
) {
}
