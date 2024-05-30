package com.api.checklistcondominio.domain.sindico;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoSindico(
        @NotNull
        Long id,
        String nome,
        String email,
        String cpf
) {
}
