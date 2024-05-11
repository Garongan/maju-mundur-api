package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.NewTransactionRequest;
import com.enigma.majumundur.dto.response.TransactionResponse;

import java.util.List;

public interface TransactionService {
    TransactionResponse createTransaction(NewTransactionRequest request);
    List<TransactionResponse> getAllTransactions();
}
