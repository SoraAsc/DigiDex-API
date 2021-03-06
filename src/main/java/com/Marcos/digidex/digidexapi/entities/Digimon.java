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
public class Digimon implements Comparable<Digimon> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String imageUrl;

    @Column(nullable = false)
    private Integer hp; //Health Points
    @Column(nullable = false)
    private Integer ds; //Digi-Soul
    @Column(nullable = false)
    private Integer at; //Attack
    @Column(nullable = false)
    private Float ats; //Attack Speed
    @Column(nullable = false)
    private Float ct; //Critical Hit %
    @Column(nullable = false)
    private Integer ht; //Hit Rate
    @Column(nullable = false)
    private Integer de; //Defense
    @Column(nullable = false)
    private Float ev; //Evade %

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

    @ElementCollection(targetClass = Families.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "Families", joinColumns = @JoinColumn(name = "id"))
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Families> families;

    @Column
    private Integer lv;

    @Column
    private Long previousEvolutionId;
    @Column
    private String previousEvolutionImageUrl;

    @Column
    private Long nextEvolutionId;
    @Column
    private Integer nextEvolutionLv;
    @Column
    private String nextEvolutionImageUrl;


    @OneToMany(targetEntity = Attacks.class,
            fetch = FetchType.LAZY,cascade = {CascadeType.ALL},
            orphanRemoval = true)
    private List<Attacks> attacks;


    @Override
    public int compareTo(Digimon d2) {
        return this.getName().compareToIgnoreCase(d2.getName());
    }
}
