package com.Marcos.digidex.digidexapi.dto.mapper;

import com.Marcos.digidex.digidexapi.dto.request.DigimonDTO;
import com.Marcos.digidex.digidexapi.dto.request.DigimonDTOWithoutValidation;
import com.Marcos.digidex.digidexapi.entities.Digimon;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DigimonMapper {

    DigimonMapper INSTANCE = Mappers.getMapper(DigimonMapper.class);

    Digimon toEntity(DigimonDTO digimonDTO);

    DigimonDTO DTOWithoutValidationToDTO(DigimonDTOWithoutValidation digimonDTOWithoutValidation);

    DigimonDTO toDTO(Digimon digimon);

}
