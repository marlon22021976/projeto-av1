package com.projeto.av1.service;

import com.projeto.av1.model.Aposta;
import com.projeto.av1.model.Jogo;
import com.projeto.av1.model.ResultadoJogo;
import org.springframework.stereotype.Service;

@Service
public class PontuacaoService {

    public int calcularPontos(Jogo jogo, Aposta aposta) {

        // Segurança: evita null pointer
        if (jogo == null || aposta == null ||
                aposta.getPalpiteCasa() == null ||
                aposta.getPalpiteFora() == null ||
                jogo.getVencedor() == null) {

            return 0;
        }

        Integer palpiteCasa = aposta.getPalpiteCasa();
        Integer palpiteFora = aposta.getPalpiteFora();

        // EMPATE
        if (jogo.getVencedor() == ResultadoJogo.EMPATE
                && palpiteCasa.equals(palpiteFora)) {
            return 1;
        }

        // CASA venceu
        if (jogo.getVencedor() == ResultadoJogo.CASA
                && palpiteCasa > palpiteFora) {
            return 3;
        }

        // FORA venceu
        if (jogo.getVencedor() == ResultadoJogo.FORA
                && palpiteCasa < palpiteFora) {
            return 3;
        }

        return 0;
    }
}