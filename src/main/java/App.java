public class App {

  public static void main(String[] args) {

    String[] split = args[0].split("-");

    String chegada[] = split[0].split(":");
    String saida[] = split[1].split(":");

    Integer servidores = Integer.valueOf(split[2]);
    Integer capacidade = Integer.valueOf(split[3]);

    SimpleQueue simpleQueue = new SimpleQueue(capacidade, servidores,
        Double.valueOf(chegada[0]),
        Double.valueOf(chegada[1]),
        Double.valueOf(saida[0]),
        Double.valueOf(saida[1]));

    simpleQueue.start();
  }

}
