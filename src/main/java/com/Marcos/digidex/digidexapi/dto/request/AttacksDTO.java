package com.Marcos.digidex.digidexapi.dto.request;

import com.Marcos.digidex.digidexapi.enums.ElementalAttributes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttacksDTO {

    private Long id;

    @NotEmpty
    @Size(min = 2,max = 100)
    private String name;

    @NotEmpty
    private String imageUrl;

    @Min(value = 0)
    private int baseDamage;

    @Min(value = 0)
    private int multiply;

    @Enumerated(EnumType.STRING)
    private ElementalAttributes elementalAttribute;

    @Min(value = 0)
    private int cooldown;

    @Min(value = 0)
    private int dsConsumed;

}
