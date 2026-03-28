package com.projeto.av1.service;

import com.projeto.av1.model.Rodada;
import com.projeto.av1.repository.RodadaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RodadaService {

    private final RodadaRepository repository;

    public RodadaService(RodadaRepository repository) {
        this.repository = repository;
    }

    public List<Rodada> listar() {
        return repository.findAll();
    }

    public Rodada buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Rodada salvar(Rodada rodada) {
        return repository.save(rodada);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}