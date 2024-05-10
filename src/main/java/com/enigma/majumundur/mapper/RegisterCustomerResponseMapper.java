package com.enigma.majumundur.mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.enigma.majumundur.dto.response.RegisterResponse;
import com.enigma.majumundur.entity.Customer;

@Service
public class RegisterCustomerResponseMapper implements Function<Customer, RegisterResponse> {

    @Override
    public RegisterResponse apply(Customer customer) {
        return new RegisterResponse(
                customer.getUserAccount().getUsername(),
                customer.getUserAccount().getRoles().stream().map(role -> role.getRole().name()).toList());
    }

}
