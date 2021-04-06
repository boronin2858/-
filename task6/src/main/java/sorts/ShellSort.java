package sorts;

public class ShellSort extends Sort {

    public ShellSort() {
        super();
        this.name = "shell";
    }

    @Override
    public void main(Integer[] data) {
        int gap = data.length / 2;
        while (gap >= 1) {
            for (int right = 0; right < data.length; right++) {
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (data[c] > data[c + gap]) {
                        int buf = data[c];
                        data[c] = data[c + gap];
                        data[c + gap] = data[c];
                    }
                }
            }

            gap /=2;
        }
    }

}
