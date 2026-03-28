package com.projeto.av1.service;

import com.projeto.av1.model.Bolao;
import com.projeto.av1.repository.BolaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BolaoService {

    private final BolaoRepository repository;

    public BolaoService(BolaoRepository repository) {
        this.repository = repository;
    }

    public List<Bolao> listar() {
        return repository.findAll();
    }

    public Bolao buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Bolao salvar(Bolao bolao) {
        return repository.save(bolao);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}