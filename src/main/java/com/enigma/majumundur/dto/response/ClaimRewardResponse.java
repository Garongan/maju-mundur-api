package com.enigma.majumundur.dto.response;

public record ClaimRewardResponse(
        String id,
        String claimDate,
        String customerId,
        String rewardId
) {
}
