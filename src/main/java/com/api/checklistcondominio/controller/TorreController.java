package com.api.checklistcondominio.controller;

import com.api.checklistcondominio.domain.Torre.*;
import com.api.checklistcondominio.domain.condominio.Condominio;
import com.api.checklistcondominio.domain.condominio.CondominioRepository;
import com.api.checklistcondominio.domain.condominio.DadosAtualizacaoCondominio;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/torre")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TorreController {

    @Autowired
    TorreRepository torreRepository;

    @Autowired
    CondominioRepository condominioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTorre dados, UriComponentsBuilder uriBuilder) {
        Condominio condominio = condominioRepository.findById(dados.condominio_id())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));

        long countTorresAtivas = torreRepository.countByCondominioAndAtivoTrue(condominio);
        if (countTorresAtivas >= condominio.getQuantidade_torres()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Número máximo de torres atingido para este condomínio.");
        }

        Torre torre = new Torre(dados, condominio.getId(), condominioRepository);
        torreRepository.save(torre);

        URI uri = uriBuilder.path("/torres/{id}").buildAndExpand(torre.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTorre(torre));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemTorre>> listar(@PageableDefault(size = 10) Pageable paginacao) {
        var page = torreRepository.findAllByAtivoTrue(paginacao).map(DadosListagemTorre::new);
        return ResponseEntity.ok(page);
    }


    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var torre = torreRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTorre(torre));
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTorre dados) {
        var torre = torreRepository.getReferenceById(id);
        Condominio condominio = condominioRepository.findById(dados.condominio_id())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        torre.atualizarInformacoes(dados, condominio);
        return ResponseEntity.ok(new DadosDetalhamentoTorre(torre));
    }



    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var torre = torreRepository.getReferenceById(id);
        torre.excluir();

        return ResponseEntity.noContent().build();
    }


}
