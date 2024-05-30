package com.api.checklistcondominio.domain.administrador;

public record DadosListagemAdministrador(Long id, String nome, String email, String cpf) {
    public DadosListagemAdministrador(Administrador administrador){
        this(administrador.getId(), administrador.getNome(), administrador.getEmail(), administrador.getCpf());
    }
}
