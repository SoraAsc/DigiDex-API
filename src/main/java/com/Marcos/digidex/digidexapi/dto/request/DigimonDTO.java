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
public class DigimonDTO {

    private Long id;

    @NotEmpty
    @Size(min = 1,max = 60)
    private String name;

    @NotEmpty
    private String imageUrl;

    @Min(value = 0)
    private int hp; //Health Points

    @Min(value = 0)
    private int ds; //Digi-Soul

    @Min(value = 0)
    private int at; //Attack

    @Min(value = 0)
    private float as; //Attack Speed

    @Min(value = 0)
    @Max(value = 1)
    private float ct; //Critical Hit %

    @Min(value = 0)
    private int ht; //Hit Rate

    @Min(value = 0)
    private int de; //Defense

    @Min(value = 0)
    @Max(value = 1)
    private float ev; //Evade %

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


    private Long previousEvolutionId;


    private Long nextEvolutionId;

    @NotNull
    @Valid
    private List<Attacks> attacks;

}
