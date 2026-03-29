package com.projeto.av1.service;

import com.projeto.av1.dto.RankingItem;
import com.projeto.av1.model.Aposta;
import com.projeto.av1.repository.ApostaRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RankingService {

    private final ApostaRepository apostaRepository;

    public RankingService(ApostaRepository apostaRepository) {
        this.apostaRepository = apostaRepository;
    }

    public List<RankingItem> rankingDoBolao(Long bolaoId) {

        List<Aposta> apostas = apostaRepository.findAll()
                .stream()
                .filter(a ->
                        a.getJogo() != null &&
                        a.getJogo().getRodada() != null &&
                        a.getJogo().getRodada().getBolao().getId().equals(bolaoId))
                .toList();

        Map<String, Integer> pontosPorUsuario = new HashMap<>();

        for (Aposta a : apostas) {
            String nome = a.getUsuario().getNome();
            pontosPorUsuario.put(nome,
                    pontosPorUsuario.getOrDefault(nome, 0) + a.getPontos());
        }

        List<Map.Entry<String, Integer>> ordenado =
                pontosPorUsuario.entrySet()
                        .stream()
                        .sorted((a, b) -> b.getValue() - a.getValue())
                        .toList();

        List<RankingItem> ranking = new ArrayList<>();
        int pos = 1;

        for (var entry : ordenado) {
            ranking.add(new RankingItem(entry.getKey(), entry.getValue(), pos++));
        }

        return ranking;
    }
}