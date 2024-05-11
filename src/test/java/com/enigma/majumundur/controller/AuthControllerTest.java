package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.LoginRequest;
import com.enigma.majumundur.dto.request.RegisterCustomerRequest;
import com.enigma.majumundur.dto.request.RegisterMerchantRequest;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.LoginResponse;
import com.enigma.majumundur.dto.response.RegisterResponse;
import com.enigma.majumundur.service.AuthService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
class AuthControllerTest {

    @MockBean
    private AuthService authService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser(username = "username")
    void shouldReturn200StatusAndReturnLoginResponseWhenLogin() throws Exception {
        // given
        LoginRequest actualLoginRequest = new LoginRequest(
                "username",
                "password"
        );
        LoginResponse loginResponse = new LoginResponse(
                "example-id",
                "username",
                "example-token",
                List.of("ROLE_USER")
        );

        // stubbing
        Mockito
                .when(authService.login(actualLoginRequest))
                .thenReturn(loginResponse);

        // when
        String json = objectMapper.writeValueAsString(actualLoginRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders.post(ApiUrl.AUTH + "/login")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                ).andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    // then
                    CommonResponse<LoginResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(200, response.statusCode());
                    assertEquals(StatusMessage.SUCCESS, response.message());
                    assertNotNull(response.data());
                });
    }

    @Test
    @WithMockUser(username = "username")
    void shouldReturn201StatusAndReturnRegisterResponseWhenRegisterCustomer() throws Exception {
        // given
        RegisterCustomerRequest actualRegisterCustomerRequest = new RegisterCustomerRequest(
                "username",
                "password",
                "email",
                "phone",
                "address"
        );
        RegisterResponse actualRegisterResponse = new RegisterResponse(
                "username",
                List.of("ROLE_USER")
        );

        // stubbing
        Mockito
                .when(authService.registerCustomer(actualRegisterCustomerRequest))
                .thenReturn(actualRegisterResponse);

        // when
        String json = objectMapper.writeValueAsString(actualRegisterCustomerRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders.post(ApiUrl.AUTH + "/register-customer")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    // then
                    CommonResponse<RegisterResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(201, response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_CREATED, response.message());
                    assertNotNull(response.data());
                });
    }

    @Test
    @WithMockUser(username = "username")
    void shouldReturnRegisterResponseWhenRegisterMerchant() throws Exception {
        // given
        RegisterMerchantRequest actualRegisterMerchantRequest = new RegisterMerchantRequest(
                "username",
                "password",
                "email",
                "phone",
                "address"
        );

        RegisterResponse actualRegisterResponse = new RegisterResponse(
                "username",
                List.of("ROLE_MERCHANT")
        );

        // stubbing
        Mockito
                .when(authService.registerMerchant(actualRegisterMerchantRequest))
                .thenReturn(actualRegisterResponse);

        // when
        String json = objectMapper.writeValueAsString(actualRegisterMerchantRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders.post(ApiUrl.AUTH + "/register-merchant")
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json)
                ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    // then
                    CommonResponse<RegisterResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(201, response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_CREATED, response.message());
                    assertNotNull(response.data());
                });

    }
}