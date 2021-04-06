package algorithms;

public class Kramer extends Algorithm {

    double[] dets;
    double baseDet;
    long[] buf;

    public Kramer(double[][] matrix) {
        super(matrix);
        dets = new double[this.size];
        buf = new long[1000000];
    }

    public double[] getResult() {
        double[] result = new double[this.size];
        for (int i = 0; i < this.size; i++)
            result[i] = dets[i] / baseDet;
        return result;
    }

    public double[] process() {

        double[][] m = createMatrixWithoutColumn(this.matrix, this.size);
        this.baseDet = calculateDeterminant(m);

        for (int j = 0; j < this.size; j++) {
            m = createMatrixWithoutColumn(this.matrix, this.size);
            for (int i = 0; i < this.size; i++) {
                m[i][j] = this.matrix[i][this.size];
            }
            dets[j] = calculateDeterminant(m);
        }

        return this.getResult();
    }

    private double[][] createMatrixWithoutColumn(double[][] matrix, int col) {
        int size = matrix.length;
        double[][] result = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = j < col ? matrix[i][j] : matrix[i][j + 1];
            }
        }
        return result;
    }

    private double[][] createMatrixWithoutRow(double[][] matrix, int row) {
        int size = matrix.length;
        double[][] result = new double[size - 1][size];
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size; j++) {
                result[i][j] = i < row ? matrix[i][j] : matrix[i + 1][j];
            }
        }
        return result;
    }

    public double calculateDeterminant(double[][] matrix) {
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double result = 0;
        for (int j = 0; j < matrix.length; j++) {
            result += (j % 2 == 1 ? -1 : 1) * matrix[0][j] * this.calculateDeterminant(this.createMatrixWithoutColumn(this.createMatrixWithoutRow(matrix, 0), j));
        }
        return result;
    }

}
