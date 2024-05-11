package com.enigma.majumundur.dto.request;

import com.enigma.majumundur.entity.UserAccount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MerchantRequest(
        @NotBlank(message = "Shop name is required")
        @NotNull(message = "Shop name is required")
        String shopName,
        @NotBlank(message = "Merchant phone number is required")
        @NotNull(message = "Merchant phone number is required")
        String phone,
        @NotBlank(message = "Merchant address is required")
        @NotNull(message = "Merchant address is required")
        String address,
        @NotBlank(message = "Merchant account is required")
        @NotNull(message = "Merchant account is required")
        UserAccount userAccount
) {
}
