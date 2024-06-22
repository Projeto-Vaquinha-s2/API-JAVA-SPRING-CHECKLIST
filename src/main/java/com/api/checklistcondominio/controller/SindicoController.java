package com.api.checklistcondominio.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.api.checklistcondominio.domain.sindico.DadosAtualizacaoSindico;
import com.api.checklistcondominio.domain.sindico.DadosCadastroSindico;
import com.api.checklistcondominio.domain.sindico.DadosDetalhamentoSindico;
import com.api.checklistcondominio.domain.sindico.DadosListagemSindico;
import com.api.checklistcondominio.domain.sindico.Sindico;
import com.api.checklistcondominio.domain.sindico.SindicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("sindicos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class SindicoController {
    @Autowired
    private SindicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroSindico dados, UriComponentsBuilder uriBuilder) {
        var sindico = new Sindico(dados);
        repository.save(sindico);

        var uri = uriBuilder.path("/sindicos/{id}").buildAndExpand(sindico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoSindico(sindico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemSindico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemSindico::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoSindico dados) {
        var sindico = repository.getReferenceById(dados.id());
        sindico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoSindico(sindico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var sindico = repository.getReferenceById(id);
        sindico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var sindico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoSindico(sindico));
    }
}