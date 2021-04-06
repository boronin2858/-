package algorithms;

public class GausJordan extends Algorithm {

    boolean[] busyLine; // Использованные строки для разрешающих элементов

    public GausJordan(double[][] matrix) {
        super(matrix);
        busyLine = new boolean[this.size];
    }

    public double[] getResult() {
        double[] result = new double[this.size];
        for (int i = 0; i < this.size; i++)
            for (int j = 0; j < this.size; j++) {
                if (this.matrix[i][j] == 0) continue;

                result[j] = this.matrix[j][this.size];
            }
        return result;
    }

    public double[] process() {
        for (int colNum = 0; colNum < size; colNum++) {
            int perIdx = this.getPermissiveIdx(this.getColumn(colNum));
            if (perIdx == -1) throw new NullPointerException();
            double perValue = matrix[perIdx][colNum];
            // если разрешающий элемент не равен единице, то необходимо его привести к 1
            if (perValue != 1) {
                this.convertToOne(perIdx, perValue);
            }

            this.zeroingStrings(perIdx, colNum);
            busyLine[perIdx] = true;
        }
        return this.getResult();
    }

    /*
     * Зануление строки
     * @param perIdx индекс разрешающего элемента
     * @param colNum номер колонки из которой требуется взять значение
     */
    private void zeroingStrings(int perIdx, int colNum) {
        for (int i = 0; i < size; i++) {
            if (i == perIdx) continue;
            double strValue = this.matrix[i][colNum];
            for (int j = 0; j < size + 1; j++) {
                this.matrix[i][j] -= strValue * this.matrix[perIdx][j];
            }
        }
    }

    /*
     * Получить столбец из матрицы
     * @param matrix матрица из которой требуется получить столбец
     * @param colNum Номер столбца
     */
    private double[] getColumn(int colNum) {

        double[] column = new double[this.size];
        for (int i = 0; i < this.size; i++) {
            column[i] = this.matrix[i][colNum];
        }
        return column;
    }

    /*
     * Получить разрешающий элемент
     * @param column столбец
     */
    private int getPermissiveIdx(double[] column) {
        int idx = -1;
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < this.size; i++) {
            if (!busyLine[i] && column[i] != 0 && Math.abs(column[i]) < Math.abs(min)) {
                idx = i;
                min = column[i];

            }
        }
        return idx;
    }

    /*
     * Преобразовать разрешающий элемент к 1
     * @param matrix матрица
     * @param strNum номер строки для преобразования
     * @param perm разрешающий элемент
     */
    private void convertToOne(int strNum, double perm) {
        for (int j = 0; j < this.size + 1; j++) {
            this.matrix[strNum][j] /= perm;
        }
    }
}
