package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.CustomerRequest;
import com.enigma.majumundur.dto.response.CustomerResponse;
import com.enigma.majumundur.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(CustomerRequest request);

    List<CustomerResponse> getAllCustomer();

    Customer getCustomerById(String id);
}
