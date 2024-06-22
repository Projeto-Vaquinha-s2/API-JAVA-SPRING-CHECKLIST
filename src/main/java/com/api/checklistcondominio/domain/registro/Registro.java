package com.api.checklistcondominio.domain.registro;


import com.api.checklistcondominio.domain.Torre.Torre;
import com.api.checklistcondominio.domain.Torre.TorreRepository;
import com.api.checklistcondominio.domain.condominio.Condominio;
import com.api.checklistcondominio.domain.condominio.CondominioRepository;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "registro")
@Entity(name = "Registro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Registro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condominio_id")
    private Condominio condominio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "torre_id")
    private Torre torre;

    private LocalDateTime data_do_registro;
    private String descricao_problema;

    @Enumerated(EnumType.STRING)
    private TipoProblema tipo_problema;

    private Boolean ativo;

    public Condominio getCondominio() {
        return condominio;
    }

    public void setCondominio(Condominio condominio) {
        this.condominio = condominio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Torre getTorre() {
        return torre;
    }

    public void setTorre(Torre torre) {
        this.torre = torre;
    }


    public LocalDateTime getData_do_registro() {
        return data_do_registro;
    }

    public void setData_do_registro(LocalDateTime data_do_registro) {
        this.data_do_registro = data_do_registro;
    }

    public TipoProblema getTipo_problema() {
        return tipo_problema;
    }

    public void setTipo_problema(TipoProblema tipo_problema) {
        this.tipo_problema = tipo_problema;
    }

    public String getDescricao_problema() {
        return descricao_problema;
    }

    public void setDescricao_problema(String descricao_problema) {
        this.descricao_problema = descricao_problema;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Registro(DadosCadastroRegistro dados, Long condominioId, CondominioRepository condominioRepository, Long torreId, TorreRepository torreRepository) {
        this.condominio = condominioRepository.findById(condominioId)
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        this.torre = torreRepository.findById(torreId)
                .orElseThrow(() -> new IllegalArgumentException("Torre não encontrada"));

        this.data_do_registro = dados.data_do_registro();
        this.tipo_problema = dados.tipo_problema();
        this.descricao_problema = dados.descricao_problema();
        this.ativo = true; // Definindo ativo como true por padrão
    }

    public Registro(DadosCadastroRegistro dados) {
    }


    public void atualizarInformacoes(@Valid DadosAtualizacaoRegistro dados, Long condominioId, CondominioRepository condominioRepository, Long torreId, TorreRepository torreRepository){
        this.condominio = condominioRepository.findById(condominioId)
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        this.torre = torreRepository.findById(torreId)
                .orElseThrow(() -> new IllegalArgumentException("Torre não encontrada"));
        this.data_do_registro = dados.data_do_registro();
        this.tipo_problema = dados.tipo_problema();
        this.descricao_problema = dados.descricao_problema();
    }



    public void excluir(){
        this.ativo = false;
    }

    public void atualizarInformacoes(DadosAtualizacaoRegistro dados) {
    }
}
