package com.Marcos.digidex.digidexapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttackerTypes {

    SA("Short Attacker"),
    NA("Near Attacker"),
    QA("Quick Attacker"),
    DE("Defender");

    private final String description;

}
