package com.api.checklistcondominio.domain.registro;

import java.time.LocalDateTime;

public record DadosListagemRegistro(Long id, Long condominioId, Long torreId, LocalDateTime data_do_registro, String descricao_problema, TipoProblema tipo_problema) {
    public DadosListagemRegistro(Registro registro){
        this(registro.getId(), registro.getCondominio().getId(), registro.getTorre().getId(), registro.getData_do_registro(), registro.getDescricao_problema(), registro.getTipo_problema());
    }
}
