package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.dto.request.ClaimRewardRequest;
import com.enigma.majumundur.dto.response.ClaimRewardResponse;
import com.enigma.majumundur.entity.ClaimReward;
import com.enigma.majumundur.entity.Customer;
import com.enigma.majumundur.entity.Reward;
import com.enigma.majumundur.mapper.ClaimRewardResponseMapper;
import com.enigma.majumundur.service.ClaimRewardService;
import com.enigma.majumundur.service.CustomerService;
import com.enigma.majumundur.service.RewardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ClaimRewardServiceImplTest {

    @Mock
    private final ClaimRewardResponseMapper claimRewardResponseMapper = new ClaimRewardResponseMapper();
    @Mock
    private RewardService rewardService;
    @Mock
    private CustomerService customerService;
    @Mock
    private ClaimRewardService claimRewardService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldDecreaseCustomerPointWhenClaimReward() {
        // given
        ClaimRewardRequest claimRewardRequest = new ClaimRewardRequest("example-rewardid");

        Reward reward = Reward.builder()
                .id("example-rewardid")
                .name("example-rewardname")
                .pointRequired(100)
                .stock(10)
                .build();

        Customer customer = Customer.builder()
                .id("example-customerid")
                .name("example-customername")
                .phone("example-customerphone")
                .point(100)
                .build();

        ClaimReward claimReward = ClaimReward.builder()
                .id("example-claimrewardid")
                .claimDate(new Date(Instant.now().toEpochMilli()))
                .customer(customer)
                .reward(reward)
                .build();

        ClaimRewardResponse expectedClaimRewardResponse = claimRewardResponseMapper.apply(claimReward);

        // stubbing
        Mockito
                .when(rewardService.getOneRewardById(claimReward.getId()))
                .thenReturn(reward);
        Mockito
                .when(customerService.getCustomerById(claimReward.getId()))
                .thenReturn(customer);
        Mockito
                .doNothing().when(customerService)
                .setCustomerPoint(customer.getId(), reward.getPointRequired());
        Mockito
                .when(claimRewardService.claimReward(claimRewardRequest))
                .thenReturn(expectedClaimRewardResponse);

        // when
        customerService.setCustomerPoint(customer.getId(), reward.getPointRequired());
        Reward actualReward = rewardService.getOneRewardById(claimReward.getId());
        Customer actualCustomer = customerService.getCustomerById(claimReward.getId());
        ClaimRewardResponse actualClaimRewardResponse = claimRewardService.claimReward(claimRewardRequest);

        // then
        Mockito.verify(customerService, Mockito.times(1))
                .setCustomerPoint(customer.getId(), reward.getPointRequired());
        assertEquals(expectedClaimRewardResponse, actualClaimRewardResponse);
        assertEquals(reward, actualReward);
        assertEquals(customer, actualCustomer);
    }
}