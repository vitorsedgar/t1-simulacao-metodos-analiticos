package com;

import java.util.HashMap;
import java.util.Map;

public class Fila {

    private int fila;
    private int tamanhoMaximoDaFila;
    private int nServidores;
    private int perda;
    private Map<Integer, Double> statusMap;
    private Double tempoChegadaMinimo;
    private Double tempoChegadaMaximo;
    private Double tempoSaidaMinimo;
    private Double tempoSaidaMaximo;

    public Fila(int tamanhoMaximoDaFila, int nServidores, Double tempoChegadaMinimo, Double tempoChegadaMaximo,
                Double tempoSaidaMinimo, Double tempoSaidaMaximo) {

        this.nServidores = nServidores;
        this.tamanhoMaximoDaFila = tamanhoMaximoDaFila;
        this.tempoChegadaMinimo = tempoChegadaMinimo;
        this.tempoChegadaMaximo = tempoChegadaMaximo;
        this.tempoSaidaMinimo = tempoSaidaMinimo;
        this.tempoSaidaMaximo = tempoSaidaMaximo;
        this.fila = 0;
        this.perda = 0;
        this.statusMap = new HashMap<>();

        for (int i = 0; i <= tamanhoMaximoDaFila; i++) {
            this.statusMap.put(i, 0.0);
        }
    }

    public void contabilizaTempo(Double tempoEvento) {
        Double tempoStatusFila = statusMap.get(fila);
        statusMap.put(fila, tempoStatusFila + (tempoEvento - Contexto.tempoGlobal));
        Contexto.tempoGlobal = tempoEvento;
    }

    public void adicionarEvento() {
        fila++;
    }

    public void removerEvento() {
        fila--;
    }

    public void adicionarPerda() {
        perda++;
    }

    public boolean possuiEspaco() {
        return fila < tamanhoMaximoDaFila;
    }

    public boolean possuiServidorDisponivel() {
        return fila <= nServidores;
    }

    public boolean naoPossuiServidorDisponivel() {
        return fila >= nServidores;
    }

    public Double getTempoSaidaMinimo() {
        return tempoSaidaMinimo;
    }

    public Double getTempoSaidaMaximo() {
        return tempoSaidaMaximo;
    }

    public Double getTempoChegadaMinimo() {
        return tempoChegadaMinimo;
    }

    public Double getTempoChegadaMaximo() {
        return tempoChegadaMaximo;
    }

    public void printaResultados() {
        System.out.printf(
                "\nQUEUE FINAL STATUS: \n QUEUE SIZE: %s \n GLOBAL TIME: %s%n \n CLIENTES PERDIDOS: %s \n",
                fila,
                Contexto.tempoGlobal, perda);

        for (int i = 0; i <= tamanhoMaximoDaFila; i++) {
            System.out.printf("QUEUE HAD SIZE %s PER %s times WITH %s OF PROBABILITY \n", i,
                    statusMap.get(i), getProbabilidade(i));
        }
    }

    private String getProbabilidade(int statusIndex) {
        return String.format("%.2f", statusMap.get(statusIndex) / Contexto.tempoGlobal * 100) + "%";
    }
}
