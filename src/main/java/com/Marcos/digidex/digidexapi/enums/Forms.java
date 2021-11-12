package com.Marcos.digidex.digidexapi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Forms {

    In_Training("In-Training"),
    Rookie("Rookie"),
    Rookie_X("Rookie X"),
    Champion("Champion"),
    Champion_X("Champion X"),
    H_Hybrid("H-Hybrid"),
    Ultimate("Ultimate"),
    Ultimate_X("Ultimate X"),
    Armor("Armor"),
    B_Hybrid("B-Hybrid"),
    Mega("Mega"),
    Mega_X("Mega X"),
    Side_Mega("Side Mega"),
    Z_Hybrid("Z-Hybrid"),
    Burst_Mode("Burst Mode"),
    Burst_Mode_X("Burst Mode X"),
    Jogress("Jogress"),
    Jogress_X("Jogress X"),
    O_Hybrid("O-Hybrid");

    private final String description;
}
