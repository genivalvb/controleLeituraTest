package com.cpujob.crud.leitura.leitura.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LivroType {
    LIDO("Lido"),
    NAOLIDO("Não Lido"),
    ASERLIDO("A ser Lido");

    private final String description;
}
