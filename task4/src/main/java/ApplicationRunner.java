import java.io.IOException;

import monte_karlo.MonteKarlo;
import chart.Chart;


public class ApplicationRunner {

  public static void main(String[] args) throws IOException {
    MonteKarlo monteKarlo = new MonteKarlo();
    var data = monteKarlo.getData();

    Chart chart = new Chart();
    String fileName = chart.createChart(data);

    System.out.printf("Для просмотра графика откройте файл %s\n", fileName);
  }
}
