package com.projeto.av1.service;

import com.projeto.av1.model.*;
import com.projeto.av1.repository.ResultadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoService {

    private final ResultadoRepository repository;
    private final JogoService jogoService;
    private final ApostaService apostaService;
    private final PontuacaoService pontuacaoService;

    public ResultadoService(ResultadoRepository repository,
                            JogoService jogoService,
                            ApostaService apostaService,
                            PontuacaoService pontuacaoService) {
        this.repository = repository;
        this.jogoService = jogoService;
        this.apostaService = apostaService;
        this.pontuacaoService = pontuacaoService;
    }

    public Resultado salvar(Resultado r) {

        Jogo jogo = jogoService.buscar(r.getJogo().getId());
        if (jogo == null) return null;

        r.setJogo(jogo);
        r.calcularVencedor();
        jogo.setVencedor(r.getVencedor());
        jogoService.salvar(jogo);

        List<Aposta> apostas = apostaService.listarPorJogo(jogo.getId());
        for (Aposta a : apostas) {
            int pontos = pontuacaoService.calcularPontos(jogo, a);
            a.setPontos(pontos);
            apostaService.salvar(a);
        }

        return repository.save(r);
    }

    public Resultado buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Resultado> listar() {
        return repository.findAll();
    }
}