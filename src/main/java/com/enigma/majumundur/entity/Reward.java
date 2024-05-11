package com.enigma.majumundur.entity;

import com.enigma.majumundur.constant.TableName;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = TableName.M_REWARD)
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "point_required", nullable = false)
    private Integer pointRequired;

    @Column(name = "stock", nullable = false)
    private Integer stock;

}
