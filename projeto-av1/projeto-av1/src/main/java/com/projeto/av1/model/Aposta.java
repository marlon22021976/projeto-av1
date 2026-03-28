package com.projeto.av1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "aposta")
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("palpite_casa")
    @Column(name = "palpite_casa")
    private Integer palpiteCasa;

    @JsonProperty("palpite_fora")
    @Column(name = "palpite_fora")
    private Integer palpiteFora;

    @JsonProperty("pontos")
    @Column(name = "pontos")
    private Integer pontos;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "jogo_id")
    private Jogo jogo;

    public Aposta() {
    }

    public Aposta(Long id, Integer palpiteCasa, Integer palpiteFora, Integer pontos, Usuario usuario, Jogo jogo) {
        this.id = id;
        this.palpiteCasa = palpiteCasa;
        this.palpiteFora = palpiteFora;
        this.pontos = pontos;
        this.usuario = usuario;
        this.jogo = jogo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPalpiteCasa() {
        return palpiteCasa;
    }

    public void setPalpiteCasa(Integer palpiteCasa) {
        this.palpiteCasa = palpiteCasa;
    }

    public Integer getPalpiteFora() {
        return palpiteFora;
    }

    public void setPalpiteFora(Integer palpiteFora) {
        this.palpiteFora = palpiteFora;
    }

    public Integer getPontos() {
        return pontos;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Jogo getJogo() {
        return jogo;
    }

    public void setJogo(Jogo jogo) {
        this.jogo = jogo;
    }
}