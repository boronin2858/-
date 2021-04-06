package sorts;

public class InsertionSort extends Sort {

    public InsertionSort() {
        super();
        this.name = "insertion";
    }

    @Override
    public void main(Integer[] data) {
        for (int j = 0; j < data.length; j++) {
            int val = data[j];
            int i = j - 1;
            for (; i >= 0; i--) {
                if (val < data[i]) {
                    data[i + 1] = data[i];
                } else {
                    break;
                }
            }
            data[i + 1] = val;
        }
    }
}
