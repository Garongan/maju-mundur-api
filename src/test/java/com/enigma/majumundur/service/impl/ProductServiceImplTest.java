package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.dto.request.NewProductRequest;
import com.enigma.majumundur.dto.response.MerchantResponse;
import com.enigma.majumundur.dto.response.ProductResponse;
import com.enigma.majumundur.entity.Merchant;
import com.enigma.majumundur.entity.Product;
import com.enigma.majumundur.entity.Role;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.mapper.MerchantResponseMapper;
import com.enigma.majumundur.mapper.ProductResponseMapper;
import com.enigma.majumundur.repository.ProductRepository;
import com.enigma.majumundur.service.MerchantService;
import com.enigma.majumundur.service.UserService;
import com.enigma.majumundur.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private MerchantService merchantService;
    @Mock
    private final MerchantResponseMapper merchantResponseMapper = new MerchantResponseMapper();
    @Mock
    private final ProductResponseMapper productResponseMapper = new ProductResponseMapper(merchantResponseMapper);
    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldCreateProductAndReturnProductResponseWhenCalled() {
        // given
        NewProductRequest newProductRequest = new NewProductRequest(
                "example-name",
                10000L,
                10,
                "example-user-account-id"
        );
        Role role = Role.builder()
                .role(UserRole.ROLE_MERCHANT)
                .build();

        UserAccount userAccount = UserAccount.builder()
                .id("example-user-account-id")
                .username("example-username")
                .password("example-password")
                .roles(List.of(role))
                .build();

        Merchant expectedMerchant = Merchant.builder()
                .id("example-id")
                .shopName("example-shop-name")
                .phone("example-phone-number")
                .address("example-address")
                .userAccount(userAccount)
                .build();

        MerchantResponse merchantResponse = new MerchantResponse(
                "example-id",
                "example-shop-name",
                "example-phone-number",
                "example-address"
        );

        Product product = Product.builder()
                .name(newProductRequest.name())
                .price(newProductRequest.price())
                .stock(newProductRequest.stock())
                .merchant(expectedMerchant)
                .build();

        ProductResponse expectedProductResponse = new ProductResponse(
                "example-id",
                "example-name",
                1000L,
                10,
                merchantResponse
        );

        // stubbing
        Mockito.doNothing().when(validationUtil).validate(newProductRequest);
        Mockito
                .when(merchantService.getMerchantByUserAccountId(newProductRequest.userAccountId()))
                .thenReturn(expectedMerchant);
        Mockito
                .when(productResponseMapper.apply(productRepository.saveAndFlush(product)))
                .thenReturn(expectedProductResponse);

        // when
        validationUtil.validate(newProductRequest);
        ProductResponse actualProductResponse = productResponseMapper.apply(productRepository.saveAndFlush(product));
        Merchant actualMerchant = merchantService.getMerchantByUserAccountId(newProductRequest.userAccountId());

        // then
        assertEquals(expectedMerchant, actualMerchant);
        assertEquals(expectedProductResponse, actualProductResponse);
    }

    @Test
    void getProductById() {
    }

    @Test
    void getAllProducts() {
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }
}