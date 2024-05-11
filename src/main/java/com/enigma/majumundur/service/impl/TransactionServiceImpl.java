package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.dto.request.NewTransactionRequest;
import com.enigma.majumundur.dto.response.TransactionResponse;
import com.enigma.majumundur.entity.Customer;
import com.enigma.majumundur.entity.Transaction;
import com.enigma.majumundur.entity.TransactionDetail;
import com.enigma.majumundur.mapper.TransactionDetailMapper;
import com.enigma.majumundur.mapper.TransactionResponseMapper;
import com.enigma.majumundur.repository.TransactionRepository;
import com.enigma.majumundur.service.CustomerService;
import com.enigma.majumundur.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final CustomerService customerService;
    private final TransactionDetailMapper transactionDetailMapper;
    private final TransactionResponseMapper transactionResponseMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public TransactionResponse createTransaction(NewTransactionRequest request) {
        Customer customer = customerService.getCustomerById(request.customerId());

        List<TransactionDetail> transactionDetails = request.details().stream().map(transactionDetailMapper).toList();

        long totalPrice = transactionDetails
                .stream()
                .map(transactionDetail -> transactionDetail.getQuantity() * transactionDetail.getPrice())
                .mapToLong(Long::longValue).sum();

        customer.setPoint(customer.getPoint() + (int) totalPrice / 1000);

        Transaction transaction = Transaction.builder()
                .transDate(new Date(Instant.now().toEpochMilli()))
                .customer(customer)
                .transactionDetails(transactionDetails)
                .build();
        transaction.setTransactionDetails(transactionDetails.stream().peek(transactionDetail -> transactionDetail.setTransaction(transaction)).toList());

        return transactionResponseMapper.apply(transactionRepository.saveAndFlush(transaction));
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponse> getAllTransactions() {
        return transactionRepository.findAll().stream().map(transactionResponseMapper).toList();
    }
}
