package com;

public class Roteamento {

    private String indexFila;
    private double probabilidade;

    public Roteamento(String indexFila, double probabilidade) {
        this.indexFila = indexFila;
        this.probabilidade = probabilidade;
    }

    public String getIndexFila() {
        return indexFila;
    }

    public void setIndexFila(String indexFila) {
        this.indexFila = indexFila;
    }

    public double getProbabilidade() {
        return probabilidade;
    }

    public void setProbabilidade(double probabilidade) {
        this.probabilidade = probabilidade;
    }
}
