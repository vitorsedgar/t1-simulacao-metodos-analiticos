public class App {

  public static void main(String[] args) {

    String[] split = args[0].split("-");
//    String[] split = "2:4-3:5-2-5".split("-");

    String chegada[] = split[0].split(":");
    String saida[] = split[1].split(":");

    Integer servidores = Integer.valueOf(split[2]);
    Integer capacidade = Integer.valueOf(split[3]);

    Double somatorio = 0.0;

    for (int i = 0; i < 5; i++) {

      System.out.printf("Execução da %dº simulação: \n\n", i+1);
      FilaSimples filaSimples = new FilaSimples(capacidade, servidores,
          Double.valueOf(chegada[0]),
          Double.valueOf(chegada[1]),
          Double.valueOf(saida[0]),
          Double.valueOf(saida[1]));

      somatorio += filaSimples.start();

      System.out.println("------------------------------------------");

    }

    System.out.println("\nAvarage global time: " + (somatorio/5));
  }

}
