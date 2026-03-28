package com.projeto.av1.repository;

import com.projeto.av1.model.Bolao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BolaoRepository extends JpaRepository<Bolao, Long> {
}