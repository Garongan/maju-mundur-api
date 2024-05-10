package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.dto.request.CustomerRequest;
import com.enigma.majumundur.dto.request.LoginRequest;
import com.enigma.majumundur.dto.request.MerchantRequest;
import com.enigma.majumundur.dto.response.LoginResponse;
import com.enigma.majumundur.dto.response.RegisterResponse;
import com.enigma.majumundur.entity.Customer;
import com.enigma.majumundur.entity.Merchant;
import com.enigma.majumundur.entity.Role;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.mapper.RegisterCustomerResponseMapper;
import com.enigma.majumundur.mapper.RegisterMerchantResponseMapper;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.service.CustomerService;
import com.enigma.majumundur.service.JwtService;
import com.enigma.majumundur.service.MerchantService;
import com.enigma.majumundur.service.RoleService;
import com.enigma.majumundur.utils.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private CustomerService customerService;
    @Mock
    private MerchantService merchantService;
    @Mock
    private final RegisterCustomerResponseMapper registerCustomerResponseMapper = new RegisterCustomerResponseMapper();
    @Mock
    private final RegisterMerchantResponseMapper registerMerchantResponseMapper = new RegisterMerchantResponseMapper();
    @Mock
    private ValidationUtil validationUtil;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtService jwtService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldInitAdminWhenCalled() {
        // given
        Role expectedAdminRole = Role.builder().role(UserRole.ROLE_ADMIN).build();
        Role expectedMerchantRole = Role.builder().role(UserRole.ROLE_MERCHANT).build();
        Role expectedCustomerRole = Role.builder().role(UserRole.ROLE_CUSTOMER).build();
        UserAccount expectedUserAccount = UserAccount.builder()
                .username("admin")
                .password("admin")
                .roles(List.of(expectedAdminRole, expectedMerchantRole, expectedCustomerRole))
                .build();

        // stubbing
        Mockito
                .when(userAccountRepository.saveAndFlush(expectedUserAccount))
                .thenReturn(expectedUserAccount);
        Mockito
                .when(roleService.saveOrGet(UserRole.ROLE_ADMIN))
                .thenReturn(expectedAdminRole);
        Mockito
                .when(roleService.saveOrGet(UserRole.ROLE_MERCHANT))
                .thenReturn(expectedMerchantRole);
        Mockito
                .when(roleService.saveOrGet(UserRole.ROLE_CUSTOMER))
                .thenReturn(expectedCustomerRole);

        // when
        UserAccount actualUserAccount = userAccountRepository.saveAndFlush(expectedUserAccount);
        Role actualAdminRole = roleService.saveOrGet(UserRole.ROLE_ADMIN);
        Role actualMerchantRole = roleService.saveOrGet(UserRole.ROLE_MERCHANT);
        Role actualCustomerRole = roleService.saveOrGet(UserRole.ROLE_CUSTOMER);

        // then
        assertEquals(expectedUserAccount, actualUserAccount);
        assertEquals(expectedAdminRole, actualAdminRole);
        assertEquals(expectedMerchantRole, actualMerchantRole);
        assertEquals(expectedCustomerRole, actualCustomerRole);
    }

    @Test
    void shouldSaveCustomerWhenRegisterCustomer() {
        // given
        Role expectedCustomerRole = Role.builder().role(UserRole.ROLE_CUSTOMER).build();
        UserAccount expectedUserAccount = UserAccount.builder()
                .username("customer")
                .password("password")
                .roles(List.of(expectedCustomerRole))
                .build();
        CustomerRequest customerRequest = new CustomerRequest(
                "example-customer-name",
                "example-road",
                "example-phone",
                expectedUserAccount
        );
        Customer customer = Customer.builder()
                .id("example-id")
                .name(customerRequest.name())
                .address(customerRequest.address())
                .phone(customerRequest.phone())
                .userAccount(customerRequest.userAccount())
                .build();
        RegisterResponse expectedRegisterResponse = registerCustomerResponseMapper.apply(customer);


        // stubbing
        Mockito
                .when(userAccountRepository.saveAndFlush(expectedUserAccount))
                .thenReturn(expectedUserAccount);
        Mockito
                .when(roleService.saveOrGet(UserRole.ROLE_CUSTOMER))
                .thenReturn(expectedCustomerRole);
        Mockito
                .when(registerCustomerResponseMapper.apply(customerService.saveCustomer(customerRequest)))
                .thenReturn(expectedRegisterResponse);

        // when
        UserAccount actualUserAccount = userAccountRepository.saveAndFlush(expectedUserAccount);
        Role actualCustomerRole = roleService.saveOrGet(UserRole.ROLE_CUSTOMER);
        RegisterResponse actualRegisterResponse = registerCustomerResponseMapper.apply(customerService.saveCustomer(customerRequest));

        // then
        assertEquals(expectedUserAccount, actualUserAccount);
        assertEquals(expectedCustomerRole, actualCustomerRole);
        assertEquals(expectedRegisterResponse, actualRegisterResponse);
    }

    @Test
    void shouldSaveMerchantWhenRegisterMerchant() {
        // given
        Role expectedMerchantRole = Role.builder().role(UserRole.ROLE_MERCHANT).build();
        UserAccount expectedUserAccount = UserAccount.builder()
                .username("merchant")
                .password("password")
                .roles(List.of(expectedMerchantRole))
                .build();
        MerchantRequest merchantRequest = new MerchantRequest(
                "example-shop-name",
                "example-phone",
                "example-address",
                expectedUserAccount
        );
        Merchant merchant = Merchant.builder()
                .id("example-id")
                .shopName(merchantRequest.shopName())
                .phone(merchantRequest.phone())
                .address(merchantRequest.address())
                .userAccount(merchantRequest.userAccount())
                .build();
        RegisterResponse expectedRegisterMerchantResponse = registerMerchantResponseMapper.apply(merchant);

        // stubbing
        Mockito
                .when(userAccountRepository.saveAndFlush(expectedUserAccount))
                .thenReturn(expectedUserAccount);
        Mockito
                .when(roleService.saveOrGet(UserRole.ROLE_MERCHANT))
                .thenReturn(expectedMerchantRole);
        Mockito
                .when(registerMerchantResponseMapper.apply(merchantService.saveMerchant(merchantRequest)))
                .thenReturn(expectedRegisterMerchantResponse);

        // when
        UserAccount actualUserAccount = userAccountRepository.saveAndFlush(expectedUserAccount);
        Role actualMerchantRole = roleService.saveOrGet(UserRole.ROLE_MERCHANT);
        RegisterResponse actualRegisterMerchantResponse = registerMerchantResponseMapper.apply(merchantService.saveMerchant(merchantRequest));

        // then
        assertEquals(expectedUserAccount, actualUserAccount);
        assertEquals(expectedMerchantRole, actualMerchantRole);
        assertEquals(expectedRegisterMerchantResponse, actualRegisterMerchantResponse);

    }

    @Test
    void shouldReturnUsernameAndTokenWhenLogin() {
        // given
        LoginRequest loginRequest = new LoginRequest("example-username", "example-password");
        Role expectedRole = Role.builder().role(UserRole.ROLE_ADMIN).build();
        UserAccount expectedUserAccount = UserAccount.builder()
                .username(loginRequest.username())
                .password(loginRequest.password())
                .roles(List.of(expectedRole))
                .build();
        String expectedToken = "example-token";
        LoginResponse expectedLoginResponse = new LoginResponse(
                expectedUserAccount.getUsername(),
                expectedToken,
                expectedUserAccount.getRoles().stream().map(role -> role.getRole().name()).toList()
        );

        // stubbing
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito
                .when(authentication.getPrincipal())
                .thenReturn(expectedUserAccount);
        Mockito.doNothing().when(validationUtil).validate(loginRequest);
        Mockito
                .when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(authentication);
        Mockito
                .when(jwtService.generateToken(expectedUserAccount))
                .thenReturn(expectedToken);

        // when
        validationUtil.validate(loginRequest);
        Authentication authenticate = authenticationManager.authenticate(Mockito.any());
        UserAccount actualUserAccount = (UserAccount) authenticate.getPrincipal();
        String actualToken = jwtService.generateToken(expectedUserAccount);
        LoginResponse actualLoginResponse = new LoginResponse(
                actualUserAccount.getUsername(),
                actualToken,
                actualUserAccount.getRoles().stream().map(role -> role.getRole().name()).toList()
        );

        // then
        assertEquals(expectedUserAccount, actualUserAccount);
        assertEquals(expectedToken, actualToken);
        assertEquals(expectedLoginResponse, actualLoginResponse);
    }
}