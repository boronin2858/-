package chart;

import org.jfree.data.xy.XYSeries;
import profiler.Result;

import java.util.ArrayList;

public class MemoryChart extends Chart {

    public MemoryChart() {
        super();
        this.fileName = "memoryChart.png";
        this.title = "График потребления памяти";
        this.xAxis = "Размер матрицы";
        this.yAxis = "Память (байт)";
    }

    @Override
    public XYSeries createSeries(ArrayList<Result> results, String name) {
        XYSeries series = new XYSeries(name);
        for (Result result : results) {
            series.add(result.getSize(), result.getMemory());
        }
        return series;
    }
}
