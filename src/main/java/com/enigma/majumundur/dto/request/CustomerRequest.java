package com.enigma.majumundur.dto.request;

import com.enigma.majumundur.entity.UserAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        @NotNull(message = "name is required")
        @NotBlank(message = "name is required")
        String name,
        @NotNull(message = "address is required")
        @NotBlank(message = "address is required")
        String address,
        @NotNull(message = "phone is required")
        @NotBlank(message = "phone is required")
        String phone,
        @NotBlank(message = "email is required")
        @NotNull(message = "email is required")
        UserAccount userAccount
) {
}
