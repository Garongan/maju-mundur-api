package com.enigma.majumundur.mapper;

import com.enigma.majumundur.dto.response.RewardResponse;
import com.enigma.majumundur.entity.Reward;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class RewardResponseMapper implements Function<Reward, RewardResponse> {
    @Override
    public RewardResponse apply(Reward reward) {
        return new RewardResponse(
                reward.getId(),
                reward.getName(),
                reward.getPointRequired(),
                reward.getStock()
        );
    }
}
