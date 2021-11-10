package com.Marcos.digidex.digidexapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class DigiDupFoundException extends Exception{

    public DigiDupFoundException(String id){
        super("Digimon with Name: "+id+" already exist");
    }

}
