package com.projeto.av1.service;

import com.projeto.av1.model.Aposta;
import com.projeto.av1.model.Jogo;
import com.projeto.av1.repository.ApostaRepository;
import com.projeto.av1.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApostaService {

    private final ApostaRepository repository;
    private final JogoRepository jogoRepository;
    private final PontuacaoService pontuacaoService;

    public ApostaService(ApostaRepository repository,
                         JogoRepository jogoRepository,
                         PontuacaoService pontuacaoService) {
        this.repository = repository;
        this.jogoRepository = jogoRepository;
        this.pontuacaoService = pontuacaoService;
    }

    public List<Aposta> listar() {
        return repository.findAll();
    }

    public List<Aposta> listarPorJogo(Long jogoId) {
        return repository.findByJogoId(jogoId);
    }

    public Aposta buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Aposta salvar(Aposta aposta) {

        if (aposta.getJogo() != null && aposta.getJogo().getId() != null) {

            Jogo jogo = jogoRepository.findById(aposta.getJogo().getId()).orElse(null);

            if (jogo != null) {
                // Preenche bolão e rodada automaticamente se não vier no JSON
                if (aposta.getBolao() == null && jogo.getRodada() != null) {
                    aposta.setBolao(jogo.getRodada().getBolao());
                }
                if (aposta.getRodada() == null) {
                    aposta.setRodada(jogo.getRodada());
                }

                if (jogo.getVencedor() != null) {
                    aposta.setPontos(pontuacaoService.calcularPontos(jogo, aposta));
                } else {
                    aposta.setPontos(0);
                }
            } else {
                aposta.setPontos(0);
            }

        } else {
            aposta.setPontos(0);
        }

        return repository.save(aposta);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}