package com.api.checklistcondominio.controller;

import com.api.checklistcondominio.domain.administrador.DadosCadastroAdministrador;
import com.api.checklistcondominio.domain.registro.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("registro")
public class RegistroController {
    @Autowired
    private RegistroRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRegistro dados, UriComponentsBuilder uriBuilder) {
        var registro = new Registro(dados);
        repository.save(registro);

        var uri = uriBuilder.path("/registro/{id}").buildAndExpand(registro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRegistro(registro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemRegistro>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemRegistro::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoRegistro dados) {
        var registro = repository.getReferenceById(dados.id());
        registro.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoRegistro(registro));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var registro = repository.getReferenceById(id);
        registro.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var registro = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoRegistro(registro));
    }
}
