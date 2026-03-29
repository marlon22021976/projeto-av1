package com.projeto.av1.controller;

import com.projeto.av1.dto.RankingItem;
import com.projeto.av1.service.RankingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    private final RankingService service;

    public RankingController(RankingService service) {
        this.service = service;
    }

    @GetMapping("/{bolaoId}")
    public ResponseEntity<List<RankingItem>> ranking(@PathVariable Long bolaoId) {
        return ResponseEntity.ok(service.rankingDoBolao(bolaoId));
    }
}