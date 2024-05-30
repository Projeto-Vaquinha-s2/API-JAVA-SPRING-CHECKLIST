package com.api.checklistcondominio.domain.administrador;

public record DadosDetalhamentoAdministrador(Long id, String nome, String email, String cpf, Boolean ativo) {
    public DadosDetalhamentoAdministrador(Administrador administrador){
        this(administrador.getId(), administrador.getNome(), administrador.getEmail(), administrador.getCpf(), administrador.getAtivo());
    }
}
