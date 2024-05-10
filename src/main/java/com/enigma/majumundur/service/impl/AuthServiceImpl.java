package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.dto.request.*;
import com.enigma.majumundur.dto.response.LoginResponse;
import com.enigma.majumundur.dto.response.RegisterResponse;
import com.enigma.majumundur.entity.Role;
import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.mapper.RegisterCustomerResponseMapper;
import com.enigma.majumundur.mapper.RegisterMerchantResponseMapper;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.service.*;
import com.enigma.majumundur.utils.ValidationUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerService customerService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final ValidationUtil validationUtil;
    private final RegisterCustomerResponseMapper registerCustomerResponseMapper;
    private final RegisterMerchantResponseMapper registerMerchantResponseMapper;
    private final MerchantService merchantService;

    @Value("${maju-mundur-api.admin.username}")
    private String adminUsername;
    @Value("${maju-mundur-api.admin.password}")
    private String adminPassword;


    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initAdmin() {
        Optional<UserAccount> adminAccount = userAccountRepository.findByUsername(adminUsername);

        if (adminAccount.isPresent()) return;

        Role adminRole = roleService.saveOrGet(UserRole.ROLE_ADMIN);
        Role merchantRole = roleService.saveOrGet(UserRole.ROLE_MERCHANT);
        Role customerRole = roleService.saveOrGet(UserRole.ROLE_CUSTOMER);

        userAccountRepository.saveAndFlush(
                UserAccount.builder()
                        .username(adminUsername)
                        .password(passwordEncoder.encode(adminPassword))
                        .roles(List.of(adminRole, merchantRole, customerRole))
                        .build());
    }

    @Override
    public RegisterResponse registerCustomer(RegisterCustomerRequest request) {
        validationUtil.validate(request);
        Role role = roleService.saveOrGet(UserRole.ROLE_CUSTOMER);
        return getRegisterResponse(request, role);
    }

    @Override
    public RegisterResponse registerMerchant(RegisterMerchantRequest request) {
        validationUtil.validate(request);
        Role role = roleService.saveOrGet(UserRole.ROLE_MERCHANT);
        return getRegisterResponse(request, role);
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        validationUtil.validate(request);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.username(), request.password());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        UserAccount userAccount = (UserAccount) authenticate.getPrincipal();
        String token = jwtService.generateToken(userAccount);
        return new LoginResponse(userAccount.getUsername(), token, userAccount.getRoles().stream().map(role -> role.getRole().name()).toList());
    }

    @Override
    public Boolean validateToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'validateToken'");
    }

    private RegisterResponse getRegisterResponse(RegisterCustomerRequest request, Role role) {
        UserAccount userAccount = UserAccount.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .roles(List.of(role))
                .build();
        userAccountRepository.saveAndFlush(userAccount);
        CustomerRequest customerRequest = new CustomerRequest(
                request.name(),
                request.address(),
                request.phone(),
                userAccount);
        return registerCustomerResponseMapper.apply(customerService.saveCustomer(customerRequest));
    }

    private RegisterResponse getRegisterResponse(RegisterMerchantRequest request, Role role) {
        UserAccount userAccount = UserAccount.builder()
                .username(request.username())
                .password(passwordEncoder.encode(request.password()))
                .roles(List.of(role))
                .build();
        userAccountRepository.saveAndFlush(userAccount);
        MerchantRequest merchantRequest = new MerchantRequest(
                request.shopName(),
                request.phone(),
                request.address(),
                userAccount);
        return registerMerchantResponseMapper.apply(merchantService.saveMerchant(merchantRequest));
    }

}
