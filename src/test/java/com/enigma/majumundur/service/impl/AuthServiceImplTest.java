package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.entity.Role;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.service.RoleService;
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
class AuthServiceImplTest {

    @Mock
    private UserAccountRepository userAccountRepository;
    @Mock
    private RoleService roleService;

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
    void registerCustomer() {
    }

    @Test
    void login() {
    }
}