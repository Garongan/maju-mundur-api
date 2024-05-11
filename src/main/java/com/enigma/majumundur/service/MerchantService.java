package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.MerchantRequest;
import com.enigma.majumundur.dto.response.MerchantResponse;
import com.enigma.majumundur.entity.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant saveMerchant(MerchantRequest request);

    List<MerchantResponse> getAllMerchant();

    Merchant getMerchantById(String id);

    Merchant getMerchantByUserAccountId(String userAccountId);
}
