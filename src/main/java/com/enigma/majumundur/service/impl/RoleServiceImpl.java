package com.enigma.majumundur.service.impl;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.entity.Role;
import com.enigma.majumundur.repository.RoleRepository;
import com.enigma.majumundur.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role saveOrGet(UserRole userRole) {
        return roleRepository.findByRole(userRole).orElseGet(() -> roleRepository.saveAndFlush(Role.builder().role(userRole).build()));
    }
}
