package profiler;

public class Result {
    int size;
    double time;
    double memory;

    public Result(int size, long time, long memory) {
        this.size = size; // размер матрицы
        this.time = (double) time / 1_000_000_000; // время выполнения в наносекундах
        this.memory = (double) memory; // занимаемая память
    }

    @Override
    public String toString() {
        return "Result{" +
                "time=" + time +
                ", memory=" + memory +
                '}';
    }

    public int getSize() {
        return this.size;
    }

    public double getTime() {
        return this.time;
    }

    public double getMemory() {
        return this.memory;
    }
}
