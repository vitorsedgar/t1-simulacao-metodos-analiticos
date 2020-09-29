import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FilaSimples {

  // FCFS - First-Come, First-Served
  // LCFS - Last-Come, First-Served

  // Capacidade:
  // Fila de Capacidade Finita: cliente pode ser barrado caso a fila esteja cheia
  // Fila de Capacidade Infinita: cliente nunca é recusado

  // Número de Servidores: quantidade de "Atendentes"

  private int fila;
  private int tamanhoMaximoDaFila;
  private int nServidores;
  private int perda;
  private Double tempoGlobal;
  private PriorityQueue<Evento> escalonador;
  private int nRandomGerados = 0;
  private Map<Integer, Double> statusMap;
  private Double tempoChegadaMinimo;
  private Double tempoChegadaMaximo;
  private Double tempoSaidaMinimo;
  private Double tempoSaidaMaximo;

  public FilaSimples(int tamanhoMaximoDaFila, int nServidores, Double tempoChegadaMinimo,
                     Double tempoChegadaMaximo, Double tempoSaidaMinimo, Double tempoSaidaMaximo) {
    this.tamanhoMaximoDaFila = tamanhoMaximoDaFila;
    this.nServidores = nServidores;
    this.tempoChegadaMinimo = tempoChegadaMinimo;
    this.tempoChegadaMaximo = tempoChegadaMaximo;
    this.tempoSaidaMinimo = tempoSaidaMinimo;
    this.tempoSaidaMaximo = tempoSaidaMaximo;
    this.escalonador = new PriorityQueue<>(new EventoComparator());
    this.perda = 0;
    this.tempoGlobal = 0.0;
    this.fila = 0;
    this.statusMap = new HashMap<>();
    
    for (int i = 0; i <= tamanhoMaximoDaFila; i++) {
      statusMap.put(i, 0.0);
    }
  }

  public Double start() {
    // Tempo da chegada do primeiro evento é 3 devido as definições do trabalho
    escalonador.add(new Evento(TipoEvento.ENTRADA, 3.0));

    while (nRandomGerados < 100000) {

      Evento evento = escalonador.poll();

      if (TipoEvento.ENTRADA.equals(evento.getTipoEvento())) {
        chegada(evento);
      } else {
        saida(evento);
      }
    }

    System.out.printf(
        "QUEUE FINAL STATUS: \n QUEUE SIZE: %s \n GLOBAL TIME: %s%n \n CLIENTES PERDIDOS: %s \n",
        fila,
        tempoGlobal, perda);

    for (int i = 0; i <= tamanhoMaximoDaFila; i++) {
      System.out.printf("QUEUE HAD SIZE %s PER %s times WITH %s OF PROBABILITY \n", i,
          statusMap.get(i), getProbabilidade(i));
    }
    
    return tempoGlobal;
  }

  private void saida(Evento evento) {
    contabilizaTempo(evento.getTempo());
    fila--;
    if (fila >= nServidores) {
      agendaEvento(TipoEvento.SAIDA);
    }
  }

  private void chegada(Evento evento) {
    contabilizaTempo(evento.getTempo());
    if (fila < tamanhoMaximoDaFila) {
      fila++;
      if (fila <= nServidores) {
        agendaEvento(TipoEvento.SAIDA);
      }
    } else {
      perda++;
    }
    agendaEvento(TipoEvento.ENTRADA);
  }

  private void contabilizaTempo(Double time) {
    Double tempoStatusFila = statusMap.get(fila);
    statusMap.put(fila, tempoStatusFila + (time - tempoGlobal));
    tempoGlobal = time;
  }

  private String getProbabilidade(int statusIndex) {
    return String.format("%.2f", statusMap.get(statusIndex) / tempoGlobal * 100) + "%";
  }

  private void agendaEvento(TipoEvento TipoEvento) {
    // Definições do Trabalho:
    // Tempo de Entrada entre 2 e 4
    // Tempo de Saida entre 3 e 5
    nRandomGerados++;
    escalonador.add(new Evento(TipoEvento,
        TipoEvento.equals(TipoEvento.ENTRADA) ?
            tempoGlobal + GeradorNumeroAleatorio.getNextEventTime(tempoChegadaMinimo, tempoChegadaMaximo) :
            tempoGlobal + GeradorNumeroAleatorio
                .getNextEventTime(tempoSaidaMinimo, tempoSaidaMaximo)));
  }
}
