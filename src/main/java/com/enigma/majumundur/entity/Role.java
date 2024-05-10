package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.TableName;
import com.enigma.majumundur.constant.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = TableName.M_USER_ROLE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
