package com.cpujob.crud.leitura.leitura.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LivroAlreadyRegisteredException extends Exception{

    public LivroAlreadyRegisteredException(String livroName){
        super(String.format("Livro with name %s already registered in the system.", livroName));
    }
}
