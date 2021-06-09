package com.cpujob.crud.leitura.leitura.controller;

import com.cpujob.crud.leitura.leitura.dto.LivroDTO;
import com.cpujob.crud.leitura.leitura.exception.LivroAlreadyRegisteredException;
import com.cpujob.crud.leitura.leitura.exception.LivroNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api("Manages livro stock")
public interface LivroControllersDocs {

    @ApiOperation(value = "Livro creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Success livro creation"),
            @ApiResponse(code = 400, message = "Missing required fields or wrong field range value.")
    })
    LivroDTO createLivro(LivroDTO livroDTO) throws LivroAlreadyRegisteredException;

    @ApiOperation(value = "Returns livro found by a given name")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success livro found in the system"),
            @ApiResponse(code = 404, message = "Livro with given name not found.")
    })
    LivroDTO findByName(@PathVariable String nome) throws LivroNotFoundException;

    @ApiOperation(value = "Returns a list of all livros registered in the system")
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = "List of all livros registered in the system"),
    })
    List<LivroDTO> listLivros();

    @ApiOperation( value = "Delete a livro found by a given valid ID")
    @ApiResponses( value = {
            @ApiResponse( code = 204, message = "Success livro deleted int the system"),
            @ApiResponse( code = 404, message = "Livro with given id not found")
    })
    void deleteById(@PathVariable Long id) throws LivroNotFoundException;
}
