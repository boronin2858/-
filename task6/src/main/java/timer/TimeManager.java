package timer;

import sorts.ISort;

import java.util.concurrent.TimeUnit;

/*
 * Декоратор для вычисления времени работы алгоритма
 */
public class TimeManager implements ISort, ITimer {
    protected ISort instance;
    private long beginTime;
    private long endTime;

    public TimeManager(ISort instance) {
        this.instance = instance;
        this.resetTimer();
    }

    @Override
    public void main(Integer[] data) {
        this.startTimer();
        instance.main(data);
        this.stopTimer();
    }

    @Override
    public long getResultTime() {
        return TimeUnit.NANOSECONDS.toMillis(this.endTime - this.beginTime);
    }

    @Override
    public void startTimer() {
        this.beginTime = System.nanoTime();
    }

    @Override
    public void stopTimer() {
        this.endTime = System.nanoTime();
    }

    @Override
    public void resetTimer() {
        this.beginTime = 0;
        this.endTime = 0;
    }
}
