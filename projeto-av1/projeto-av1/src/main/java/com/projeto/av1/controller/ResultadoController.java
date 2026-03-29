package com.projeto.av1.controller;

import com.projeto.av1.model.Resultado;
import com.projeto.av1.service.ResultadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/resultado")
public class ResultadoController {

    private final ResultadoService service;

    public ResultadoController(ResultadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Resultado>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    public ResponseEntity<Resultado> salvar(@RequestBody Resultado resultado) {
        return ResponseEntity.ok(service.salvar(resultado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Resultado> atualizar(
            @PathVariable Long id,
            @RequestBody Resultado novo
    ) {
        Resultado existente = service.buscar(id);
        if (existente == null) return ResponseEntity.notFound().build();

        existente.setGolsCasa(novo.getGolsCasa());
        existente.setGolsFora(novo.getGolsFora());
        existente.calcularVencedor();

        return ResponseEntity.ok(service.salvar(existente));
    }
}