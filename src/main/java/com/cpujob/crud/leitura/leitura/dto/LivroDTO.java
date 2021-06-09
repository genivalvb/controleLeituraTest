package com.cpujob.crud.leitura.leitura.dto;

import com.cpujob.crud.leitura.leitura.enums.LivroType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivroDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String name;

    @NotNull
    @Size(min = 1, max = 200)
    private String autor;

    @NotNull
    @Max(8)
    private String dataLeitura;

    @NotNull
    @Max(500)
    private String descricao;

    @Enumerated(EnumType.STRING)
    @NotNull
    private LivroType status;
}
