package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.TransactionResponse;
import com.enigma.majumundur.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransactionResponseMapper implements Function<Transaction, TransactionResponse> {

    private final TransactionDetailResponseMapper transactionDetailResponseMapper;

    @Override
    public TransactionResponse apply(Transaction transaction) {
        long totalPrice = transaction.getTransactionDetails()
                .stream()
                .map(transactionDetail -> transactionDetail.getQuantity() * transactionDetail.getPrice())
                .mapToLong(Long::longValue).sum();
        return new TransactionResponse(
                transaction.getId(),
                transaction.getTransDate().toString(),
                ((int) (totalPrice / 1000)),
                transaction.getCustomer().getId(),
                transaction.getTransactionDetails().stream().map(transactionDetailResponseMapper).toList()
        );
    }
}
