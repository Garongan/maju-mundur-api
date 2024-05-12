package com.enigma.majumundur.repository;

import com.enigma.majumundur.dto.response.TransactionResponse;
import com.enigma.majumundur.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    @Query(value = """
            SELECT * FROM transaction AS t
            JOIN transaction_detail AS td ON t.id = td.transaction_id
            JOIN product AS p ON td.product_id = p.id
            JOIN m_merchant AS m ON p.merchant_id = m.id
            WHERE m.id = :merchant_id
            """,
            nativeQuery = true)
    List<TransactionResponse> getMerchantHistoryTransaction(@Param("merchant_id") String merchant_id);
}
