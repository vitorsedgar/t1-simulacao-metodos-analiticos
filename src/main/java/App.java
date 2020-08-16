public class App {

  public static void main(String[] args) {
    for (int i = 0; i < 10000; i++) {
      System.out.println(
          RandomNumberGenerator.generateRandomNumber(3L, 15L, true));
    }
  }

}
