package com.api.checklistcondominio.domain.Torre;

import jakarta.validation.constraints.NotNull;

public record DadosDetalhamentoTorre(
        @NotNull long id,
        Long condominioId,
        @NotNull int numero_torre,
        @NotNull int quantidade_andares,
        @NotNull int quantidade_garagens,
        @NotNull int quantidade_salao_de_festas,
        @NotNull int quantidade_guaritas,
        @NotNull int quantidade_terracos,
        Boolean ativo
) {
    public DadosDetalhamentoTorre(Torre torre) {
        this(torre.getId(),
                torre.getCondominio().getId(),
                torre.getNumero_torre(),
                torre.getQuantidade_andares(),
                torre.getQuantidade_garagens(),
                torre.getQuantidade_salao_de_festas(),
                torre.getQuantidade_guaritas(),
                torre.getQuantidade_terracos(),
                torre.getAtivo());
    }
}
