package com.projeto.av1.dto;

public class RankingItem {

    private String usuario;
    private Integer pontos;
    private Integer posicao;

    public RankingItem(String usuario, Integer pontos, Integer posicao) {
        this.usuario = usuario;
        this.pontos = pontos;
        this.posicao = posicao;
    }

    public String getUsuario() {
        return usuario;
    }

    public Integer getPontos() {
        return pontos;
    }

    public Integer getPosicao() {
        return posicao;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setPontos(Integer pontos) {
        this.pontos = pontos;
    }

    public void setPosicao(Integer posicao) {
        this.posicao = posicao;
    }
}