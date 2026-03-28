package com.projeto.av1.controller;

import com.projeto.av1.model.Estatistica;
import com.projeto.av1.service.EstatisticaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService service;

    public EstatisticaController(EstatisticaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Estatistica>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estatistica> buscar(@PathVariable Long id) {
        Estatistica estatistica = service.buscar(id);
        return estatistica != null ? ResponseEntity.ok(estatistica) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Estatistica> salvar(@RequestBody Estatistica estatistica) {
        return ResponseEntity.ok(service.salvar(estatistica));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estatistica> atualizar(@PathVariable Long id, @RequestBody Estatistica novo) {
        Estatistica existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setRodada(novo.getRodada());
        existente.setBolao(novo.getBolao());
        existente.setMaiorPontuador(novo.getMaiorPontuador());
        existente.setMenorPontuador(novo.getMenorPontuador());
        existente.setMediaPontos(novo.getMediaPontos());

        return ResponseEntity.ok(service.salvar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}