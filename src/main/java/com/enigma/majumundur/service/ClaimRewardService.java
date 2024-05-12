package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.ClaimRewardRequest;
import com.enigma.majumundur.dto.response.ClaimRewardResponse;

public interface ClaimRewardService {
    ClaimRewardResponse claimReward(ClaimRewardRequest request);
}
