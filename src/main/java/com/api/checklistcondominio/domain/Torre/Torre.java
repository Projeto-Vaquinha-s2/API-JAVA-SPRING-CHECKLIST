package com.api.checklistcondominio.domain.Torre;

import com.api.checklistcondominio.domain.condominio.Condominio;
import com.api.checklistcondominio.domain.condominio.CondominioRepository;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Table(name = "torre")
@Entity(name = "Torre")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Torre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominio_id")
    private Condominio condominio;

    private int numero_torre;

    private int quantidade_andares;
    private int quantidade_garagens;
    private int quantidade_salao_de_festas;
    private int quantidade_guaritas;
    private int quantidade_terracos;
    private Boolean ativo;

    public Torre(DadosCadastroTorre dados, Long condominioId, CondominioRepository condominioRepository) {
        this.condominio = condominioRepository.findById(condominioId)
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        this.numero_torre = dados.numero_torre();
        this.quantidade_andares = dados.quantidade_andares();
        this.quantidade_garagens = dados.quantidade_garagens();
        this.quantidade_salao_de_festas = dados.quantidade_salao_de_festas();
        this.quantidade_guaritas = dados.quantidade_guaritas();
        this.quantidade_terracos = dados.quantidade_terracos();
        this.ativo = true;
    }

    public void atualizarInformacoes(DadosAtualizacaoTorre dados, Condominio condominio) {
        this.condominio = condominio;
        this.numero_torre = dados.numero_torre();
        this.quantidade_andares = dados.quantidade_andares();
        this.quantidade_garagens = dados.quantidade_garagens();
        this.quantidade_salao_de_festas = dados.quantidade_salao_de_festas();
        this.quantidade_guaritas = dados.quantidade_guaritas();
        this.quantidade_terracos = dados.quantidade_terracos();
    }

    public void excluir() {
        this.ativo = false;
    }
}
