package com.projeto.av1.repository;

import com.projeto.av1.model.Aposta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApostaRepository extends JpaRepository<Aposta, Long> {

    List<Aposta> findByJogoId(Long jogoId);

    List<Aposta> findByBolaoIdAndRodadaId(Long bolaoId, Long rodadaId);

    List<Aposta> findByBolaoId(Long bolaoId);
}