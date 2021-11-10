package com.Marcos.digidex.digidexapi.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class DigiNotFoundException extends Exception{

    public DigiNotFoundException(Long id){
        super("Digimon not found with ID: "+id);
    }

    public DigiNotFoundException(String id){
        super("Digimon not found with Name: "+id);
    }

}

