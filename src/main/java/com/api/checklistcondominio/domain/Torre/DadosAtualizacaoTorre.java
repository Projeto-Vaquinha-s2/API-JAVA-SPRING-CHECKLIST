package com.api.checklistcondominio.domain.Torre;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTorre(

        long id,
        @NotNull
        Long condominio_id,
        @NotNull
        int numero_torre,
        @NotNull
        int quantidade_andares,
        @NotNull
        int quantidade_garagens,
        @NotNull
        int quantidade_salao_de_festas,
        @NotNull
        int quantidade_guaritas,
        @NotNull
        int quantidade_terracos

) {
}
