package com.projeto.av1.controller;

import com.projeto.av1.model.Aposta;
import com.projeto.av1.service.ApostaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aposta")
public class ApostaController {

    private final ApostaService service;

    public ApostaController(ApostaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Aposta>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aposta> buscar(@PathVariable Long id) {
        Aposta aposta = service.buscar(id);
        return aposta != null ? ResponseEntity.ok(aposta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Aposta> salvar(@RequestBody Aposta aposta) {
        return ResponseEntity.ok(service.salvar(aposta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aposta> atualizar(@PathVariable Long id, @RequestBody Aposta nova) {
        Aposta existente = service.buscar(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        existente.setPalpiteCasa(nova.getPalpiteCasa());
        existente.setPalpiteFora(nova.getPalpiteFora());
        existente.setUsuario(nova.getUsuario());
        existente.setJogo(nova.getJogo());
        existente.setBolao(nova.getBolao());
        existente.setRodada(nova.getRodada());

        // Pontos serão recalculados automaticamente ao chamar salvar()
        return ResponseEntity.ok(service.salvar(existente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.remover(id);
        return ResponseEntity.noContent().build();
    }
}