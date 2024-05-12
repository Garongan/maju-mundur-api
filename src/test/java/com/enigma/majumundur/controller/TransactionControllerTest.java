package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.NewTransactionDetailRequest;
import com.enigma.majumundur.dto.request.NewTransactionRequest;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.TransactionDetailResponse;
import com.enigma.majumundur.dto.response.TransactionResponse;
import com.enigma.majumundur.service.TransactionService;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
class TransactionControllerTest {

    @MockBean
    private TransactionService transactionService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser(username = "username", roles = {"CUSTOMER"})
    void shouldReturn201StatusAndCommonResponseWhenCreateNewTransaction() throws Exception {
        // given
        List<NewTransactionDetailRequest> newTransactionDetailRequestList = List.of(new NewTransactionDetailRequest(
                1,
                1000L,
                "example-product-id",
                "example-reward-id"
        ));
        NewTransactionRequest newTransactionRequest = new NewTransactionRequest(
                "example-customer-id",
                newTransactionDetailRequestList
        );

        List<TransactionDetailResponse> transactionDetailResponseList = List.of(new TransactionDetailResponse(
                "example-transaction-detail-id",
                1,
                1000L,
                "example-product-id"
                ));

        TransactionResponse expectedTransactionResponse = new TransactionResponse(
                "example-transaction-id",
                "example-transaction-date",
                100,
                "example-customer-id",
                transactionDetailResponseList
        );

        // stubbing
        Mockito
                .when(transactionService.createTransaction(Mockito.any(NewTransactionRequest.class)))
                .thenReturn(expectedTransactionResponse);

        // when
        String json = objectMapper.writeValueAsString(newTransactionRequest);
        mockMvc.perform(
                MockMvcRequestBuilders.post(ApiUrl.TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    // then
                    CommonResponse<TransactionResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.CREATED.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_CREATED, response.message());
                    assertNotNull(response.data());
                });
    }

    @Test
    @WithMockUser(username = "username", roles = {"CUSTOMER"})
    void shouldReturnListTransactionResponseWhenGetAllTransactions() throws Exception {
        // given
        List<TransactionDetailResponse> transactionDetailResponseList = List.of(new TransactionDetailResponse(
                "example-transaction-detail-id",
                1,
                1000L,
                "example-product-id"
        ));

        TransactionResponse expectedTransactionResponse = new TransactionResponse(
                "example-transaction-id",
                "example-transaction-date",
                100,
                "example-customer-id",
                transactionDetailResponseList
        );

        // stubbing
        Mockito
                .when(transactionService.getAllTransactions())
                .thenReturn(List.of(expectedTransactionResponse));

        // when
        mockMvc.perform(
                MockMvcRequestBuilders.get(ApiUrl.TRANSACTION)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    // then
                    CommonResponse<List<TransactionResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.OK.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS, response.message());
                    assertNotNull(response.data());
                });
    }

    @Test
    @WithMockUser(username = "username", roles = {"MERCHANT"})
    void getHistoryTransaction() throws Exception {
        // given
        List<TransactionDetailResponse> transactionDetailResponseList = List.of(new TransactionDetailResponse(
                "example-transaction-detail-id",
                1,
                1000L,
                "example-product-id"
        ));

        TransactionResponse expectedTransactionResponse = new TransactionResponse(
                "example-transaction-id",
                "example-transaction-date",
                100,
                "example-customer-id",
                transactionDetailResponseList
        );

        // stubbing
        Mockito
                .when(transactionService.getMerchantHistoryTransaction())
                .thenReturn(List.of(expectedTransactionResponse));

        // when
        mockMvc.perform(
                MockMvcRequestBuilders.get(ApiUrl.TRANSACTION + "/history")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    // then
                    CommonResponse<List<TransactionResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.OK.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_RETRIEVE_LIST, response.message());
                    assertNotNull(response.data());
                });
    }
}