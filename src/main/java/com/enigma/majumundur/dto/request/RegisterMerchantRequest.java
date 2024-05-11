package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterMerchantRequest(

        @NotBlank(message = "Merchant shop name is required")
        String shopName,

        @NotBlank(message = "Merchant phone number is required")
        String phone,

        @NotBlank(message = "Merchant address is required")
        String address,

        @NotBlank(message = "Merchant username is required")
        String username,

        @NotBlank(message = "Merchant password is required")
        String password) {
}
