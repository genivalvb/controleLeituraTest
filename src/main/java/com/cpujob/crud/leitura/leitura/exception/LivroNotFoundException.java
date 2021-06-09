package com.cpujob.crud.leitura.leitura.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LivroNotFoundException extends Exception{

    public LivroNotFoundException(String livroName){
        super(String.format("Livro with name %s not found in the system.", livroName));
    }

    public LivroNotFoundException(Long id){
        super(String.format("Livro with id %s not found in the system.", id));
    }
}
