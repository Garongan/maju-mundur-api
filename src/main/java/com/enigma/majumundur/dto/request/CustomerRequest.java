package com.enigma.majumundur.dto.request;

import com.enigma.majumundur.entity.UserAccount;
import jakarta.validation.constraints.NotBlank;

public record CustomerRequest(

        @NotBlank(message = "name is required")
        String name,

        @NotBlank(message = "address is required")
        String address,

        @NotBlank(message = "phone is required")
        String phone,

        @NotBlank(message = "email is required")
        UserAccount userAccount
) {
}
