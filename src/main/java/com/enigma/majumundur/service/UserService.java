package com.enigma.majumundur.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.enigma.majumundur.entity.UserAccount;

public interface UserService extends UserDetailsService {
    UserAccount getByUserId(String id);

    UserAccount getByContext();
}
