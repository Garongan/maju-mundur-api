package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.LoginRequest;
import com.enigma.majumundur.dto.request.RegisterCustomerRequest;
import com.enigma.majumundur.dto.request.RegisterMerchantRequest;
import com.enigma.majumundur.dto.response.LoginResponse;
import com.enigma.majumundur.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse registerCustomer(RegisterCustomerRequest request);

    RegisterResponse registerMerchant(RegisterMerchantRequest request);

    LoginResponse login(LoginRequest request);

    Boolean validateToken();
}
