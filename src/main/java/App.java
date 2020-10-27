import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.QueueConfig;
import com.Roteamento;
import com.YamlConfigPojo;
import com.evento.Evento;
import com.evento.EventoChegada;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

  public static void main(String[] args) throws IOException {

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    YamlConfigPojo pojo = mapper.readValue(new File("config.yml"), YamlConfigPojo.class);

    Double somatorio = 0.0;

    for (int i = 0; i < 5; i++) {

      List<Fila> filas = pojo.getQueues().entrySet()
          .stream()
          .map(App::buildFila)
          .peek(fila -> fila.setRoteamentos(
              pojo.getNetwork()
                  .stream()
                  .filter(outputConfig -> outputConfig.getSource().equals(fila.getNome()))
                  .map(outputConfig -> new Roteamento(outputConfig.getTarget(),
                      outputConfig.getProbability()))
                  .collect(Collectors.toList())))
          .collect(Collectors.toList());

      System.out.printf("Execução da %dº simulação: \n\n", i + 1);

      Map<String, Integer> arrivals = pojo.getArrivals();
      String firstQueue = (String) arrivals.keySet().stream().toArray()[0];

      Evento eventoInicial = new EventoChegada(arrivals.get(firstQueue), firstQueue);
      Escalonador.iniciarEscalonador(eventoInicial);
      Contexto.start(filas);

      somatorio += Contexto.tempoGlobal;

      System.out.println("------------------------------------------");

    }

    System.out.println("\nAvarage global time: " + (somatorio / 5));
  }

  private static Fila buildFila(Entry<String, QueueConfig> entry) {
    String s = entry.getKey();
    final Integer servers = entry.getValue().getServers();
    final Optional<Integer> capacity = Optional.ofNullable(entry.getValue().getCapacity());
    final double minArrival = entry.getValue().getMinArrival();
    final double maxArrival = entry.getValue().getMaxArrival();
    final double minService = entry.getValue().getMinService();
    final double maxService = entry.getValue().getMaxService();

    return new Fila(s, capacity, servers, minArrival, maxArrival, minService, maxService);
  }

}
