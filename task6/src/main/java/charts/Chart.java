package charts;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/*
 * Интерфейс графика
 */
public class Chart {

    XYSeriesCollection dataSet = null;
    String title = "";
    String xAxis = "Кол-во элементов";
    String yAxis = "Время выполнения (мс)";
    String fileName = "";
    HashMap<String, XYSeries> series;
    public Chart(String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
        dataSet = new XYSeriesCollection();
        series  = new HashMap<>();
    }

    private XYSeries createSerie(String name) {
        XYSeries serie = new XYSeries(name);

        series.put(name, serie);

        return serie;
    }
    // создание серии
    public void pushToSerie(String name, Integer size, Long time) {
        XYSeries serie = series.get(name);
        if (serie == null) {
            serie = createSerie(name);
        }
        serie.add((double)size, (double)time);
    }

    public void createChart() throws IOException {
        for(XYSeries serie : series.values()){
            this.dataSet.addSeries(serie);
        }

        JFreeChart chart = ChartFactory.createXYLineChart(
                this.title,
                this.xAxis,
                this.yAxis,
                this.dataSet
        );
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        File chartFile = new File(this.fileName);
        ChartUtilities.saveChartAsPNG(chartFile, chart, 1000, 600);
        System.out.printf("Для просмотра графика: %s\n", chartFile.getAbsolutePath());
    }

}