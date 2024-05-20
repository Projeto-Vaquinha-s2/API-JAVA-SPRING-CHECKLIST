package com.api.checklistcondominio.controller;
import com.api.checklistcondominio.domain.condominio.*;
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
@RequestMapping("condominios")
public class CondominioController {
    @Autowired
    private CondominioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCondominio dados, UriComponentsBuilder uriBuilder) {
        var condominio = new Condominio(dados);
        repository.save(condominio);

        var uri = uriBuilder.path("/condominios/{id}").buildAndExpand(condominio.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCondominio(condominio));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCondominio>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemCondominio::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoCondominio dados) {
        var condominio = repository.getReferenceById(dados.id());
        condominio.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCondominio(condominio));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var condominio = repository.getReferenceById(id);
        condominio.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var condominio = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCondominio(condominio));
    }
}