package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.NewRewardRequest;
import com.enigma.majumundur.dto.request.UpdateRewardRequest;
import com.enigma.majumundur.dto.response.RewardResponse;
import com.enigma.majumundur.entity.Reward;
import com.enigma.majumundur.mapper.RewardResponseMapper;
import com.enigma.majumundur.repository.RewardRepository;
import com.enigma.majumundur.service.RewardService;
import com.enigma.majumundur.utils.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final RewardRepository rewardRepository;
    private final RewardResponseMapper rewardResponseMapper;
    private final ValidationUtil validationUtil;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RewardResponse createReward(NewRewardRequest request) {
        validationUtil.validate(request);
        Reward reward = Reward.builder()
                .name(request.name())
                .pointRequired(request.pointRequired())
                .stock(request.stock())
                .build();
        return rewardResponseMapper.apply(rewardRepository.saveAndFlush(reward));
    }

    @Override
    public RewardResponse getRewardById(String id) {
        return rewardResponseMapper.apply(rewardRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, StatusMessage.REWARD_NOT_FOUND)));
    }

    @Override
    @Transactional(readOnly = true)
    public Reward getOneRewardById(String id) {
        return rewardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, StatusMessage.REWARD_NOT_FOUND));
    }

    @Override
    public List<RewardResponse> getAllRewards() {
        return rewardRepository.findAll().stream().map(rewardResponseMapper).toList();
    }

    @Override
    public RewardResponse updateReward(UpdateRewardRequest request) {
        validationUtil.validate(request);
        getRewardById(request.id());

        Reward reward = Reward.builder()
                .id(request.id())
                .name(request.name())
                .pointRequired(request.pointRequired())
                .stock(request.stock())
                .build();

        return rewardResponseMapper.apply(rewardRepository.saveAndFlush(reward));
    }
}
