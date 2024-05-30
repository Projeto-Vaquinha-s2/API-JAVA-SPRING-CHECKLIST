package com.api.checklistcondominio.domain.condominio;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCondominio(
        @NotNull
        Long id,
        String nome,
        String cnpj,
        String bairro,
        String cep,
        String complemento,
        String numero,
        String uf,
        String cidade,
        int quantidade_torres) {


}
