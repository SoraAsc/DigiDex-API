package com.Marcos.digidex.digidexapi.controllers;

import com.Marcos.digidex.digidexapi.dto.request.DigimonDTO;
import com.Marcos.digidex.digidexapi.entities.Digimon;
import com.Marcos.digidex.digidexapi.services.DigiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping("/api/v1/digimon")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class DigiController {

    private DigiService digiService;

    @PostMapping("/create")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Digimon createDigimon(@RequestBody @Valid DigimonDTO digimonDTO){
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
    public DigimonDTO findDigiById(@PathVariable Long id){
        return digiService.findById(id);
    }

    @PutMapping("/{id}")
    public void updateById(@PathVariable Long id, @RequestBody @Valid DigimonDTO digimonDTO){
        digiService.update(id,digimonDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
       digiService.delete(id);
    }

}
