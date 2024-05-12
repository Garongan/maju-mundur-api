package com.enigma.majumundur.dto.response;

import java.util.List;

public record TransactionResponse(
        String id,
        String transactionDate,
        Integer pointEarned,
        String customerId,
        List<TransactionDetailResponse> details
) {
}
