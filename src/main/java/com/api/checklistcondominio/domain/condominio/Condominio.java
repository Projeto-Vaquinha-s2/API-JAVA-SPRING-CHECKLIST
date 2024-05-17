package com.api.checklistcondominio.domain.condominio;

import com.api.checklistcondominio.domain.sindico.DadosCadastroSindico;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Table(name = "condominio")
@Entity(name = "Condominio")
@Getter
@Setter

public class Condominio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cnpj;
    private String bairro;
    private String cep;
    private String complemento;
    private String numero;
    private String uf;
    private String cidade;
    private int quantidade_torres;

    private Boolean ativo;


    public Condominio(DadosCadastroCondominio dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.cnpj = dados.cnpj();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.complemento = dados.complemento();
        this.numero = dados.numero();
        this.uf = dados.uf();
        this.cidade = dados.cidade();
        this.quantidade_torres = dados.quantidade_torres();


    }
    public void atualizarInformacoes(DadosAtualizacaoCondominio dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.cnpj() != null) {
            this.cnpj = dados.cnpj();
        }
        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }
        if (dados.cep() != null) {
            this.cep = dados.cep();
        }
        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }
        if (dados.numero() != null) {
            this.numero = dados.numero();
        }
        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }
        if (dados.quantidade_torres() >= 1) {
            this.quantidade_torres = dados.quantidade_torres();
        }
    }
    public void excluir(){
        this.ativo = false;
    }

}