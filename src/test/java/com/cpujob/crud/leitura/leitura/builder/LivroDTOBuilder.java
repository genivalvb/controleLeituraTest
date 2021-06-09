package com.cpujob.crud.leitura.leitura.builder;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.cpujob.crud.leitura.leitura.enums.LivroType;
import lombok.Builder;
import com.cpujob.crud.leitura.leitura.dto.LivroDTO;


@Builder
public class LivroDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nome = "Pai Rico Pai Pobre";

    @Builder.Default
    private String autor = "Robert T. Kiyosaki";

    @Builder.Default
    private String dataLeitura = "13/05/2021";

    @Builder.Default
    private String descricao = "Melhor livro de finanças para abertura de mente";

    @Builder.Default
    private LivroType status = LivroType.LIDO;



    public LivroDTO toLivroDTO(){
        return new LivroDTO(id,
                nome,
                autor,
                dataLeitura,
                descricao,
                status
                );
    }
}
