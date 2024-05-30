package com.api.checklistcondominio.domain.condominio;

public record DadosDetalhamentoCondominio(Long id, String nome, String cnpj, String bairro, String cep, String complemento, String numero, String uf, String cidade, int quantidade_torres, Boolean ativo) {
    public DadosDetalhamentoCondominio(Condominio condominio){
        this(condominio.getId(), condominio.getNome(), condominio.getCnpj(), condominio.getBairro(), condominio.getCep(), condominio.getComplemento(), condominio.getNumero(), condominio.getUf(), condominio.getCidade(), condominio.getQuantidade_torres(), condominio.getAtivo());
    }
}
