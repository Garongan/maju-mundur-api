package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.TransactionDetailResponse;
import com.enigma.majumundur.entity.TransactionDetail;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TransactionDetailResponseMapper implements Function<TransactionDetail, TransactionDetailResponse> {
    @Override
    public TransactionDetailResponse apply(TransactionDetail transactionDetail) {
        return new TransactionDetailResponse(
                transactionDetail.getId(),
                transactionDetail.getQuantity(),
                transactionDetail.getPrice(),
                transactionDetail.getProduct().getId()
        );
    }
}
