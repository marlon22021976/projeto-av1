package com.projeto.av1.controller;

import com.projeto.av1.model.Jogo;
import com.projeto.av1.service.JogoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jogo")
public class JogoController {

    private final JogoService service;

    public JogoController(JogoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Jogo>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Jogo> buscar(@PathVariable Long id) {
        Jogo jogo = service.buscar(id);
        return jogo != null ? ResponseEntity.ok(jogo) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Jogo> salvar(@RequestBody Jogo jogo) {
        return ResponseEntity.ok(service.salvar(jogo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizar(@PathVariable Long id, @RequestBody Jogo novo) {
        Jogo existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setTimeCasa(novo.getTimeCasa());
        existente.setTimeFora(novo.getTimeFora());
        existente.setDataHora(novo.getDataHora());
        existente.setVencedor(novo.getVencedor());
        existente.setRodada(novo.getRodada());

        return ResponseEntity.ok(service.salvar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}