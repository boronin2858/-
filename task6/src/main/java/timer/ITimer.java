package timer;

/*
 * Интерфейс времени сортировки
 */
public interface ITimer {
    void startTimer();

    void stopTimer();

    void resetTimer();

    long getResultTime();
}
