import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SimpleQueue {

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
  private Double globalTime;
  private PriorityQueue<QueueEvent> escalonador;
  private int nRandomGenerated = 0;
  private Map<Integer, Double> statusVerifierMap;
  private Double arrivalTimeMin;
  private Double arrivalTimeMax;
  private Double terminationTimeMin;
  private Double terminationTimeMax;

  public SimpleQueue(int queueSize, int nServidores, Double arrivalTimeMin,
      Double arrivalTimeMax, Double terminationTimeMin, Double terminationTimeMax) {
    this.tamanhoMaximoDaFila = queueSize;
    this.nServidores = nServidores;
    this.arrivalTimeMin = arrivalTimeMin;
    this.arrivalTimeMax = arrivalTimeMax;
    this.terminationTimeMin = terminationTimeMin;
    this.terminationTimeMax = terminationTimeMax;
    escalonador = new PriorityQueue<QueueEvent>(new EventQueueComparator());
    perda = 0;
    globalTime = 0.0;
    fila = 0;
    statusVerifierMap = new HashMap<>();
    for (int i = 0; i <= queueSize; i++) {
      statusVerifierMap.put(i, 0.0);
    }
  }

  public Double start() {
    // Tempo da chegada do primeiro evento é 3 devido as definições do trabalho
    escalonador.add(new QueueEvent(EventType.ENTRADA, 3.0));

    while (nRandomGenerated < 100000) {

      QueueEvent event = escalonador.poll();

      if (event.getEventType().equals(EventType.ENTRADA)) {
        chegada(event);
      } else {
        saida(event);
      }
    }

    System.out.printf(
        "QUEUE FINAL STATUS: \n QUEUE SIZE: %s \n GLOBAL TIME: %s%n \n CLIENTES PERDIDOS: %s \n",
        fila,
        globalTime, perda);

    Double somaDasLinhas = 0.0;

    for (int i = 0; i <= tamanhoMaximoDaFila; i++) {
      somaDasLinhas += statusVerifierMap.get(i);
      System.out.printf("QUEUE HAD SIZE %s PER %s times WITH %s OF PROBABILITY \n", i,
          statusVerifierMap.get(i), getProbability(i));
    }

    //System.out.println("SOMA DAS LINHAS: " + somaDasLinhas);
    //System.out.println("GLOBAL TIME: " + globalTime);
    return globalTime;
  }

  private void saida(QueueEvent event) {
    contabilizaTempo(event.getTime());
    fila--;
    if (fila >= nServidores) {
      agendaEvento(EventType.SAIDA);
    }
  }

  private void chegada(QueueEvent event) {
    contabilizaTempo(event.getTime());
    if (fila < tamanhoMaximoDaFila) {
      fila++;
      if (fila <= nServidores) {
        agendaEvento(EventType.SAIDA);
      }
    } else {
      perda++;
    }
    agendaEvento(EventType.ENTRADA);
  }

  private void contabilizaTempo(Double time) {
    Double queueStatusTime = statusVerifierMap.get(fila);
    statusVerifierMap.put(fila, queueStatusTime + (time - globalTime));
    globalTime = time;
  }

  private String getProbability(int statusIndex) {
    return String.format("%.2f", statusVerifierMap.get(statusIndex) / globalTime * 100) + "%";
  }

  private void agendaEvento(EventType eventType) {
    // Definições do Trabalho:
    // Tempo de Entrada entre 2 e 4
    // Tempo de Saida entre 3 e 5
    nRandomGenerated++;
    escalonador.add(new QueueEvent(eventType,
        eventType.equals(EventType.ENTRADA) ?
            globalTime + RandomNumberGenerator.getNextEventTime(arrivalTimeMin, arrivalTimeMax) :
            globalTime + RandomNumberGenerator
                .getNextEventTime(terminationTimeMin, terminationTimeMax)));
  }
}
