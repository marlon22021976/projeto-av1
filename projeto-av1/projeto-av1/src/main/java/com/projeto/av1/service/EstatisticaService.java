package com.projeto.av1.service;

import com.projeto.av1.model.*;
import com.projeto.av1.repository.*;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EstatisticaService {

    private final ApostaRepository apostaRepository;
    private final EstatisticaRepository estatisticaRepository;
    private final BolaoRepository bolaoRepository;
    private final RodadaRepository rodadaRepository;

    public EstatisticaService(ApostaRepository apostaRepository,
                              EstatisticaRepository estatisticaRepository,
                              BolaoRepository bolaoRepository,
                              RodadaRepository rodadaRepository) {
        this.apostaRepository = apostaRepository;
        this.estatisticaRepository = estatisticaRepository;
        this.bolaoRepository = bolaoRepository;
        this.rodadaRepository = rodadaRepository;
    }

    public Estatistica gerarEstatistica(Long bolaoId, Long rodadaId) {

        List<Aposta> apostas = apostaRepository.findByBolaoIdAndRodadaId(bolaoId, rodadaId);

        if (apostas.isEmpty()) {
            throw new RuntimeException("Nenhuma aposta encontrada para esta rodada.");
        }

        Aposta maior = apostas.stream()
                .max(Comparator.comparing(Aposta::getPontos))
                .orElse(null);

        Aposta menor = apostas.stream()
                .min(Comparator.comparing(Aposta::getPontos))
                .orElse(null);

        Estatistica e = new Estatistica();
        e.setBolao(bolaoRepository.findById(bolaoId).orElse(null));
        e.setRodada(rodadaRepository.findById(rodadaId).orElse(null));
        e.setMaiorPontuador(maior != null ? maior.getUsuario() : null);
        e.setMenorPontuador(menor != null ? menor.getUsuario() : null);

        e.setMediaPontos(
                apostas.stream().mapToInt(Aposta::getPontos).average().orElse(0)
        );

        e.setTotalApostadores(apostas.size());

        return estatisticaRepository.save(e);
    }
}