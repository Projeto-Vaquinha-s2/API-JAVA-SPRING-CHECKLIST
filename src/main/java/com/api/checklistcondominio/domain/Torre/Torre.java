package com.api.checklistcondominio.domain.Torre;

import com.api.checklistcondominio.domain.condominio.Condominio;
import com.api.checklistcondominio.domain.condominio.CondominioRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


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

    private int quantidade_andares;
    private int quantidade_garagens;
    private int quantidade_salao_de_festas;
    private int quantidade_guaritas;
    private int quantidade_terracos;
    private Boolean ativo;

    private int numero_torre;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public int getNumero_torre() {
        return numero_torre;
    }

    public void setNumero_torre(int numero_torre) {
        this.numero_torre = numero_torre;
    }

    public int getQuantidade_andares() {
        return quantidade_andares;
    }

    public void setQuantidade_andares(int quantidade_andares) {
        this.quantidade_andares = quantidade_andares;
    }

    public int getQuantidade_salao_de_festas() {
        return quantidade_salao_de_festas;
    }

    public void setQuantidade_salao_de_festas(int quantidade_salao_de_festas) {
        this.quantidade_salao_de_festas = quantidade_salao_de_festas;
    }

    public int getQuantidade_garagens() {
        return quantidade_garagens;
    }

    public void setQuantidade_garagens(int quantidade_garagens) {
        this.quantidade_garagens = quantidade_garagens;
    }

    public int getQuantidade_guaritas() {
        return quantidade_guaritas;
    }

    public void setQuantidade_guaritas(int quantidade_guaritas) {
        this.quantidade_guaritas = quantidade_guaritas;
    }

    public int getQuantidade_terracos() {
        return quantidade_terracos;
    }

    public void setQuantidade_terracos(int quantidade_terracos) {
        this.quantidade_terracos = quantidade_terracos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

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
