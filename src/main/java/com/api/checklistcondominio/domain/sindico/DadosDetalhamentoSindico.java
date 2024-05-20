package com.api.checklistcondominio.domain.sindico;

public record DadosDetalhamentoSindico(Long id, String nome, String email, String cpf, Boolean ativo) {
    public DadosDetalhamentoSindico (Sindico sindico) {
        this(sindico.getId(),sindico.getNome(), sindico.getEmail(), sindico.getCpf(), sindico.getAtivo() );
    }
}
