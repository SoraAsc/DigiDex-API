package com.Marcos.digidex.digidexapi.controllers;

import com.Marcos.digidex.digidexapi.dto.request.DigimonDTO;
import com.Marcos.digidex.digidexapi.dto.request.DigimonDTOWithoutValidation;
import com.Marcos.digidex.digidexapi.exceptions.DigiDupFoundException;
import com.Marcos.digidex.digidexapi.exceptions.DigiNotFoundException;
import com.Marcos.digidex.digidexapi.services.DigiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/digimon")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DigiController {

    private DigiService digiService;

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public DigimonDTO createDigimon(@RequestBody @Valid DigimonDTO digimonDTO) throws DigiNotFoundException, DigiDupFoundException {
        return digiService.createDigimon(digimonDTO);
    }

    @GetMapping
    public List<DigimonDTO> listAll(@RequestParam(required = false) String page,
                                    @RequestParam(required = false) String orderBy){
        if(page!= null) {
            String pageNum = page.replaceAll("[^0-9]", "");
            return pageNum.isEmpty() ? digiService.listByPage(0,orderBy) : digiService.listByPage(Integer.parseInt(pageNum), orderBy);
        }
        return digiService.listAll(orderBy);
    }

    @GetMapping("/{id}")
    public DigimonDTO findDigiById(@PathVariable String id) throws DigiNotFoundException {
        return digiService.findById(id);
    }

    @PatchMapping("/{id}")
    public void updateById(@PathVariable Long id, @RequestBody @Valid DigimonDTOWithoutValidation digimonDTOW) throws DigiNotFoundException, DigiDupFoundException {
        digiService.update(id,digimonDTOW);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) throws DigiNotFoundException {
       digiService.delete(id);
    }

}
