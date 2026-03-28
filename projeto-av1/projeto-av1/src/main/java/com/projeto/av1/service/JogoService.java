package com.projeto.av1.service;

import com.projeto.av1.model.Jogo;
import com.projeto.av1.repository.JogoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JogoService {

    private final JogoRepository repository;

    public JogoService(JogoRepository repository) {
        this.repository = repository;
    }

    public List<Jogo> listar() {
        return repository.findAll();
    }

    public Jogo buscar(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Jogo salvar(Jogo jogo) {
        return repository.save(jogo);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}