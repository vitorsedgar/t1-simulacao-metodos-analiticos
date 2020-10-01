package com;

public class Roteamento {

    private int indexFila;
    private double probabilidade;

    public Roteamento(int indexFila, double probabilidade) {
        this.indexFila = indexFila;
        this.probabilidade = probabilidade;
    }

    public int getIndexFila() {
        return indexFila;
    }

    public double getProbabilidade() {
        return probabilidade;
    }
}
