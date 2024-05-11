package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterCustomerRequest(
        @NotBlank(message = "Customer name is required")
        @NotNull(message = "Customer name is required")
        String name,
        @NotBlank(message = "Customer phone number is required")
        @NotNull(message = "Customer phone number is required")
        String phone,
        @NotBlank(message = "Customer address is required")
        @NotNull(message = "Customer address is required")
        String address,
        @NotBlank(message = "Customer username is required")
        @NotNull(message = "Customer username is required")
        String username,
        @NotBlank(message = "Customer password is required")
        @NotNull(message = "Customer password is required")
        String password) {

}
