package com.enigma.majumundur.dto.request;

import com.enigma.majumundur.entity.UserAccount;

public record MerchantRequest(
        String shopName,
        String phone,
        String address,
        UserAccount userAccount
) {
}
