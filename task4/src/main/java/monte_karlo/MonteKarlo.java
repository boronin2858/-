package monte_karlo;

import java.util.Map;
import java.util.Random;
import java.util.HashMap;


public class MonteKarlo {
  /* Рассчет попадания точки в единичный радиус */
  private int getY(double x, double y) {
    return (x * x + y * y < 1.0) ? 1 : 0;
  }

  /* Рассчет кол-ва точек и точности ПИ */
  public Map<Integer, Double> getData() {
    Random rnd = new Random();
    Map<Integer, Double> data = new HashMap<Integer, Double>();

    int k = 0;
    for (int i = 1; i <= MonteKarloConstants.LIMIT; i++) {
      double x = rnd.nextFloat();
      double y = rnd.nextFloat();
      k += this.getY(x, y);

      // каждую 1000 сохраняем для графика
      if (i % 1000 == 0) {
        data.put(i, 4 * k / MonteKarloConstants.LIMIT);
      }

    }
    return data;
  }
}
