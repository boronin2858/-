package sorts;

/*
 * Пузырьковая сортировка
 */
public class BubbleSort extends Sort {

    public BubbleSort() {
        super();
        this.name = "bubble";
    }

    @Override
    public void main(Integer[] data) {
        for (int i = data.length - 1; i >= 1; i--) {
            for (int j = 0; j < i; j++) {
                if (data[j] > data[j + 1]) {
                    int buf = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = buf;
                }
            }
        }
    }
}
