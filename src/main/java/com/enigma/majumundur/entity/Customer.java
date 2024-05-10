package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.TableName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = TableName.M_CUSTOMER)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "phone", unique = true, nullable = false, length = 15)
    private String phone;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "point", nullable = true)
    private Integer point;

    @OneToOne
    @JoinColumn(name = "user_account_id", unique = true, nullable = false)
    private UserAccount userAccount;
}
