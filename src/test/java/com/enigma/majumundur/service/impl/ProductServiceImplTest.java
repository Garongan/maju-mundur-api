package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.dto.request.NewProductRequest;
import com.enigma.majumundur.dto.request.UpdateProductRequest;
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
import com.enigma.majumundur.service.ProductService;
import com.enigma.majumundur.service.UserService;
import com.enigma.majumundur.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
    private MerchantResponseMapper merchantResponseMapper = new MerchantResponseMapper();
    @Mock
    private ProductResponseMapper productResponseMapper = new ProductResponseMapper(merchantResponseMapper);
    @Mock
    private UserService userService;
    @Mock
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository, validationUtil, merchantService, productResponseMapper, userService);
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
    void shouldReturnProductResponseWhenGetOneById() {
        // given
        String productId = "example-product-id";
        String merchantId = "example-merchant-id";
        Merchant merchant = Merchant.builder()
                .id(merchantId)
                .shopName("example-shop-name")
                .phone("example-phone-number")
                .address("example-address")
                .build();
        MerchantResponse merchantResponse = new MerchantResponse(
                merchant.getId(),
                merchant.getShopName(),
                merchant.getPhone(),
                merchant.getAddress()
        );
        Product expectedProduct = Product.builder()
                .id(productId)
                .name("example-name")
                .price(1000L)
                .stock(10)
                .merchant(merchant)
                .build();
        ProductResponse expectedProductResponse = new ProductResponse(
                expectedProduct.getId(),
                expectedProduct.getName(),
                expectedProduct.getPrice(),
                expectedProduct.getStock(),
                merchantResponse
        );

        // stubbing
        Mockito
                .when(productRepository.findById(productId))
                .thenReturn(Optional.of(expectedProduct));
        Mockito
                .when(productResponseMapper.apply(expectedProduct))
                .thenReturn(expectedProductResponse);

        // when
        Product actualProduct = productRepository.findById(productId).orElse(null);
        assert actualProduct != null;
        ProductResponse actualProductResponse = productResponseMapper.apply(actualProduct);

        // then
        assertNotNull(actualProductResponse);
        assertEquals(expectedProductResponse, actualProductResponse);
    }

    @Test
    void shouldReturnProductResponsePageWhenCalled() {
        // given
        String direction = "asc";
        String orderBy = "name";
        int currentPage = 1;
        int pageSize = 10;
        String id = "example-page-id";

        Merchant merchant = Merchant.builder()
                .id("example-merchant-id")
                .shopName("example-shop-name")
                .phone("example-phone-number")
                .address("example-address")
                .build();
        MerchantResponse merchantResponse = new MerchantResponse(
                merchant.getId(),
                merchant.getShopName(),
                merchant.getPhone(),
                merchant.getAddress());
        Product product = Product.builder()
                .id(id)
                .name("example-name")
                .price(1000L)
                .stock(10)
                .merchant(merchant)
                .build();
        ProductResponse productResponse = new ProductResponse(
                id,
                "example-name",
                1000L,
                10,
                merchantResponse
        );
        Page<Product> productPage = new PageImpl<>(List.of(product));
        Page<ProductResponse> expectedProductResponsePage = new PageImpl<>(List.of(productResponse));
        Sort sort = Sort.by(Sort.Direction.fromString(direction), orderBy);
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);

        // stubbing
        Mockito
                .when(productResponseMapper.apply(product))
                .thenReturn(productResponse);
        Mockito
                .when(productRepository.findAll(pageable))
                .thenReturn(productPage);

        // when
        Page<ProductResponse> actualProductResponsePage = productService.getAllProducts(direction, orderBy, currentPage, pageSize);

        // then
        assertNotNull(actualProductResponsePage);
        assertEquals(expectedProductResponsePage, actualProductResponsePage);
    }

    @Test
    void updateProduct() {
        // given
        UpdateProductRequest updateProductRequest = new UpdateProductRequest(
                "example-id",
                "example-name",
                1000L,
                10,
                "example-user-account-id"
        );

        Role role = Role.builder().role(UserRole.ROLE_MERCHANT).build();

        UserAccount expectedUserAccount = UserAccount.builder()
                .id("example-user-account-id")
                .username("example-username")
                .password("example-password")
                .roles(List.of(role))
                .build();

        Merchant merchant = Merchant.builder()
                .id("example-id")
                .shopName("example-shop-name")
                .phone("example-phone-number")
                .address("example-address")
                .userAccount(expectedUserAccount)
                .build();

        Product product = Product.builder()
                .id("example-id")
                .name("example-name")
                .price(1000L)
                .stock(10)
                .merchant(merchant)
                .build();
        MerchantResponse merchantResponse = new MerchantResponse(
                "example-id",
                "example-shop-name",
                "example-phone-number",
                "example-address"
        );
        ProductResponse expectedProductResponse = new ProductResponse(
                "example-id",
                "example-name",
                1000L,
                10,
                merchantResponse
        );

        // stubbing
        Mockito.doNothing().when(validationUtil).validate(updateProductRequest);
        Mockito
                .when(productRepository.findById(updateProductRequest.id()))
                .thenReturn(Optional.of(product));
        Mockito
                .when(userService.getByContext())
                .thenReturn(expectedUserAccount);
        Mockito
                .when(productResponseMapper.apply(productRepository.saveAndFlush(product)))
                .thenReturn(expectedProductResponse);

        // when
        validationUtil.validate(updateProductRequest);
        Product actualProduct = productRepository.findById(updateProductRequest.id()).orElse(null);
        UserAccount actualAccount = userService.getByContext();
        ProductResponse actualProductResponse = productService.updateProduct(updateProductRequest);

        // then

        assertNotNull(actualProduct);
        assertEquals(product, actualProduct);

        assertNotNull(actualAccount);
        assertEquals(expectedUserAccount, actualAccount);

        assertNotNull(actualProductResponse);
        assertEquals(expectedProductResponse, actualProductResponse);
    }

    @Test
    void deleteProduct() {
        // given
        String productId = "example-product-id";
        String userAccountId = "example-user-account-id";

        Role role = Role.builder().role(UserRole.ROLE_MERCHANT).build();
        UserAccount expectedUserAccount = UserAccount.builder()
                .id(userAccountId)
                .username("example-username")
                .password("example-password")
                .roles(List.of(role))
                .build();

        MerchantResponse merchantResponse = new MerchantResponse(
                "example-id",
                "example-shop-name",
                "example-phone-number",
                "example-address"
        );
        Product product = Product.builder()
                .id(productId)
                .name("example-name")
                .price(1000L)
                .stock(10)
                .build();
        ProductResponse expectedProductResponse = new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getStock(),
                merchantResponse
        );

        // stubbing
        Mockito
                .when(userService.getByContext())
                .thenReturn(expectedUserAccount);
        Mockito
                .when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));
        Mockito
                .when(productResponseMapper.apply(product))
                .thenReturn(expectedProductResponse);
        Mockito
                .doNothing()
                .when(productRepository)
                .deleteById(productId);

        // when
        ProductResponse actualProductResponse = productService.getProductById(productId);
        UserAccount actualAccount = userService.getByContext();
        productService.deleteProduct(productId, userAccountId);

        // then
        assertNotNull(actualAccount);
        assertEquals(expectedUserAccount, actualAccount);

        assertNotNull(actualProductResponse);
        assertEquals(expectedProductResponse, actualProductResponse);

        Mockito.verify(productRepository, Mockito.times(1)).deleteById(productId);
    }
}