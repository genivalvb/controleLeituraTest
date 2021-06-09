package com.cpujob.crud.leitura.leitura.service;

import com.cpujob.crud.leitura.leitura.dto.LivroDTO;
import com.cpujob.crud.leitura.leitura.exception.LivroNotFoundException;
import com.cpujob.crud.leitura.leitura.mapper.LivroMapper;
import com.cpujob.crud.leitura.leitura.model.Livro;
import com.cpujob.crud.leitura.leitura.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cpujob.crud.leitura.leitura.exception.LivroAlreadyRegisteredException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LivroService {

    private final LivroRepository livroRepository;
    private final LivroMapper livroMapper = LivroMapper.INSTANCE;

    public LivroDTO createLivro(LivroDTO livroDTO) throws LivroAlreadyRegisteredException{
        verifyIsAlreadyRegistered(livroDTO.getName());
        Livro livro = livroMapper.toModel(livroDTO);
        Livro savedLivro = livroRepository.save(livro);
        return livroMapper.toDTO(savedLivro);
    }

    public LivroDTO findByName(String name) throws LivroNotFoundException {
        Livro foundLivro = livroRepository.findByName(name)
                .orElseThrow(() -> new LivroNotFoundException(name));
        return livroMapper.toDTO(foundLivro);
    }

    public List<LivroDTO> listAll() {
        return livroRepository.findAll()
                .stream()
                .map(livroMapper::toDTO)
                .collect(Collectors.toList());
    }

    public void deleteById(Long id) throws LivroNotFoundException {
        verifyIfExists(id);
        livroRepository.deleteById(id);
    }

    private void verifyIsAlreadyRegistered(String name) throws LivroAlreadyRegisteredException{
        Optional<Livro> optSavedLivro = livroRepository.findByName(name);
        if (optSavedLivro.isPresent()){
            throw new LivroAlreadyRegisteredException((name));
        }
    }

    private Livro verifyIfExists(Long id) throws LivroNotFoundException {
        return livroRepository.findById(id)
                .orElseThrow(() -> new LivroNotFoundException(id));
    }
}
