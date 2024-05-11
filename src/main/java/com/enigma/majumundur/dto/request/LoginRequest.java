package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
        @NotBlank(message = "Username is required")
        @NotNull(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        @NotNull(message = "Password is required")
        String password
) {

}
