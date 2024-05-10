package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterMerchantRequest(
        @NotBlank(message = "Shop name is required") String shopName,
        @NotBlank(message = "Phone number is required") String phone,
        @NotBlank(message = "Address is required") String address,
        @NotBlank(message = "Username is required") String username,
        @NotBlank(message = "Password is required") String password) {

}
