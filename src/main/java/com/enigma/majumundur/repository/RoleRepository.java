package com.enigma.majumundur.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enigma.majumundur.constant.UserRole;
import com.enigma.majumundur.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(UserRole role);
}
