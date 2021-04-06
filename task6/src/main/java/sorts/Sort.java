package sorts;

public abstract class Sort implements ISort {
    public String name;
    @Override
    public abstract void main(Integer[] data);
}
