package com.api.checklistcondominio.controller;

import com.api.checklistcondominio.domain.Torre.DadosCadastroTorre;
import com.api.checklistcondominio.domain.Torre.DadosDetalhamentoTorre;
import com.api.checklistcondominio.domain.Torre.Torre;
import com.api.checklistcondominio.domain.Torre.TorreRepository;
import com.api.checklistcondominio.domain.condominio.Condominio;
import com.api.checklistcondominio.domain.condominio.CondominioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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

import com.api.checklistcondominio.domain.registro.DadosAtualizacaoRegistro;
import com.api.checklistcondominio.domain.registro.DadosCadastroRegistro;
import com.api.checklistcondominio.domain.registro.DadosDetalhamentoRegistro;
import com.api.checklistcondominio.domain.registro.DadosListagemRegistro;
import com.api.checklistcondominio.domain.registro.Registro;
import com.api.checklistcondominio.domain.registro.RegistroRepository;

import jakarta.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping("registro")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RegistroController {
    @Autowired
    private RegistroRepository repository;

    @Autowired
    private CondominioRepository condominioRepository;

    @Autowired
    private TorreRepository torreRepository;

    @PostMapping
    @jakarta.transaction.Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroRegistro dados, UriComponentsBuilder uriBuilder) {
        Condominio condominio = condominioRepository.findById(dados.idCondominio())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));
        Torre torre = torreRepository.findById(dados.idTorre())
                .orElseThrow(() -> new IllegalArgumentException("Condomínio não encontrado"));


        Registro registro = new Registro(dados, condominio.getId(), condominioRepository, torre.getId(), torreRepository);
        repository.save(registro);

        URI uri = uriBuilder.path("/registro/{id}").buildAndExpand(registro.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoRegistro(registro));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemRegistro>> listar(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        Page<Registro> registros = repository.findAllByAtivoTrue(paginacao);
        Page<DadosListagemRegistro> page = registros.map(DadosListagemRegistro::new);
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
