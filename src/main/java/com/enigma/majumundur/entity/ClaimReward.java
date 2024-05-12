package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.TableName;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = TableName.CLAIM_REWARD)
public class ClaimReward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "claim_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    private Reward reward;
}
