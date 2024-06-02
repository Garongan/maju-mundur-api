package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.dto.request.*;
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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthServiceImplTest {

    @MockBean
    private UserAccountRepository userAccountRepository;
    @Mock
    private RoleService roleService;
    @Mock
    private PasswordEncoder passwordEncoder;
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
    @InjectMocks
    private AuthServiceImpl authServiceImpl;

    @BeforeEach
    void setUp() {
        authServiceImpl = new AuthServiceImpl(
                userAccountRepository,
                roleService,
                passwordEncoder,
                customerService,
                authenticationManager,
                jwtService,
                validationUtil,
                registerCustomerResponseMapper,
                registerMerchantResponseMapper,
                merchantService);
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
        when(roleService.saveOrGet(UserRole.ROLE_ADMIN))
                .thenReturn(expectedAdminRole);
        when(roleService.saveOrGet(UserRole.ROLE_MERCHANT))
                .thenReturn(expectedMerchantRole);
        when(roleService.saveOrGet(UserRole.ROLE_CUSTOMER))
                .thenReturn(expectedCustomerRole);
        when(userAccountRepository.saveAndFlush(expectedUserAccount))
                .thenReturn(expectedUserAccount);

        // when
        authServiceImpl.initAdmin();

        // then
        verify(roleService, times(1)).saveOrGet(UserRole.ROLE_ADMIN);
        verify(roleService, times(1)).saveOrGet(UserRole.ROLE_MERCHANT);
        verify(roleService, times(1)).saveOrGet(UserRole.ROLE_CUSTOMER);
        verify(userAccountRepository, times(1)).saveAndFlush(any(UserAccount.class));
    }

    @Test
    void shouldNotInitAdminWhenAlreadyExists() {
        // given
        UserAccount expectedAdminAccount = UserAccount.builder().username("admin-user").build();

        // stubbing
        when(userAccountRepository.findByUsername("admin-user"))
                .thenReturn(Optional.of(expectedAdminAccount));

        // when
        Optional<UserAccount> actualAdminAccount = userAccountRepository.findByUsername("admin-user");

        // then
        assertTrue(actualAdminAccount.isPresent());
    }

    @Test
    void shouldSaveCustomerWhenRegisterCustomer() {
        // given
        Role expectedCustomerRole = Role.builder().role(UserRole.ROLE_CUSTOMER).build();
        RegisterCustomerRequest registerCustomerRequest = new RegisterCustomerRequest(
                "example-customer-name",
                "example-road",
                "example-phone",
                "customer",
                "password"
        );
        UserAccount expectedUserAccount = UserAccount.builder()
                .username(registerCustomerRequest.username())
                .password(registerCustomerRequest.password())
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
        doNothing().when(validationUtil).validate(registerCustomerRequest);
        when(userAccountRepository.saveAndFlush(expectedUserAccount))
                .thenReturn(expectedUserAccount);
        when(roleService.saveOrGet(UserRole.ROLE_CUSTOMER))
                .thenReturn(expectedCustomerRole);
        when(registerCustomerResponseMapper.apply(customerService.saveCustomer(customerRequest)))
                .thenReturn(expectedRegisterResponse);

        // when
        RegisterResponse actualRegisterResponse = authServiceImpl.registerCustomer(registerCustomerRequest);

        // then
        verify(validationUtil, times(1))
                .validate(registerCustomerRequest);
        verify(roleService, times(1))
                .saveOrGet(UserRole.ROLE_CUSTOMER);
        assertEquals(expectedRegisterResponse, actualRegisterResponse);
    }

    @Test
    void shouldSaveMerchantWhenRegisterMerchant() {
        // given
        Role expectedMerchantRole = Role.builder().role(UserRole.ROLE_MERCHANT).build();
        RegisterMerchantRequest registerMerchantRequest = new RegisterMerchantRequest(
                "example-shop-name",
                "example-phone",
                "example-address",
                "merchant",
                "password"
        );
        UserAccount expectedUserAccount = UserAccount.builder()
                .username(registerMerchantRequest.username())
                .password(registerMerchantRequest.password())
                .roles(List.of(expectedMerchantRole))
                .build();
        MerchantRequest merchantRequest = new MerchantRequest(
                registerMerchantRequest.shopName(),
                registerMerchantRequest.phone(),
                registerMerchantRequest.address(),
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
        doNothing().when(validationUtil).validate(registerMerchantRequest);
        when(userAccountRepository.saveAndFlush(expectedUserAccount))
                .thenReturn(expectedUserAccount);
        when(roleService.saveOrGet(UserRole.ROLE_MERCHANT))
                .thenReturn(expectedMerchantRole);
        when(registerMerchantResponseMapper.apply(merchantService.saveMerchant(merchantRequest)))
                .thenReturn(expectedRegisterMerchantResponse);

        // when
        RegisterResponse actualRegisterMerchantResponse = authServiceImpl.registerMerchant(registerMerchantRequest);

        // then
        verify(validationUtil, times(1))
                .validate(registerMerchantRequest);
        verify(validationUtil, times(1))
                .validate(registerMerchantRequest);
        verify(roleService, times(1))
                .saveOrGet(UserRole.ROLE_MERCHANT);
        assertEquals(expectedRegisterMerchantResponse, actualRegisterMerchantResponse);
    }

    @Test
    void shouldReturnUsernameAndTokenWhenLogin() {
        // given
        LoginRequest loginRequest = new LoginRequest("example-username", "example-password");
        Role expectedRole = Role.builder().role(UserRole.ROLE_ADMIN).build();
        UserAccount expectedUserAccount = UserAccount.builder()
                .id("example-id")
                .username(loginRequest.username())
                .password(loginRequest.password())
                .roles(List.of(expectedRole))
                .build();
        String expectedToken = "example-token";
        LoginResponse expectedLoginResponse = new LoginResponse(
                expectedUserAccount.getId(),
                expectedUserAccount.getUsername(),
                expectedToken,
                expectedUserAccount.getRoles().stream().map(role -> role.getRole().name()).toList()
        );

        // stubbing
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal())
                .thenReturn(expectedUserAccount);
        doNothing().when(validationUtil).validate(loginRequest);
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);
        when(jwtService.generateToken(expectedUserAccount))
                .thenReturn(expectedToken);

        // when
        LoginResponse actualLoginResponse = authServiceImpl.login(loginRequest);

        // then
        verify(validationUtil, times(1)).validate(loginRequest);
        assertEquals(expectedLoginResponse, actualLoginResponse);
    }
}