package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.ClaimRewardRequest;
import com.enigma.majumundur.dto.response.ClaimRewardResponse;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.service.ClaimRewardService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class ClaimRewardControllerTest {

    @MockBean
    private ClaimRewardService claimRewardService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser(username = "username")
    void shouldReturn200StatusAndCommonResponseWhenClaimReward() throws Exception {
        // given
        ClaimRewardRequest claimRewardRequest = new ClaimRewardRequest("rewardId");

        ClaimRewardResponse claimRewardResponse = new ClaimRewardResponse(
                "userId",
                "example-claim-date",
                "example-customer-id",
                "example-reward-id"
        );

        // stubbing
        Mockito
                .when(claimRewardService.claimReward(claimRewardRequest))
                .thenReturn(claimRewardResponse);

        // when
        String json = objectMapper.writeValueAsString(claimRewardRequest);
        mockMvc
                .perform(
                        MockMvcRequestBuilders.post(ApiUrl.CLAIM_REWARD)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    CommonResponse<ClaimRewardResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.OK.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS, response.message());
                    assertNotNull(response.data());
                });
    }
}