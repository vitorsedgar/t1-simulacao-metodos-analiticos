import java.time.ZonedDateTime;
import java.util.Objects;

public class RandomNumberGenerator {

  final static int CONSTANTE = 2531011;
  static Double seed;

  public static Double generateRandomNumberFrom(double a, double maxValue,
      boolean isCongruenteLinear) {
    if (Objects.isNull(seed)) {
      seed = (double) ZonedDateTime.now().getSecond();
    }
    seed = (a * seed + (isCongruenteLinear ? CONSTANTE : 0)) % maxValue;
    return seed / maxValue;
  }

  public static Double getNextEventTime(Double min, Double max) {
    Double randomDouble = RandomNumberGenerator
        .generateRandomNumberFrom(134775813, Math.pow(2, 32), true);
    return (max - min) * randomDouble + min;
  }

}
