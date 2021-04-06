package generators;

import java.util.Iterator;

public abstract class Generator implements Iterator<Integer[]> {
    final int STEP = 100000;     // шаг
    final int STOP = 1000000;   // максимальный размер массива
    final int REP = 5; // кол-во повторений
    public String name;
    public String fileName;
    int current = 0; // начальный размер массива

    @Override
    public boolean hasNext() {
        return current+STEP <= STOP;
    }

    @Override
    public Integer[] next() {
        current += STEP;
        return generateArray();
    }

    @Override
    public void remove() {}

    public Integer[] generateArray() {
        return new Integer[current];
    }
}
