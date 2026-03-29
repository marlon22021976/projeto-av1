package com.projeto.av1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "estatistica")
public class Estatistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("rodada_id")
    @ManyToOne
    @JoinColumn(name = "rodada_id")
    private Rodada rodada;

    @JsonProperty("bolao_id")
    @ManyToOne
    @JoinColumn(name = "bolao_id")
    private Bolao bolao;

    @JsonProperty("maior_pontuador_id")
    @ManyToOne
    @JoinColumn(name = "maior_pontuador_id")
    private Usuario maiorPontuador;

    @JsonProperty("menor_pontuador_id")
    @ManyToOne
    @JoinColumn(name = "menor_pontuador_id")
    private Usuario menorPontuador;

    @JsonProperty("media_pontos")
    @Column(name = "media_pontos")
    private Double mediaPontos;

    @JsonProperty("total_apostadores")
    @Column(name = "total_apostadores")
    private Integer totalApostadores;

    public Estatistica() {}

    public Estatistica(Long id, Rodada rodada, Bolao bolao,
                       Usuario maiorPontuador, Usuario menorPontuador,
                       Double mediaPontos, Integer totalApostadores) {
        this.id = id;
        this.rodada = rodada;
        this.bolao = bolao;
        this.maiorPontuador = maiorPontuador;
        this.menorPontuador = menorPontuador;
        this.mediaPontos = mediaPontos;
        this.totalApostadores = totalApostadores;
    }

    public Long getId() { return id; }
    public Rodada getRodada() { return rodada; }
    public Bolao getBolao() { return bolao; }
    public Usuario getMaiorPontuador() { return maiorPontuador; }
    public Usuario getMenorPontuador() { return menorPontuador; }
    public Double getMediaPontos() { return mediaPontos; }
    public Integer getTotalApostadores() { return totalApostadores; }

    public void setId(Long id) { this.id = id; }
    public void setRodada(Rodada rodada) { this.rodada = rodada; }
    public void setBolao(Bolao bolao) { this.bolao = bolao; }
    public void setMaiorPontuador(Usuario maiorPontuador) { this.maiorPontuador = maiorPontuador; }
    public void setMenorPontuador(Usuario menorPontuador) { this.menorPontuador = menorPontuador; }
    public void setMediaPontos(Double mediaPontos) { this.mediaPontos = mediaPontos; }
    public void setTotalApostadores(Integer totalApostadores) { this.totalApostadores = totalApostadores; }
}