package com.Marcos.digidex.digidexapi.entities;

import com.Marcos.digidex.digidexapi.enums.ElementalAttributes;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attacks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private int baseDamage;

    @Column(nullable = true)
    private int multiply;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ElementalAttributes elementalAttribute;

    @Column
    private int cooldown;

    @Column
    private int dsConsumed;
}
