package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.LoginRequest;
import com.enigma.majumundur.dto.request.RegisterCustomerRequest;
import com.enigma.majumundur.dto.request.RegisterMerchantRequest;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.LoginResponse;
import com.enigma.majumundur.dto.response.RegisterResponse;
import com.enigma.majumundur.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiUrl.AUTH)
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Login")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(
            path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        CommonResponse<LoginResponse> commonResponse = new CommonResponse<>(HttpStatus.OK.value(), StatusMessage.SUCCESS, loginResponse, null);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Operation(summary = "RegisterCustomer")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(
            path = "/register-customer",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<RegisterResponse>> registerCustomer(@RequestBody RegisterCustomerRequest request) {
        RegisterResponse registerResponse = authService.registerCustomer(request);
        CommonResponse<RegisterResponse> response = new CommonResponse<>(
                HttpStatus.CREATED.value(),
                StatusMessage.SUCCESS_CREATED,
                registerResponse,
                null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "RegisterMerchant")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(
            path = "/register-merchant",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<RegisterResponse>> registerMerchant(@RequestBody RegisterMerchantRequest request) {
        RegisterResponse registerResponse = authService.registerMerchant(request);
        CommonResponse<RegisterResponse> response = new CommonResponse<>(
                HttpStatus.CREATED.value(),
                StatusMessage.SUCCESS_CREATED,
                registerResponse,
                null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
