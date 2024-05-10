package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.MerchantResponse;
import com.enigma.majumundur.entity.Merchant;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class MerchantResponseMapper implements Function<Merchant, MerchantResponse> {
    @Override
    public MerchantResponse apply(Merchant merchant) {
        return new MerchantResponse(
                merchant.getId(),
                merchant.getShopName(),
                merchant.getPhone(),
                merchant.getAddress()
        );
    }
}
