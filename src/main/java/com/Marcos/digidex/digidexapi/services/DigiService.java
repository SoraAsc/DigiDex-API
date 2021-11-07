package com.Marcos.digidex.digidexapi.services;

import com.Marcos.digidex.digidexapi.dto.request.DigimonDTO;
import com.Marcos.digidex.digidexapi.entities.Digimon;
import com.Marcos.digidex.digidexapi.repositories.DigiRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DigiService {

    private DigiRepository digiRepository;

    //Create new digi
    public Digimon createDigimon(DigimonDTO digimonDTO){
        Digimon digimon = digiRepository.save(ConvertDigimonDTOEntity(digimonDTO));
        return digimon;
    }

    //List all digi
    public List<DigimonDTO> listAll(String orderBy){
        boolean reversed = orderBy!=null && orderBy.equals("desc") ? true : false;
        //if(orderBy!=null ){ reversed = orderBy.equals("desc") ? true : false; }
        List<Digimon> allDigi = SortDigiListByName( digiRepository.findAll(), reversed );

        return allDigi.stream()
                .map( digimon -> ConvertDigimonEntityToDTO(digimon) )
                .collect(Collectors.toList());
    }

    //List 6 per page
    public List<DigimonDTO> listByPage(Integer page, String orderBy) {
        boolean reversed = orderBy!=null && orderBy.equals("desc") ? true : false;
        List<Digimon> allDigi = SortDigiListByName( digiRepository.findAll(), reversed );
        int digiListSize = allDigi.size();

        List<Digimon> allDigiInPage = Optional.of(
                allDigi.subList(
                        digiListSize > page * 6 ? page * 6 : digiListSize,
                        digiListSize > 6 * (page + 1) ? 6 * (page + 1) : digiListSize
                )
        ).orElse(new ArrayList<Digimon>(Collections.EMPTY_LIST));

        return allDigiInPage.stream()
                .map(digimon -> ConvertDigimonEntityToDTO(digimon))
                .collect(Collectors.toList());
    }

    //List digi with specific Id
    public DigimonDTO findById(Long id){
        return ConvertDigimonEntityToDTO(digiRepository.findById(id).orElse(null) );
    }

    public void update(Long id, DigimonDTO digimonDTO){
        digiRepository.save(ConvertDigimonDTOEntity(digimonDTO));
    }

    public void delete(Long id){
        digiRepository.deleteById(id);
    }

    private DigimonDTO ConvertDigimonEntityToDTO(Digimon digimon){
        return digimon!=null ? DigimonDTO.builder()
                .name(digimon.getName())
                .imageUrl(digimon.getImageUrl())
                .hp(digimon.getHp())
                .ds(digimon.getDs())
                .at(digimon.getAt())
                .as(digimon.getAs())
                .ct(digimon.getCt())
                .ht(digimon.getHt())
                .de(digimon.getDe())
                .ev(digimon.getEv())
                .form(digimon.getForm())
                .attribute(digimon.getAttribute())
                .elementalAttribute(digimon.getElementalAttribute())
                .attackerType(digimon.getAttackerType())
                .type(digimon.getType())
                .families(digimon.getFamilies())
                .previousEvolutionId(digimon.getPreviousEvolutionId())
                .nextEvolutionId(digimon.getNextEvolutionId())
                .attacks(digimon.getAttacks())
                .build() : null;
    }

    private Digimon ConvertDigimonDTOEntity(DigimonDTO digimonDTO){
        return digimonDTO!=null ? Digimon.builder()
                .name(digimonDTO.getName())
                .imageUrl(digimonDTO.getImageUrl())
                .hp(digimonDTO.getHp())
                .ds(digimonDTO.getDs())
                .at(digimonDTO.getAt())
                .as(digimonDTO.getAs())
                .ct(digimonDTO.getCt())
                .ht(digimonDTO.getHt())
                .de(digimonDTO.getDe())
                .ev(digimonDTO.getEv())
                .form(digimonDTO.getForm())
                .attribute(digimonDTO.getAttribute())
                .elementalAttribute(digimonDTO.getElementalAttribute())
                .attackerType(digimonDTO.getAttackerType())
                .type(digimonDTO.getType())
                .families(digimonDTO.getFamilies())
                .previousEvolutionId(digimonDTO.getPreviousEvolutionId())
                .nextEvolutionId(digimonDTO.getNextEvolutionId())
                .attacks(digimonDTO.getAttacks())
                .build() : null;
    }

    private List<Digimon> SortDigiListByName(List<Digimon> digimon,boolean isReversed){
        if(isReversed)
            Collections.sort(digimon, Collections.reverseOrder());
        else Collections.sort(digimon);
        return digimon;
    }

}
