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

import com.api.checklistcondominio.domain.administrador.Administrador;
import com.api.checklistcondominio.domain.administrador.AdministradorRepository;
import com.api.checklistcondominio.domain.administrador.DadosAtualizacaoAdministrador;
import com.api.checklistcondominio.domain.administrador.DadosCadastroAdministrador;
import com.api.checklistcondominio.domain.administrador.DadosDetalhamentoAdministrador;
import com.api.checklistcondominio.domain.administrador.DadosListagemAdministrador;

import jakarta.validation.Valid;
@RestController
@RequestMapping("administrador")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AdministradorController {
    @Autowired
    private AdministradorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroAdministrador dados, UriComponentsBuilder uriBuilder) {
        var administrador = new Administrador(dados);
        repository.save(administrador);

        var uri = uriBuilder.path("/administrador/{id}").buildAndExpand(administrador.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoAdministrador(administrador));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemAdministrador>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemAdministrador::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoAdministrador dados) {
        var administrador = repository.getReferenceById(dados.id());
        administrador.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoAdministrador(administrador));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var administrador = repository.getReferenceById(id);
        administrador.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var administrador = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoAdministrador(administrador));
    }
}

