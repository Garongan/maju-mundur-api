package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.StatusMessage;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.enigma.majumundur.entity.UserAccount;
import com.enigma.majumundur.repository.UserAccountRepository;
import com.enigma.majumundur.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAccountRepository userAccountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(StatusMessage.USERNAME_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getByUserId(String id) {
        return userAccountRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, StatusMessage.USERNAME_NOT_FOUND));
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getByContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userAccountRepository
                .findByUsername(authentication.getPrincipal().toString())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, StatusMessage.USERNAME_NOT_FOUND));
    }

}
