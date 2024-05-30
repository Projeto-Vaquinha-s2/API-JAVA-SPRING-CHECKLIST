package com.api.checklistcondominio.domain.registro;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroRegistro(
        Long idCondominio,
        Long idTorre,
        @NotNull
        String foto_path,
        @NotNull
        LocalDateTime data_do_registro,
        @NotNull
        String descricao_problema,
        @NotNull
        TipoProblema tipo_problema

) {
}
