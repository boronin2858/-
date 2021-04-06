package algorithms;

public abstract class Algorithm {
    public double[][] matrix;
    public int size;

    Algorithm(double[][] matrix) {
        this.matrix = matrix;
        this.size = matrix.length;
    }

    public abstract double[] process();

    public abstract double[] getResult();
}
