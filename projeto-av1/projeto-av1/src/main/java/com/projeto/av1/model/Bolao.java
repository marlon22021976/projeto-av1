package com.projeto.av1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bolao")
public class Bolao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @JsonProperty("valor_aposta")
    @Column(name = "valor_aposta", nullable = false)
    private Double valorAposta;

    @JsonProperty("data_inicio")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @JsonProperty("data_fim")
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "criador_id", nullable = false)
    @JsonProperty("criador")
    private Usuario criador;

    public Bolao() {}

    public Bolao(Long id, String nome, Double valorAposta, LocalDate dataInicio, LocalDate dataFim, Usuario criador) {
        this.id = id;
        this.nome = nome;
        this.valorAposta = valorAposta;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.criador = criador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValorAposta() {
        return valorAposta;
    }

    public void setValorAposta(Double valorAposta) {
        this.valorAposta = valorAposta;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Usuario getCriador() {
        return criador;
    }

    public void setCriador(Usuario criador) {
        this.criador = criador;
    }
}