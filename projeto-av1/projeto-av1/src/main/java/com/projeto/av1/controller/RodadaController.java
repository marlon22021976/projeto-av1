package com.projeto.av1.controller;

import com.projeto.av1.model.Rodada;
import com.projeto.av1.service.RodadaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rodada")
public class RodadaController {

    private final RodadaService service;

    public RodadaController(RodadaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Rodada>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rodada> buscar(@PathVariable Long id) {
        Rodada rodada = service.buscar(id);
        return rodada != null ? ResponseEntity.ok(rodada) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Rodada> salvar(@RequestBody Rodada rodada) {
        return ResponseEntity.ok(service.salvar(rodada));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Rodada> atualizar(@PathVariable Long id, @RequestBody Rodada novo) {
        Rodada existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setNumero(novo.getNumero());
        existente.setDataInicio(novo.getDataInicio());
        existente.setDataFim(novo.getDataFim());
        existente.setDescricao(novo.getDescricao());
        existente.setBolao(novo.getBolao());

        return ResponseEntity.ok(service.salvar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}