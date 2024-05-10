package com.enigma.majumundur.dto.request;

import com.enigma.majumundur.entity.UserAccount;

public record CustomerRequest(
        String name,
        String address,
        String phone,
        UserAccount userAccount
) {
}
