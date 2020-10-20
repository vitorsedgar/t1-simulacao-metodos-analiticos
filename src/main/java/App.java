import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.Roteamento;
import com.YamlConfigPojo;
import com.evento.Evento;
import com.evento.EventoCH1;
import com.evento.EventoChegada;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

  public static void main(String[] args) throws IOException {

    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    YamlConfigPojo pojo = mapper.readValue(new File("config.yml"), YamlConfigPojo.class);

    Stream<String> linhas = Files.lines(Paths.get("config.txt"));
    Double somatorio = 0.0;

    for (int i = 0; i < 5; i++) {

      List<Fila> filas = new LinkedList<>();

      pojo.getQueues().forEach((s, queueConfig) -> {
        filas.add(new Fila(
            queueConfig.getCapacity(),
            queueConfig.getServers(),
            queueConfig.getMinArrival(),
            queueConfig.getMaxArrival(),
            queueConfig.getMinService(),
            queueConfig.getMaxService(),
            new ArrayList<Roteamento>() {{
              addAll(pojo.getNetwork()
                  .stream()
                  .filter(outputConfig -> outputConfig.getSource().equals(s))
                  .map(outputConfig -> new Roteamento(outputConfig.getTarget(), outputConfig.getProbability()))
                  .collect(Collectors.toList()));
            }}));
      });

      System.out.printf("Execução da %dº simulação: \n\n", i+1);

      Evento eventoInicial = new EventoCH1(pojo.getArrivals().get(0));
      //Evento eventoInicial = new EventoChegada(2.5, 0);
      Escalonador.iniciarEscalonador(eventoInicial);
      Contexto.start(filas);

      somatorio += Contexto.tempoGlobal;

      System.out.println("------------------------------------------");

    }

    System.out.println("\nAvarage global time: " + (somatorio/5));
  }

  public static String convertYamlToJson(String yaml) throws IOException {
    ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
    Object obj = yamlReader.readValue(yaml, Object.class);

    ObjectMapper jsonWriter = new ObjectMapper();
    return jsonWriter.writeValueAsString(obj);
  }

}
