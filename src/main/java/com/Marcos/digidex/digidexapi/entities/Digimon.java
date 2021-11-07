package com.Marcos.digidex.digidexapi.entities;

import com.Marcos.digidex.digidexapi.enums.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Digimon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private int hp; //Health Points
    @Column(nullable = false)
    private int ds; //Digi-Soul
    @Column(nullable = false)
    private int at; //Attack
    @Column(nullable = false)
    private float as; //Attack Speed
    @Column(nullable = false)
    private float ct; //Critical Hit %
    @Column(nullable = false)
    private int ht; //Hit Rate
    @Column(nullable = false)
    private int de; //Defense
    @Column(nullable = false)
    private float ev; //Evade %

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Forms form;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Attributes attribute;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ElementalAttributes elementalAttribute;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AttackerTypes attackerType;

    @Column
    private String type;

    @ElementCollection(targetClass = Families.class)
    @CollectionTable(name = "Families", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private List<Families> families;

    @Column
    private Long previousEvolutionId;

    @Column
    private Long nextEvolutionId;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
    private List<Attacks> attacks;


}
