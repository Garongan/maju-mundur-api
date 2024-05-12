package com.enigma.majumundur.service;

import com.enigma.majumundur.dto.request.NewRewardRequest;
import com.enigma.majumundur.dto.request.UpdateRewardRequest;
import com.enigma.majumundur.dto.response.RewardResponse;
import com.enigma.majumundur.entity.Reward;

import java.util.List;

public interface RewardService {
    RewardResponse createReward(NewRewardRequest request);

    RewardResponse getRewardById(String id);

    Reward getOneRewardById(String id);

    List<RewardResponse> getAllRewards();

    RewardResponse updateReward(UpdateRewardRequest request);
}
