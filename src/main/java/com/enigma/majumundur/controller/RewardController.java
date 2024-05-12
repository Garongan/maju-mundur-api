package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.NewRewardRequest;
import com.enigma.majumundur.dto.request.UpdateRewardRequest;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.RewardResponse;
import com.enigma.majumundur.service.RewardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApiUrl.REWARD)
public class RewardController {

    private final RewardService rewardService;

    @Operation(summary = "Create new reward")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('ADMIN') and isAuthenticated()")
    @PostMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<RewardResponse>> createReward(@RequestBody NewRewardRequest request) {
        RewardResponse rewardResponse = rewardService.createReward(request);

        CommonResponse<RewardResponse> commonResponse = new CommonResponse<>(
                HttpStatus.CREATED.value(),
                StatusMessage.SUCCESS_CREATED,
                rewardResponse,
                null
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @Operation(summary = "Get reward by id")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(
            path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<RewardResponse>> getRewardById(@PathVariable String id) {
        RewardResponse rewardResponse = rewardService.getRewardById(id);

        CommonResponse<RewardResponse> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS,
                rewardResponse,
                null
        );

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Operation(summary = "Get all rewards")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<List<RewardResponse>>> getAllRewards() {
        List<RewardResponse> rewardResponses = rewardService.getAllRewards();

        CommonResponse<List<RewardResponse>> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS,
                rewardResponses,
                null
        );

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

    @Operation(summary = "Update reward")
    @SecurityRequirement(name = "Authorization")
    @PreAuthorize("hasRole('ADMIN') and isAuthenticated()")
    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CommonResponse<RewardResponse>> updateReward(UpdateRewardRequest request) {
        RewardResponse rewardResponse = rewardService.updateReward(request);

        CommonResponse<RewardResponse> commonResponse = new CommonResponse<>(
                HttpStatus.OK.value(),
                StatusMessage.SUCCESS,
                rewardResponse,
                null
        );

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }
}
