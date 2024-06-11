package com.api.checklistcondominio.domain.registro;

import java.time.LocalDateTime;

public record DadosListagemRegistro(Long id, Long condominioId, Long torreId, String foto_path, LocalDateTime data_do_registro) {
    public DadosListagemRegistro(Registro registro){
        this(registro.getId(), registro.getCondominio().getId(), registro.getTorre().getId(), registro.getFoto_path(), registro.getData_do_registro());
    }
}
