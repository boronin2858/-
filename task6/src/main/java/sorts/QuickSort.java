package sorts;

public class QuickSort extends Sort {

    public QuickSort() {
        super();
        this.name = "quick";
    }

    @Override
    public void main(Integer[] data) {
        int low = 0;
        int high = data.length - 1;
        quickSort(data, low, high);
    }

    private void quickSort(Integer[] data, int low, int high) {
        if (data.length == 0)
            return;//завершить выполнение, если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int middleValue = data[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (data[i] < middleValue) {
                i++;
            }

            while (data[j] > middleValue) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = data[i];
                data[i] = data[j];
                data[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(data, low, j);

        if (high > i)
            quickSort(data, i, high);
    }
}
