package com;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Fila {

  public String nome;
  public int fila;
  public int tamanhoMaximoDaFila;
  public int nServidores;
  public int perda;
  public Map<Integer, Double> statusMap;
  public Double tempoChegadaMinimo;
  public Double tempoChegadaMaximo;
  public Double tempoSaidaMinimo;
  public Double tempoSaidaMaximo;
  public List<Roteamento> roteamentos;

  public Fila(String nome, int tamanhoMaximoDaFila, int nServidores,
      Double tempoChegadaMinimo,
      Double tempoChegadaMaximo,
      Double tempoSaidaMinimo, Double tempoSaidaMaximo, List<Roteamento> roteamentos) {

    this.nome = nome;
    this.nServidores = nServidores;
    this.tamanhoMaximoDaFila = tamanhoMaximoDaFila;
    this.tempoChegadaMinimo = tempoChegadaMinimo;
    this.tempoChegadaMaximo = tempoChegadaMaximo;
    this.tempoSaidaMinimo = tempoSaidaMinimo;
    this.tempoSaidaMaximo = tempoSaidaMaximo;
    this.fila = 0;
    this.perda = 0;
    this.statusMap = new HashMap<>();
    this.roteamentos = roteamentos;

    for (int i = 0; i <= tamanhoMaximoDaFila; i++) {
      this.statusMap.put(i, 0.0);
    }
  }

  public void contabilizaTempo(Double tempoEvento) {
    Double tempoStatusFila = this.statusMap.get(fila);
    this.statusMap.put(fila,
        Optional.ofNullable(tempoStatusFila).orElse(0.0) + (tempoEvento - Contexto.tempoGlobal));
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
    return fila < tamanhoMaximoDaFila || tamanhoMaximoDaFila == 0;
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

  //Calcula fila destino de roteamento retornando null se nenhuma
  public String getDestinoRoteamento() {
    if (roteamentos == null || roteamentos.isEmpty()) {
      return null;
    }

    double aleatorio = GeradorNumeroAleatorio
        .generateRandomNumberFrom(134775813, Math.pow(2, 32), true);

    String filaDestino = null;

    this.roteamentos.sort(Comparator.comparingDouble(Roteamento::getProbabilidade));

    Double probabilidade = 0.0;
    List<Roteamento> roteamentoList = this.roteamentos;
    for (int i = 0, roteamentoListSize = roteamentoList.size(); i < roteamentoListSize; i++) {
      Roteamento roteamento = roteamentoList.get(i);
      probabilidade += roteamento.getProbabilidade();
      if (aleatorio <= probabilidade) {
        filaDestino = roteamento.getIndexFila();
        break;
      }
    }

    return filaDestino;
  }

}
