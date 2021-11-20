package com.Marcos.digidex.digidexapi.services;

import com.Marcos.digidex.digidexapi.dto.mapper.DigimonMapper;
import com.Marcos.digidex.digidexapi.dto.request.DigimonDTO;
import com.Marcos.digidex.digidexapi.dto.request.DigimonDTOWithoutValidation;
import com.Marcos.digidex.digidexapi.entities.Digimon;
import com.Marcos.digidex.digidexapi.exceptions.DigiDupFoundException;
import com.Marcos.digidex.digidexapi.exceptions.DigiNotFoundException;
import com.Marcos.digidex.digidexapi.repositories.DigiRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DigiService {

    private DigiRepository digiRepository;

    private final DigimonMapper digimonMapper = DigimonMapper.INSTANCE;

    //Create new digi
    public DigimonDTO createDigimon(DigimonDTO digimonDTO) throws DigiDupFoundException, DigiNotFoundException {
        verifyIfNameIsDup(digimonDTO.getName(), null);
        PickEvolutionImage(digimonDTO);
        digiRepository.save(digimonMapper.toEntity(digimonDTO));
        return digimonDTO;
    }

    //List all digi
    public List<DigimonDTO> listAll(String orderBy){
        boolean reversed = orderBy != null && orderBy.equals("desc");

        List<Digimon> allDigi = SortDigiListByName( digiRepository.findAll(), reversed );

        return allDigi.stream().map(digimonMapper::toDTO).collect(Collectors.toList());
    }

    //List 6 per page
    public List<DigimonDTO> listByPage(Integer page, String orderBy) {
        boolean reversed = orderBy != null && orderBy.equals("desc");
        List<Digimon> allDigi = SortDigiListByName( digiRepository.findAll(), reversed );
        int digiListSize = allDigi.size();

        List<Digimon> allDigiInPage = Optional.of(
                allDigi.subList(
                        Math.min(digiListSize, page * 6),
                        Math.min(digiListSize, 6 * (page + 1))
                )
        ).orElse(new ArrayList<>(Collections.emptyList()));
        return allDigiInPage.stream()
                .map(digimonMapper::toDTO)
                .collect(Collectors.toList());
    }

    //List digi with specific id
    public DigimonDTO findById(String id) throws DigiNotFoundException {
        try {
            return digimonMapper.toDTO(verifyIfExists(Long.parseLong(id)));
        } catch (NumberFormatException e){
            return digimonMapper.toDTO(verifyIfExists(id));
        }
    }

    public void update(Long id, DigimonDTOWithoutValidation digimonDTOW) throws DigiNotFoundException, DigiDupFoundException {
        Digimon digimon = verifyIfExists(id);
        DigimonDTO digimonDTO = digimonMapper.DTOWithoutValidationToDTO(digimonDTOW);

        replaceNotNullProperties(digimon,digimonDTO);
        digimonDTO.setId(id);
        verifyIfNameIsDup(digimonDTO.getName(),id);

        PickEvolutionImage(digimonDTO);

        System.out.println(digimonDTO);
        digiRepository.save(digimonMapper.toEntity(digimonDTO));
    }

    public void delete(Long id) throws DigiNotFoundException {
        verifyIfExists(id);
        digiRepository.deleteById(id);
    }

    private List<Digimon> SortDigiListByName(List<Digimon> digimon,boolean isReversed){
        if(isReversed)
            digimon.sort(Collections.reverseOrder());
        else Collections.sort(digimon);
        return digimon;
    }

    private Digimon verifyIfExists(String id) throws DigiNotFoundException {
        return digiRepository.findByName(id).orElseThrow( () -> new DigiNotFoundException(id));
    }

    private Digimon verifyIfExists(Long id) throws DigiNotFoundException{
        return digiRepository.findById(id).orElseThrow(() -> new DigiNotFoundException(id));
    }

    private void verifyIfNameIsDup(String name, Long id) throws DigiDupFoundException {
        Optional<Digimon> dupDigi = digiRepository.findByName(name);
        if(dupDigi.isPresent() && (id==null || !dupDigi.get().getId().equals(id)) ){
            throw new DigiDupFoundException(name);
        }
    }

    private void PickEvolutionImage(DigimonDTO digimonDTO) throws DigiNotFoundException {
        if (digimonDTO.getNextEvolutionImageUrl() == null && digimonDTO.getNextEvolutionId()>=1) {
            digimonDTO.setNextEvolutionImageUrl(verifyIfExists(digimonDTO.getNextEvolutionId()).getImageUrl()); //Next
        }
        if (digimonDTO.getPreviousEvolutionImageUrl() == null && digimonDTO.getPreviousEvolutionId()>=1) {
            Digimon digimon = verifyIfExists(digimonDTO.getPreviousEvolutionId()); //Ant
            digimonDTO.setLv(digimon.getNextEvolutionLv());
            digimonDTO.setPreviousEvolutionImageUrl(digimon.getImageUrl());
        }
    }

    private void replaceNotNullProperties(Object source, DigimonDTO target) {
        List<Method> allDigiGetMethods = Arrays.stream(DigimonDTOWithoutValidation.class.getDeclaredMethods())
                .filter(method -> method.getName().contains("get") && !method.getName().contains("Id"))
                .collect(Collectors.toList());

        for (Method digiGetMethod: allDigiGetMethods ) {
            String getMethodName = digiGetMethod.getName();
            try {
                if(target.getClass().getMethod(getMethodName).invoke(target) == null) {
                    String setMethodName = getMethodName.replace("get","set");

                    Method method = source.getClass().getMethod(getMethodName);
                    target.getClass().getMethod(setMethodName, method.getReturnType() ).invoke(target, method.invoke(source));
                }
            } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
                System.out.println("Error while parsing DTO Without Validation to DTO");
            }
        }
    }

}
