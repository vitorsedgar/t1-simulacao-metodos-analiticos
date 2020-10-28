package com;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class Fila {

  public String nome;
  public Integer fila;
  public Optional<Integer> tamanhoMaximoDaFila;
  public Integer nServidores;
  public Integer perda;
  public Map<Integer, Double> statusMap;
  public Double tempoChegadaMinimo;
  public Double tempoChegadaMaximo;
  public Double tempoSaidaMinimo;
  public Double tempoSaidaMaximo;
  public List<Roteamento> roteamentos;

  public Fila(String nome, Optional<Integer> tamanhoMaximoDaFila, Integer nServidores,
      Double tempoChegadaMinimo,
      Double tempoChegadaMaximo,
      Double tempoSaidaMinimo, Double tempoSaidaMaximo) {

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
  }

  public void contabilizaTempo(Double tempoEvento) {
    Double tempoStatusFila = this.statusMap.get(fila);
    this.statusMap.put(fila,
        Optional.ofNullable(tempoStatusFila).orElse(0.0) + (tempoEvento - Contexto.tempoGlobal));
  }

  public String getNome() {
    return nome;
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
    return !tamanhoMaximoDaFila.isPresent()
        || fila < tamanhoMaximoDaFila.get()
        || tamanhoMaximoDaFila.get() == 0;
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
    System.out.println("#####################################################################################");
    System.out.println("Fila: " + this.nome);

    System.out.printf(
        "\nQUEUE FINAL STATUS: \n QUEUE SIZE: %s \n GLOBAL TIME: %s%n \n CLIENTES PERDIDOS: %s \n",
        fila,
        Contexto.tempoGlobal, perda);

    statusMap.forEach((integer, aDouble) ->
        System.out.printf("QUEUE HAD SIZE %s PER %s times WITH %s OF PROBABILITY \n", integer,
            aDouble, getProbabilidade(integer)));

    System.out.printf("\nPopulação média: %s clientes \n", String.format("%.2f",getPopulacaoMedia()));
    System.out.printf("Vazão: %s clientes/hora \n", String.format("%.2f",getVazao()));
    System.out.printf("Utilização: %s \n", String.format("%.4f",getUtilizacao()));
    System.out.printf("Tempo de resposta: %s minutos \n", String.format("%.2f", 60 * getPopulacaoMedia()/getVazao()));

    System.out.println("#####################################################################################");
  }

  private String getProbabilidade(int statusIndex) {
    return String.format("%.2f", statusMap.get(statusIndex) / Contexto.tempoGlobal * 100) + "%";
  }

  //Calcula fila destino de roteamento retornando null se nenhuma
  public String getDestinoRoteamento() {
    if (roteamentos == null || roteamentos.isEmpty()) {
      return null;
    }
    if (roteamentos.size() == 1 && roteamentos.get(0).getProbabilidade() == 1.0) {
      return roteamentos.get(0).getIndexFila();
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

  public void setRoteamentos(List<Roteamento> roteamentos) {
    this.roteamentos = roteamentos;
  }

  public Double getPopulacaoMedia() {
    double soma = 0.0;
    for (int i =0; i < statusMap.size(); i++){
      Double value = statusMap.get(i);
      soma += (value/Contexto.tempoGlobal) * i;
    }
    return soma;
  }

  public Double getVazao() {
    double soma = 0.0;
    for (int i =0; i < statusMap.size(); i++){
      Double value = statusMap.get(i);
      soma += (value/Contexto.tempoGlobal) * (60/((this.tempoSaidaMaximo + this.tempoSaidaMinimo)/2));
    }
    return soma;
  }

  public Double getUtilizacao() {
    double soma = 0.0;
    for (int i =0; i < statusMap.size(); i++){
      Double value = statusMap.get(i);
      soma += (value/Contexto.tempoGlobal) * (Integer.min(i, this.nServidores)/this.nServidores);
    }
    return soma;
  }

}
