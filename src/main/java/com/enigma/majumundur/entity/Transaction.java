package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.TableName;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = TableName.TRANSACTION)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "trans_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transDate;

    @OneToOne
    @JoinColumn(name = "customer_user_account_id", nullable = false, unique = true)
    private UserAccount customerUserAccountId;

    @OneToMany(mappedBy = "transaction")
    private List<TransactionDetail> transactionDetails;
}
