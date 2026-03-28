package com.projeto.av1.controller;

import com.projeto.av1.model.Bolao;
import com.projeto.av1.service.BolaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bolao")
public class BolaoController {

    private final BolaoService service;

    public BolaoController(BolaoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Bolao>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bolao> buscar(@PathVariable Long id) {
        Bolao bolao = service.buscar(id);
        return bolao != null ? ResponseEntity.ok(bolao) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Bolao> salvar(@RequestBody Bolao bolao) {
        return ResponseEntity.ok(service.salvar(bolao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bolao> atualizar(@PathVariable Long id, @RequestBody Bolao novo) {
        Bolao existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNome(novo.getNome());
        existente.setValorAposta(novo.getValorAposta());
        existente.setDataInicio(novo.getDataInicio());
        existente.setDataFim(novo.getDataFim());
        existente.setCriador(novo.getCriador());

        return ResponseEntity.ok(service.salvar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}