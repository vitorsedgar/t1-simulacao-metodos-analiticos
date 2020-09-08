import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class SimpleQueue {

  // FCFS - First-Come, First-Served
  // LCFS - Last-Come, First-Served

  // Capacidade:
  // Fila de Capacidade Finita: cliente pode ser barrado caso a fila esteja cheia
  // Fila de Capacidade Infinita: cliente nunca é recusado

  // Número de Servidores: quantidade de "Atendentes"

  List<QueueEvent> queue;
  private int queueSize;
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
    this.queueSize = queueSize;
    this.nServidores = nServidores;
    this.arrivalTimeMin = arrivalTimeMin;
    this.arrivalTimeMax = arrivalTimeMax;
    this.terminationTimeMin = terminationTimeMin;
    this.terminationTimeMax = terminationTimeMax;
    escalonador = new PriorityQueue<QueueEvent>(new EventQueueComparator());
    perda = 0;
    globalTime = 0.0;
    queue = new ArrayList<QueueEvent>();
    statusVerifierMap = new HashMap<>();
    for (int i = 0; i <= queueSize; i++) {
      statusVerifierMap.put(i, 0.0);
    }
  }

  public void start() {
    // Tempo da chegada do primeiro evento é 3 devido as definições do trabalho
    escalonador.add(new QueueEvent(EventType.ENTRADA, 3.0));

    while (nRandomGenerated < 100000) {

      QueueEvent event = escalonador.poll();

      if (event.getEventType().equals(EventType.ENTRADA)) {
        if (queue.size() < queueSize) {
          updateLogger(event);
          queue.add(event);
        } else {
          globalTime = event.getTime();
          perda++;
        }
        agendaEvento(EventType.ENTRADA);
      } else {
        updateLogger(event);
        queue.remove(0);
      }

      long servidoresOcupados = escalonador.stream()
          .filter(eventFilter -> (eventFilter.getEventType().equals(EventType.SAIDA)))
          .count();

      if (queue.size() >= nServidores && servidoresOcupados < nServidores) {
        agendaEvento(EventType.SAIDA);
      }
    }

    System.out.printf(
        "QUEUE FINAL STATUS: \n QUEUE SIZE: %s \n GLOBAL TIME: %s%n \n CLIENTES PERDIDOS: %s \n",
        queue.size(),
        globalTime, perda);

    for (int i = 0; i < queueSize; i++) {
      System.out.printf("QUEUE HAD SIZE %s PER %s times \n", i, statusVerifierMap.get(i));
    }
  }

  private void updateLogger(QueueEvent event) {
    Double queueStatusTime = statusVerifierMap.get(queue.size());
    statusVerifierMap.put(queue.size(), queueStatusTime + (event.getTime() - globalTime));
    globalTime = event.getTime();
  }

  private void agendaEvento(EventType eventType) {
    // Definições do Trabalho:
    // Tempo de Entrada entre 2 e 4
    // Tempo de Saida entre 3 e 5
    nRandomGenerated++;
    escalonador.add(new QueueEvent(eventType,
        eventType.equals(EventType.ENTRADA) ?
            globalTime + RandomNumberGenerator.getNextEventTime(arrivalTimeMin, arrivalTimeMax) :
            globalTime + RandomNumberGenerator.getNextEventTime(terminationTimeMin, terminationTimeMax)));
  }
}
