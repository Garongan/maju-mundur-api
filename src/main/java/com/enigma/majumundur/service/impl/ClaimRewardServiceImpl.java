package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.ClaimRewardRequest;
import com.enigma.majumundur.dto.response.ClaimRewardResponse;
import com.enigma.majumundur.entity.ClaimReward;
import com.enigma.majumundur.entity.Customer;
import com.enigma.majumundur.entity.Reward;
import com.enigma.majumundur.mapper.ClaimRewardResponseMapper;
import com.enigma.majumundur.repository.ClaimRewardRepository;
import com.enigma.majumundur.service.ClaimRewardService;
import com.enigma.majumundur.service.CustomerService;
import com.enigma.majumundur.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ClaimRewardServiceImpl implements ClaimRewardService {

    private final ClaimRewardRepository claimRewardRepository;
    private final ClaimRewardResponseMapper claimRewardResponseMapper;
    private final RewardService rewardService;
    private final CustomerService customerService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ClaimRewardResponse claimReward(ClaimRewardRequest request) {
        Reward reward = rewardService.getOneRewardById(request.rewardId());
        Customer customer = customerService.getCustomerById(request.customerId());

        if (customer.getPoint() < reward.getPointRequired()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, StatusMessage.CUSTOMER_POINT_NOT_ENOUGH);
        }

        customerService.setCustomerPoint(customer.getId(), reward.getPointRequired());

        ClaimReward claimReward = ClaimReward.builder()
                .claimDate(new Date(Instant.now().toEpochMilli()))
                .customer(customer)
                .reward(reward)
                .build();

        return claimRewardResponseMapper.apply(claimRewardRepository.saveAndFlush(claimReward));
    }
}
