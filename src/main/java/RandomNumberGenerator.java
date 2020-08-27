import java.time.ZonedDateTime;
import java.util.Objects;

public class RandomNumberGenerator {

  static Double seed;
  final static int CONSTANTE = 2531011;

  public static Double generateRandomNumber(double a, double maxValue, boolean isCongruenteLinear) {
    if (Objects.isNull(seed)) {
      seed = (double) ZonedDateTime.now().getSecond();
    }
    seed = (a * seed + (isCongruenteLinear ? CONSTANTE : 0)) % maxValue;
    return seed / maxValue;
  }

}
