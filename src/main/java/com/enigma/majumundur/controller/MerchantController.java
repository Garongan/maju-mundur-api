package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.MerchantResponse;
import com.enigma.majumundur.service.MerchantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrl.MERCHANT)
public class MerchantController {
    private final MerchantService merchantService;

    @Operation(summary = "Get All Merchant")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('ADMIN') and isAuthenticated()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<List<MerchantResponse>>> getAllMerchant() {
        List<MerchantResponse> merchantResponseList = merchantService.getAllMerchant();
        CommonResponse<List<MerchantResponse>> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS_RETRIEVE_LIST,
                merchantResponseList,
                null);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
