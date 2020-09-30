import com.Contexto;
import com.Escalonador;
import com.Fila;
import com.evento.Evento;
import com.evento.EventoChegada;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class App {

  public static void main(String[] args) throws IOException {

    List<String> linhas = Files.lines(Paths.get("config.txt")).collect(Collectors.toList());

    Double somatorio = 0.0;

    for (int i = 0; i < 5; i++) {

      List<Fila> filas = new LinkedList<>();

      linhas.forEach(linha -> {
        String[] split = linha.split("-");
        String[] chegada = split[0].split(":");
        String[] saida = split[1].split(":");
        int servidores = Integer.parseInt(split[2]);
        int capacidade = Integer.parseInt(split[3]);

        filas.add(new Fila(capacidade, servidores,
                Double.valueOf(chegada[0]),
                Double.valueOf(chegada[1]),
                Double.valueOf(saida[0]),
                Double.valueOf(saida[1])));
      });

      System.out.printf("Execução da %dº simulação: \n\n", i+1);

      //Evento eventoInicial = new EventoCH1(2.5);
      Evento eventoInicial = new EventoChegada(3.0);
      Escalonador.iniciarEscalonador(eventoInicial);
      Contexto.start(filas);

      somatorio += Contexto.tempoGlobal;

      System.out.println("------------------------------------------");

    }

    System.out.println("\nAvarage global time: " + (somatorio/5));
  }

}
