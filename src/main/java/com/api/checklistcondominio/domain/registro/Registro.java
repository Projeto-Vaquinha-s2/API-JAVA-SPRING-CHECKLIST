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

    private String foto_path;
    private LocalDateTime data_do_registro;
    private String descricao_problema;

    @Enumerated(EnumType.STRING)
    private TipoProblema tipo_problema;

    private Boolean ativo;

    public Registro(DadosCadastroRegistro dados, Long condominioId, CondominioRepository condominioRepository, Long torreId, TorreRepository torreRepository) {
        this.condominio = condominioRepository.findById(condominioId)
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        this.torre = torreRepository.findById(torreId)
                .orElseThrow(() -> new IllegalArgumentException("Torre não encontrada"));
        this.foto_path = dados.foto_path();
        this.data_do_registro = dados.data_do_registro();
        this.tipo_problema = dados.tipo_problema();
        this.descricao_problema = dados.descricao_problema();
        this.ativo = true;
    }

    public Registro(DadosCadastroRegistro dados) {
    }


    public void atualizarInformacoes(@Valid DadosAtualizacaoRegistro dados, Long condominioId, CondominioRepository condominioRepository, Long torreId, TorreRepository torreRepository){
        this.condominio = condominioRepository.findById(condominioId)
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        this.torre = torreRepository.findById(torreId)
                .orElseThrow(() -> new IllegalArgumentException("Torre não encontrada"));
        this.foto_path = dados.foto_path();
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
