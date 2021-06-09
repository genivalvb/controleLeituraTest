package com.cpujob.crud.leitura.leitura.controller;

import com.cpujob.crud.leitura.leitura.dto.LivroDTO;
import com.cpujob.crud.leitura.leitura.exception.LivroAlreadyRegisteredException;
import com.cpujob.crud.leitura.leitura.exception.LivroNotFoundException;
import com.cpujob.crud.leitura.leitura.exception.ResourceNotFoundException;
import com.cpujob.crud.leitura.leitura.model.Livro;
import com.cpujob.crud.leitura.leitura.repository.LivroRepository;
import com.cpujob.crud.leitura.leitura.service.LivroService;
import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController @CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/livros")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LivroController implements LivroControllersDocs{

    private final LivroService livroService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LivroDTO createLivro(@RequestBody @Valid LivroDTO livroDTO) throws LivroAlreadyRegisteredException {
        return livroService.createLivro(livroDTO);
    }

    @GetMapping("/{name}")
    public LivroDTO findByName(@PathVariable String name) throws LivroNotFoundException {
        return livroService.findByName(name);
    }

    @GetMapping
    public List<LivroDTO> listLivros() {
        return livroService.listAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws LivroNotFoundException {
        livroService.deleteById(id);
    }

}
