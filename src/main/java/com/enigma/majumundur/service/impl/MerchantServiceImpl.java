package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.dto.request.MerchantRequest;
import com.enigma.majumundur.dto.response.MerchantResponse;
import com.enigma.majumundur.entity.Merchant;
import com.enigma.majumundur.mapper.MerchantResponseMapper;
import com.enigma.majumundur.repository.MerchantRepository;
import com.enigma.majumundur.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final MerchantResponseMapper merchantResponseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Merchant saveMerchant(MerchantRequest request) {
        return merchantRepository.saveAndFlush(Merchant.builder()
                .shopName(request.shopName())
                .phone(request.phone())
                .address(request.address())
                .userAccount(request.userAccount())
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MerchantResponse> getAllMerchant() {
        return merchantRepository.findAll().stream().map(merchantResponseMapper).toList();
    }

    @Override
    public Merchant getMerchantById(String id) {
        return merchantRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant not found"));
    }

    @Override
    public Merchant getMerchantByUserAccountId(String userAccountId) {
        return merchantRepository.findMerchantByUserAccountId(userAccountId);
    }
}
