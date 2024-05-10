package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.CustomerResponse;
import com.enigma.majumundur.service.CustomerService;
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
@RequestMapping(ApiUrl.CUSTOMER)
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @Operation(summary = "Get All Customer")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<List<CustomerResponse>>> getAllCustomer() {
        List<CustomerResponse> customerResponseList = customerService.getAllCustomer();
        CommonResponse<List<CustomerResponse>> response = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS_RETRIEVE_LIST,
                customerResponseList,
                null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
