package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.RegisterResponse;
import com.enigma.majumundur.entity.Merchant;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RegisterMerchantResponseMapper implements Function<Merchant, RegisterResponse> {
    @Override
    public RegisterResponse apply(Merchant merchant) {
        return new RegisterResponse(
                merchant.getUserAccount().getUsername(),
                merchant.getUserAccount().getRoles().stream().map(role -> role.getRole().name()).toList());
    }
}
