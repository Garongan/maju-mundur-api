package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.CustomerResponse;
import com.enigma.majumundur.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CustomerResponseMapper implements Function<Customer, CustomerResponse> {
    @Override
    public CustomerResponse apply(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getPhone(),
                customer.getPoint()
        );
    }
}
