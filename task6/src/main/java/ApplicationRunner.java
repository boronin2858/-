import charts.Chart;
import generators.DecreaseDist;
import generators.Generator;
import generators.IncreaseDist;
import generators.NormalDist;
import sorts.*;
import timer.TimeManager;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ApplicationRunner {

    public static final String RED = "\033[0;31m";     // RED
    public static final String GREEN = "\033[0;32m";   // GREEN
    public static final String COLOR_OFF = "\033[0m";  // COLOR OFF

    public static void main(String[] args) throws IOException {
        // Список генераторов данных
        List<Generator> generators = Arrays.asList(new NormalDist(), new IncreaseDist(), new DecreaseDist());
        // список алгоритмов
        List<Sort> algorithms = Arrays.asList(new BubbleSort(), new InsertionSort(), new QuickSort(), new ShellSort());


        for (Generator generator : generators) {
            System.out.println(generator.name);
            Chart chart = new Chart(generator.name, generator.fileName);
            while (generator.hasNext()) {
                final Integer[] array = generator.next();

                System.out.printf("Size = %s \n", array.length);
                for (final Sort algorithm : algorithms) {
                    // будем запускать каждый алгоритм асинхронно
                    ExecutorService executorService = Executors.newSingleThreadExecutor();

                    Future<Long> future = executorService.submit(() -> {
                        System.out.printf("Sort: %s ", algorithm.name);
                        return getTime(algorithm, array);
                    });

                    try {
                        // если время выполнения больше 1 минуты, то прекращаем выполнение
                        Long time = future.get(60, TimeUnit.SECONDS);
                        System.out.printf(GREEN + "|DONE| Time : %s (mc) \n" + COLOR_OFF, time);
                        chart.pushToSerie(algorithm.name, array.length, time);
                    } catch (TimeoutException e) {
                        System.out.println(RED + "|BREAK| Timeout" + COLOR_OFF);
                        future.cancel(true);
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                        executorService.shutdown();
                    }

                }

            }
            chart.createChart();
        }
    }


    private static Long getTime(Sort algorithm, Integer[] array) {
        TimeManager tManager = new TimeManager(algorithm);
        tManager.main(array);
        return tManager.getResultTime();
    }

}
