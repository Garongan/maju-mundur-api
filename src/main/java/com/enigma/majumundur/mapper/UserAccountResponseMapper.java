package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.UserAccountResponse;
import com.enigma.majumundur.entity.UserAccount;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserAccountResponseMapper implements Function<UserAccount, UserAccountResponse> {
    @Override
    public UserAccountResponse apply(UserAccount account) {
        return new UserAccountResponse(
                account.getId(),
                account.getUsername(),
                account.getRoles().stream().map(role -> role.getRole().name()).toList()
        );
    }
}
