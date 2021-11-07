package com.Marcos.digidex.digidexapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Attributes {

    Da("Data Attribute"),
    Va("Vaccine Attribute"),
    Vi("Virus Attribute"),
    No("No Attribute"),
    Un("Unknown Attribute");

    private final String description;
}
