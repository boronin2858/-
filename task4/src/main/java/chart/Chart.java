package chart;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;

import org.jfree.data.xy.XYDataset;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;

/*
* Строитель графиков
*/
public class Chart {
    private String fileName = "chart.png";

    /*
    * Установка данных
    * */
    private XYDataset setData(Map<Integer, Double> data) {
        XYSeries apprSeries = new XYSeries("Процент приближения");
        for (Map.Entry<Integer, Double> item : data.entrySet()) {
            apprSeries.add((double)item.getKey(), item.getValue() * 100/Math.PI);
        }
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(apprSeries);
        return dataset;
    }

    /*
     * Создание графика
     */
    private JFreeChart createChart(XYDataset dataset)
    {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График изменения значения приближения величины числа ПИ",  // заголовок
                "Колво испытаний",                                     // x-axis label
                "Приближение",                                         // y-axis label
                dataset                                                         // данные
        );
        return chart;
    }

    /*
     * Создание графика
     *
     * @param data словарь <Кол-во испытаний, Значение>
     */
    public String createChart(Map<Integer, Double> data) throws IOException {
        JFreeChart chart = createChart(setData(data));
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));

        //файл для сохранения
        File chartFile = new File(this.fileName);
        ChartUtilities.saveChartAsPNG(chartFile, chart, 1000, 600);
        return chartFile.getAbsolutePath();
    }
}
