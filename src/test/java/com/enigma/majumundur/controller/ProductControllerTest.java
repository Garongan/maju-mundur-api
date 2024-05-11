package com.enigma.majumundur.controller;

import com.enigma.majumundur.constant.ApiUrl;
import com.enigma.majumundur.constant.StatusMessage;
import com.enigma.majumundur.dto.request.NewProductRequest;
import com.enigma.majumundur.dto.request.UpdateProductRequest;
import com.enigma.majumundur.dto.response.CommonResponse;
import com.enigma.majumundur.dto.response.MerchantResponse;
import com.enigma.majumundur.dto.response.PaginationResponse;
import com.enigma.majumundur.dto.response.ProductResponse;
import com.enigma.majumundur.mapper.PaginationResponseMapper;
import com.enigma.majumundur.service.ProductService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser(username = "username", roles = {"MERCHANT"})
    void shouldReturn201StatusAndCommonResponseWhenCreateNewProduct() throws Exception {
        // given
        NewProductRequest newProductRequest = new NewProductRequest(
                "product-name",
                1000L,
                10,
                "merchant-id"
        );

        MerchantResponse merchantResponse = new MerchantResponse(
                "merchant-id",
                "shop-name",
                "phone",
                "address"
        );

        ProductResponse productResponse = new ProductResponse(
                "product-id",
                "product-name",
                1000L,
                10,
                merchantResponse
        );

        // stubbing
        Mockito
                .when(productService.createProduct(newProductRequest))
                .thenReturn(productResponse);

        // when
        String json = objectMapper.writeValueAsString(newProductRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders.post(ApiUrl.PRODUCT)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(result -> {
                    // then
                    CommonResponse<ProductResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.CREATED.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_CREATED, response.message());
                    assertNotNull(response.data());
                });
    }

    @Test
    @WithMockUser(username = "username")
    void getProductById() throws Exception {
        // given
        String productId = "product-id";

        MerchantResponse merchantResponse = new MerchantResponse(
                "merchant-id",
                "shop-name",
                "phone",
                "address"
        );
        ProductResponse productResponse = new ProductResponse(
                "product-id",
                "product-name",
                1000L,
                10,
                merchantResponse
        );

        // stubbing
        Mockito
                .when(productService.getProductById(productId))
                .thenReturn(productResponse);

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.get(ApiUrl.PRODUCT + "/" + productId)
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    // then
                    CommonResponse<ProductResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.OK.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS, response.message());
                    assertNotNull(response.data());
                });
    }

    @Test
    @WithMockUser(username = "username")
    void getAllProducts() throws Exception {
        // given
        String direction = "asc";
        String orderBy = "name";
        Integer currentPage = 1;
        long pageSize = 10L;

        MerchantResponse merchantResponse = new MerchantResponse(
                "merchant-id",
                "shop-name",
                "phone",
                "address"
        );
        ProductResponse productResponse = new ProductResponse(
                "product-id",
                "product-name",
                1000L,
                10,
                merchantResponse
        );

        Page<ProductResponse> productResponsePage = new PageImpl<>(List.of(productResponse));
        PaginationResponse paginationResponse = new PaginationResponse(
                productResponsePage.getTotalPages(),
                productResponsePage.getTotalElements(),
                productResponsePage.getNumber(),
                productResponsePage.getSize(),
                productResponsePage.hasNext(),
                productResponsePage.hasPrevious()
        );

        // stubbing
        Mockito
                .when(productService.getAllProducts(direction, orderBy, currentPage, (int) pageSize))
                .thenReturn(productResponsePage);

        // when
        mockMvc.perform(
                        MockMvcRequestBuilders.get(ApiUrl.PRODUCT)
                                .param("direction", direction)
                                .param("orderBy", orderBy)
                                .param("currentPage", currentPage.toString())
                                .param("pageSize", Long.toString(pageSize))
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    // then
                    CommonResponse<List<ProductResponse>> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.OK.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_RETRIEVE_LIST, response.message());
                    assertNotNull(response.data());
                    assertEquals(paginationResponse, response.paginationResponse());
                });
    }

    @Test
    @WithMockUser(username = "username", roles = {"MERCHANT"})
    void updateProduct() throws Exception {
        // given
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(
                "product-id",
                "product-name",
                1000L,
                10,
                "merchant-id"
        );

        MerchantResponse merchantResponse = new MerchantResponse(
                "merchant-id",
                "shop-name",
                "phone",
                "address"
        );
        ProductResponse productResponse = new ProductResponse(
                "product-id",
                "product-name",
                1000L,
                10,
                merchantResponse
        );

        // stubbing
        Mockito
                .when(productService.updateProduct(updateProductRequest))
                .thenReturn(productResponse);

        // when
        String json = objectMapper.writeValueAsString(updateProductRequest);
        mockMvc.perform(
                        MockMvcRequestBuilders.put(ApiUrl.PRODUCT)
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                                .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    // then
                    CommonResponse<ProductResponse> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.OK.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_UPDATED, response.message());
                    assertNotNull(response.data());
                });
    }

    @Test
    @WithMockUser(username = "username", roles = {"MERCHANT"})
    void deleteProduct() {
        // given
        String productId = "product-id";

        // stubbing
        Mockito.doNothing().when(productService).deleteProduct(productId, "user-account-id");

        // when
        assertDoesNotThrow(() -> mockMvc.perform(
                        MockMvcRequestBuilders.delete(ApiUrl.PRODUCT + "/" + productId)
                                .param("userAccountId", "user-account-id")
                                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(result -> {
                    // then
                    CommonResponse<String> response = objectMapper.readValue(
                            result.getResponse().getContentAsString(),
                            new TypeReference<>() {
                            });
                    assertEquals(HttpStatus.OK.value(), response.statusCode());
                    assertEquals(StatusMessage.SUCCESS_DELETED, response.message());
                    assertNull(response.data());
                }));
    }
}