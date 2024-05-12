package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.ClaimRewardResponse;
import com.enigma.majumundur.entity.ClaimReward;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class ClaimRewardResponseMapper implements Function<ClaimReward, ClaimRewardResponse> {
    @Override
    public ClaimRewardResponse apply(ClaimReward claimReward) {
        return new ClaimRewardResponse(
                claimReward.getId(),
                claimReward.getClaimDate().toString(),
                claimReward.getCustomer().getId(),
                claimReward.getReward().getId()
        );
    }
}
