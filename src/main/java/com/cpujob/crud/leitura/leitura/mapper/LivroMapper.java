package com.cpujob.crud.leitura.leitura.mapper;

import com.cpujob.crud.leitura.leitura.dto.LivroDTO;
import com.cpujob.crud.leitura.leitura.model.Livro;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LivroMapper {
    LivroMapper INSTANCE = Mappers.getMapper(LivroMapper.class);

    Livro toModel(LivroDTO livroDTO);

    LivroDTO toDTO(Livro livro);
}
