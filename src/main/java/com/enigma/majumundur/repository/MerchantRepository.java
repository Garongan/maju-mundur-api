package com.enigma.majumundur.repository;

import com.enigma.majumundur.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MerchantRepository extends JpaRepository<Merchant, String> {
}
