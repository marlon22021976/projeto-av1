package com.projeto.av1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resultado")
public class Resultado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "gols_casa")
    private Integer golsCasa;

    @Column(name = "gols_fora")
    private Integer golsFora;

    @Enumerated(EnumType.STRING)
    private Vencedor vencedor;

    @Column(name = "pontuacao")
    private Integer pontuacao; // opcional para bônus futuro

    @OneToOne
    @JoinColumn(name = "jogo_id", nullable = false)
    private Jogo jogo;

    public Resultado() {}

    public void calcularVencedor() {
        if (golsCasa == null || golsFora == null) {
            this.vencedor = null;
            return;
        }

        if (golsCasa > golsFora) this.vencedor = Vencedor.CASA;
        else if (golsCasa < golsFora) this.vencedor = Vencedor.FORA;
        else this.vencedor = Vencedor.EMPATE;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public Integer getGolsCasa() { return golsCasa; }
    public Integer getGolsFora() { return golsFora; }
    public Vencedor getVencedor() { return vencedor; }
    public Integer getPontuacao() { return pontuacao; }
    public Jogo getJogo() { return jogo; }

    public void setId(Long id) { this.id = id; }
    public void setGolsCasa(Integer golsCasa) { this.golsCasa = golsCasa; }
    public void setGolsFora(Integer golsFora) { this.golsFora = golsFora; }
    public void setVencedor(Vencedor vencedor) { this.vencedor = vencedor; }
    public void setPontuacao(Integer pontuacao) { this.pontuacao = pontuacao; }
    public void setJogo(Jogo jogo) { this.jogo = jogo; }
}