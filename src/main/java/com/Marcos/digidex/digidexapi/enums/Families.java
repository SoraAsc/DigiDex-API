package com.Marcos.digidex.digidexapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Families {

    DS("Deep Savers"),
    DR("Dragon's Roar"),
    JT("Jungle Troopers"),
    ME("Metal Empire"),
    NS("Nature Spirits"),
    NSo("Nightmare Soldiers"),
    UK("Unknown"),
    VB("Virus Busters"),
    WG("Wind Guardians"),
    DA("Dark Area"),
    TBD("Any Family");

    private final String description;

}
