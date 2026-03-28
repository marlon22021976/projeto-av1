package com.projeto.av1.service;

import com.projeto.av1.model.Aposta;
import com.projeto.av1.model.Estatistica;
import com.projeto.av1.model.Rodada;
import com.projeto.av1.repository.ApostaRepository;
import com.projeto.av1.repository.EstatisticaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstatisticaService {

    private final EstatisticaRepository repository;
    private final ApostaRepository apostaRepository;

    public EstatisticaService(EstatisticaRepository repository, ApostaRepository apostaRepository) {
        this.repository = repository;
        this.apostaRepository = apostaRepository;
    }

    public List<Estatistica> listar() {
        return repository.findAll();
    }

    public Estatistica buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Estatistica salvar(Estatistica estatistica) {
        return repository.save(estatistica);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }

    public Estatistica gerarEstatistica(Rodada rodada) {

        List<Aposta> apostas = apostaRepository.findAll().stream()
                .filter(a -> a.getJogo() != null
                        && a.getJogo().getRodada() != null
                        && a.getJogo().getRodada().getId().equals(rodada.getId()))
                .toList();

        if (apostas.isEmpty()) {
            return null;
        }

        Aposta maior = apostas.stream()
                .max((a1, a2) -> Integer.compare(a1.getPontos(), a2.getPontos()))
                .orElse(null);

        Aposta menor = apostas.stream()
                .min((a1, a2) -> Integer.compare(a1.getPontos(), a2.getPontos()))
                .orElse(null);

        double media = apostas.stream()
                .mapToInt(Aposta::getPontos)
                .average()
                .orElse(0.0);

        Estatistica estatistica = new Estatistica();
        estatistica.setRodada(rodada);
        estatistica.setBolao(rodada.getBolao());
        estatistica.setMaiorPontuador(maior != null ? maior.getUsuario() : null);
        estatistica.setMenorPontuador(menor != null ? menor.getUsuario() : null);
        estatistica.setMediaPontos(media);

        return repository.save(estatistica);
    }
}