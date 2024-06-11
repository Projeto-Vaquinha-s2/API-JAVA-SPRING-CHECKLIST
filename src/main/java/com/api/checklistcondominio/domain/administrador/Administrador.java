package com.api.checklistcondominio.domain.administrador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "administrador")
@Entity(name = "Administrador")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Administrador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;

    private Boolean ativo;

    public Administrador(DadosCadastroAdministrador dados){
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();

        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoAdministrador dados){
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.email() != null) {
            this.email = dados.email();
        }
        if (dados.cpf() != null) {
            this.cpf = dados.cpf();
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
