package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterMerchantRequest(
        @NotBlank(message = "Merchant shop name is required")
        @NotNull(message = "Merchant shop name is required")
        String shopName,
        @NotBlank(message = "Merchant phone number is required")
        @NotNull(message = "Merchant phone number is required")
        String phone,
        @NotBlank(message = "Merchant address is required")
        @NotNull(message = "Merchant address is required")
        String address,
        @NotBlank(message = "Merchant username is required")
        @NotNull(message = "Merchant username is required")
        String username,
        @NotBlank(message = "Merchant password is required")
        @NotNull(message = "Merchant password is required")
        String password) {

}
