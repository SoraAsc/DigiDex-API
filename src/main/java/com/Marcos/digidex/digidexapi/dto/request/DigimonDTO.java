package com.Marcos.digidex.digidexapi.dto.request;

import com.Marcos.digidex.digidexapi.entities.Attacks;
import com.Marcos.digidex.digidexapi.enums.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DigimonDTO implements Comparable<DigimonDTO> {

    private Long id;

    @NotEmpty
    @Size(min = 1,max = 60)
    private String name;

    @NotEmpty
    @Size(min = 1,max = 255)
    private String imageUrl;

    @Min(value = 0)
    private Integer hp; //Health Points

    @Min(value = 0)
    private Integer ds; //Digi-Soul

    @Min(value = 0)
    private Integer at; //Attack

    @Min(value = 0)
    private Float ats; //Attack Speed

    @Min(value = 0)
    @Max(value = 1)
    private Float ct; //Critical Hit %

    @Min(value = 0)
    private Integer ht; //Hit Rate

    @Min(value = 0)
    private Integer de; //Defense

    @Min(value = 0)
    @Max(value = 1)
    private Float ev; //Evade %

    @Enumerated(EnumType.STRING)
    private Forms form;

    @Enumerated(EnumType.STRING)
    private Attributes attribute;

    @Enumerated(EnumType.STRING)
    private ElementalAttributes elementalAttribute;

    @Enumerated(EnumType.STRING)
    private AttackerTypes attackerType;

    @NotEmpty
    @Size(min = 2,max = 50)
    private String type;

    @ElementCollection(targetClass = Families.class)
    @CollectionTable(name = "Families", joinColumns = @JoinColumn(name = "id"))
    @Enumerated(EnumType.STRING)
    private List<Families> families;

    private Integer lv;

    private Long previousEvolutionId;
    private String previousEvolutionImageUrl;


    private Long nextEvolutionId;
    private Integer nextEvolutionLv;
    private String nextEvolutionImageUrl;


    @NotNull
    @Valid
    private List<Attacks> attacks;

    @Override
    public int compareTo(DigimonDTO d2) {
        return this.getName().compareToIgnoreCase(d2.getName());
    }
}
