import java.time.ZonedDateTime;
import java.util.Objects;

public class RandomNumberGenerator {

  static Double seed;

  public static Double generateRandomNumber(Long a, Long maxValue) {
    if (Objects.isNull(seed)) {
      seed = (double) ZonedDateTime.now().getSecond();
    }

    seed = (a * seed) % maxValue;
    return seed / maxValue;
  }

}
