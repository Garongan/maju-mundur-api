package com.enigma.majumundur.dto.response;

import java.util.Date;
import java.util.List;

public record TransactionResponse(
        String id,
        Date transactionDate,
        Integer point,
        String customerId,
        List<TransactionDetailResponse> details
) {
}
