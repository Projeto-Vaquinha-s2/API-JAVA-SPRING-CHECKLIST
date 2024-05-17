package com.api.checklistcondominio.domain.sindico;

public record DadosListagemSindico(Long id, String nome, String email, String cpf) {
    public DadosListagemSindico(Sindico sindico){
        this(sindico.getId(), sindico.getNome(), sindico.getEmail(), sindico.getCpf());
    }
}