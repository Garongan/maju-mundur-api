package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.NewTransactionRequest;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.TransactionResponse;
import com.enigma.majumundur.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrl.TRANSACTION)
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Create new transaction")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('CUSTOMER') and isAuthenticated()")
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<TransactionResponse>> createNewTransaction(NewTransactionRequest request){
        TransactionResponse transactionResponse = transactionService.createTransaction(request);
        CommonResponse<TransactionResponse> commonResponse = new CommonResponse<>(
                HttpStatus.CREATED.value(),
                StatusMessage.SUCCESS_CREATED,
                transactionResponse,
                null
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @Operation(summary = "Get all transactions")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('CUSTOMER') and isAuthenticated()")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<List<TransactionResponse>>> getAllTransactions(){
        List<TransactionResponse> transactionResponseList = transactionService.getAllTransactions();
        CommonResponse<List<TransactionResponse>> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS,
                transactionResponseList,
                null
        );
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Operation(summary = "Get Merchant History Transaction")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('MERCHANT') and isAuthenticated()")
    @GetMapping(value = "/history", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CommonResponse<List<TransactionResponse>>> getHistoryTransaction() {
        List<TransactionResponse> transactionResponseList = transactionService.getMerchantHistoryTransaction();
        CommonResponse<List<TransactionResponse>> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS_RETRIEVE_LIST,
                transactionResponseList,
                null);
        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
