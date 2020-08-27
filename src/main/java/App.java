public class App {

  public static void main(String[] args) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < 1000; i++) {
      builder.append(RandomNumberGenerator.generateRandomNumber(134775813, Math.pow(2, 32), true));
      builder.append("\n");
    }
    System.out.println(builder.toString());
  }

}
