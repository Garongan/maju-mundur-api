package com.enigma.majumundur.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ClaimRewardRequest(
        @NotBlank(message = "Reward id is required")
        String rewardId
) {
}
