package com.enigma.majumundur.service;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.entity.Role;

public interface RoleService {
    Role saveOrGet(UserRole userRole);
}
