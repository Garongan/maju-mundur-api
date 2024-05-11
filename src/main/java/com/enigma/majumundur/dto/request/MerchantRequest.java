package com.enigma.majumundur.dto.request;

import com.enigma.majumundur.entity.UserAccount;
import jakarta.validation.constraints.NotBlank;

public record MerchantRequest(
        @NotBlank(message = "Shop name is required") String shopName,
        @NotBlank(message = "Merchant phone number is required") String phone,
        @NotBlank(message = "Merchant address is required") String address,
        @NotBlank(message = "Merchant account is required") UserAccount userAccount
) {
}
