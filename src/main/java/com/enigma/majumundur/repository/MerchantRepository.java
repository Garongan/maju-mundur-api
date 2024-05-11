package com.enigma.majumundur.repository;

import com.enigma.majumundur.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant, String> {
    Merchant findMerchantByUserAccountId(String userAccountId);
}
