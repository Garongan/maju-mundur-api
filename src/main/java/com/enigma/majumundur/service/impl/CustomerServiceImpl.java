package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.dto.request.CustomerRequest;
import com.enigma.majumundur.dto.response.CustomerResponse;
import com.enigma.majumundur.entity.Customer;
import com.enigma.majumundur.mapper.CustomerResponseMapper;
import com.enigma.majumundur.repository.CustomerRepository;
import com.enigma.majumundur.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerResponseMapper customerResponseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Customer saveCustomer(CustomerRequest request) {
        return customerRepository.saveAndFlush(Customer.builder()
                .name(request.name())
                .address(request.address())
                .phone(request.phone())
                .userAccount(request.userAccount())
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream().map(customerResponseMapper).toList();
    }
}
