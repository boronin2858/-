package profiler;

import algorithms.Algorithm;

import java.lang.management.ManagementFactory;

public class Profiler {
    long beginTime;
    long endTime;
    long beginMemory;
    long endMemory;

    public Profiler() {
        beginTime = endTime = beginMemory = endMemory = 0;
    }

    public Result test(Algorithm algorithm) {
        beginTime = getCurrentTime(); // время на начало выполнения
        beginMemory = getCurrentMemmory(); // память на начало выполнения

        algorithm.process(); // запускаем алгоритм на выполнение

        endMemory = getCurrentMemmory(); // память на конец выполнения
        endTime = getCurrentTime(); // время на конец выполнения

        return new Result(algorithm.size, endTime - beginTime, endMemory - beginMemory);
    }

    /*
     * Возвращает время в наносекундах
     */
    private long getCurrentTime() {
        return System.nanoTime();
    }

    /*
     * Возвращает заданную память в байтах
     */
    private long getCurrentMemmory() {
        return ManagementFactory.getMemoryPoolMXBeans().get(2).getUsage().getUsed();
    }

}
