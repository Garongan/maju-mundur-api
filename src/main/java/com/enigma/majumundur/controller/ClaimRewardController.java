package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.ClaimRewardRequest;
import com.enigma.majumundur.dto.response.ClaimRewardResponse;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.service.ClaimRewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrl.CLAIM_REWARD)
public class ClaimRewardController {

    private final ClaimRewardService claimRewardService;

    @Operation(summary = "Claim Reward")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('USER') and isAuthenticated()")
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<ClaimRewardResponse>> claimReward(@RequestBody ClaimRewardRequest request) {
        ClaimRewardResponse claimRewardResponse = claimRewardService.claimReward(request);

        CommonResponse<ClaimRewardResponse> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS,
                claimRewardResponse,
                null
        );

        return ResponseEntity.status(HttpStatus.OK.value()).body(commonResponse);
    }
}
