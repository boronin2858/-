package chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.data.xy.XYDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;
import profiler.Result;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Интерфейс графика
 */
public abstract class Chart {

    XYSeriesCollection dataSet = null;
    String title = "";
    String xAxis = "";
    String yAxis = "";
    String fileName = "";

    Chart() {
        dataSet = new XYSeriesCollection();
    }

    // создание серии
    public abstract XYSeries createSeries(ArrayList<Result> results, String name);

    public void addSeries(XYSeries series) {
        this.dataSet.addSeries(series);
    }

    public void createChart() throws IOException {
        JFreeChart chart = ChartFactory.createXYLineChart(
                this.title,
                this.xAxis,
                this.yAxis,
                this.dataSet
        );
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        File chartFile = new File(this.fileName);
        ChartUtilities.saveChartAsPNG(chartFile, chart, 1000, 600);
        System.out.printf("Для просмотра графика памяти откройте файл: %s\n", chartFile.getAbsolutePath());
    }

}