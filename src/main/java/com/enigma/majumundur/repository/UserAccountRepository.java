package com.enigma.majumundur.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.enigma.majumundur.entity.UserAccount;

import java.util.Optional;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, String> {
    @Cacheable("userAccounts")
    Optional<UserAccount> findByUsername(String name);
}
