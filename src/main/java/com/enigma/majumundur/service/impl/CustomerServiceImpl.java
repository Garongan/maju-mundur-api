package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.CustomerRequest;
import com.enigma.majumundur.dto.response.CustomerResponse;
import com.enigma.majumundur.entity.Customer;
import com.enigma.majumundur.mapper.CustomerResponseMapper;
import com.enigma.majumundur.repository.CustomerRepository;
import com.enigma.majumundur.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
                .point(0)
                .userAccount(request.userAccount())
                .build());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerResponse> getAllCustomer() {
        return customerRepository.findAll().stream().map(customerResponseMapper).toList();
    }

    @Override
    public Customer getCustomerById(String id) {
        return customerRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, StatusMessage.CUSTOMER_NOT_FOUND));
    }

    @Override
    public void setCustomerPoint(String id, Integer point) {
        Customer customer = getCustomerById(id);
        customer.setPoint(customer.getPoint() - point);
        customerRepository.saveAndFlush(customer);
    }
}
