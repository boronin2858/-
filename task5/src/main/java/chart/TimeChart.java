package chart;

import org.jfree.data.xy.XYSeries;
import profiler.Result;

import java.util.ArrayList;

public class TimeChart extends Chart {

    public TimeChart() {
        super();
        this.title = "График потребления времени";
        this.xAxis = "Размер матрицы";
        this.yAxis = "Время (сек)";
        this.fileName = "timeChart.png";
    }

    @Override
    public XYSeries createSeries(ArrayList<Result> results, String name) {
        XYSeries series = new XYSeries(name);
        for (Result result : results) {
            series.add(result.getSize(), result.getTime());
        }
        return series;
    }
}
