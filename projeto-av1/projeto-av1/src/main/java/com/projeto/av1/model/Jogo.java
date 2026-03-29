package com.projeto.av1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("time_casa")
    @Column(name = "time_casa", nullable = false)
    private String timeCasa;

    @JsonProperty("time_fora")
    @Column(name = "time_fora", nullable = false)
    private String timeFora;

    @JsonProperty("data_hora")
    @Column(name = "data_hora", nullable = false)
    private LocalDateTime dataHora;

    @Enumerated(EnumType.STRING)
    @Column(name = "vencedor")
    private Vencedor vencedor;

    @ManyToOne
    @JoinColumn(name = "rodada_id")
    private Rodada rodada;

    public Jogo() {
    }

    public Jogo(Long id, String timeCasa, String timeFora, LocalDateTime dataHora, Vencedor vencedor, Rodada rodada) {
        this.id = id;
        this.timeCasa = timeCasa;
        this.timeFora = timeFora;
        this.dataHora = dataHora;
        this.vencedor = vencedor;
        this.rodada = rodada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTimeCasa() {
        return timeCasa;
    }

    public void setTimeCasa(String timeCasa) {
        this.timeCasa = timeCasa;
    }

    public String getTimeFora() {
        return timeFora;
    }

    public void setTimeFora(String timeFora) {
        this.timeFora = timeFora;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Vencedor getVencedor() {
        return vencedor;
    }

    public void setVencedor(Vencedor vencedor) {
        this.vencedor = vencedor;
    }

    public Rodada getRodada() {
        return rodada;
    }

    public void setRodada(Rodada rodada) {
        this.rodada = rodada;
    }
}
