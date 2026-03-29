package com.projeto.av1.controller;

import com.projeto.av1.model.Estatistica;
import com.projeto.av1.service.EstatisticaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    public EstatisticaController(EstatisticaService estatisticaService) {
        this.estatisticaService = estatisticaService;
    }

    @PostMapping("/gerar")
    public Estatistica gerar(@RequestParam Long bolaoId, @RequestParam Long rodadaId) {
        return estatisticaService.gerarEstatistica(bolaoId, rodadaId);
    }
}