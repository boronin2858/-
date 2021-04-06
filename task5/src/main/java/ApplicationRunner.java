import algorithms.GausJordan;
import algorithms.Kramer;
import chart.MemoryChart;
import chart.TimeChart;
import org.jfree.data.general.Series;
import profiler.Profiler;
import profiler.Result;
import reader.Reader;

import java.io.IOException;
import java.util.ArrayList;


public class ApplicationRunner {


    public static void main(String[] args) throws InterruptedException {
        Reader reader = new Reader();
        Profiler profiler = new Profiler();

        ArrayList<Result> gausResultList = new ArrayList<>();
        ArrayList<Result> kramerResultList = new ArrayList<>();
        while (reader.hasNext()) {
            double[][] matrix = reader.next(); // читаем матрицу из json файла
            // запускаем алгорит Гауса
            GausJordan gausJordan = new GausJordan(matrix);
            gausResultList.add(profiler.test(gausJordan));
            // запускаем алгоритм краммера
            Kramer kramer = new Kramer(matrix);
            kramerResultList.add(profiler.test(kramer));
        }


        // рисуем графики
        MemoryChart memoryChart = new MemoryChart();
        TimeChart timeChart = new TimeChart();
        memoryChart.addSeries(memoryChart.createSeries(gausResultList, "Гаус-Джордан"));
        timeChart.addSeries(timeChart.createSeries(gausResultList, "Гаус-Джордан"));

        memoryChart.addSeries(memoryChart.createSeries(kramerResultList, "Краммер"));
        timeChart.addSeries(timeChart.createSeries(kramerResultList, "Краммер"));

        try {
            memoryChart.createChart();
            timeChart.createChart();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
