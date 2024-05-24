package com.api.checklistcondominio.domain.condominio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCondominio(
        @NotBlank
        String nome,
        @NotBlank
        String cnpj,
        @NotBlank
        String bairro,
        @NotBlank
        String cep,
        @NotBlank
        String complemento,
        @NotBlank
        String numero,
        @NotBlank
        String uf,
        @NotBlank
        String cidade,
        @NotNull
        int quantidade_torres
) {
}
