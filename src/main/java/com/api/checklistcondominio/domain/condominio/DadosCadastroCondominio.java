package com.api.checklistcondominio.domain.condominio;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroCondominio(
        @NotBlank
        String nome,
        @NotBlank
        @Pattern(regexp = "/^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$/")
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
        @NotBlank
        int quantidade_torres
) {
}
