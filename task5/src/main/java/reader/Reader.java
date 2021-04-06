package reader;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


/*
 * Класс чтения матриц из json
 */
public class Reader implements Iterator<double[][]> {

    JSONArray mainArray;
    Iterator matrixIterator;

    public Reader() {
        try {
            // считывание файла JSON
            FileReader reader = new FileReader("./src/main/data.json");
            JSONParser jsonParser = new JSONParser();
            mainArray = (JSONArray) jsonParser.parse(reader);
            matrixIterator = mainArray.iterator();

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private double[][] createMatrix() throws NoSuchElementException {
        // создание матрицы из файла
        if (matrixIterator.hasNext()) {
            // загружаем матрицу
            JSONArray jsonMatrix = (JSONArray) matrixIterator.next();
            int size = jsonMatrix.size();
            double[][] matrix = new double[size][size + 1];
            // читаем строки в матрице
            for (int i = 0; i < size; i++) {
                JSONArray jsonStr = (JSONArray) jsonMatrix.get(i);
                // читаем столбцы
                for (int j = 0; j < size + 1; j++) {
                    matrix[i][j] = ((Long) jsonStr.get(j)).doubleValue();
                }
            }
            remove();
            return matrix;
        } else {
            throw new NoSuchElementException("Матриц больше нет");
        }
    }

    /*
     * Метод отдающуй считанную матрицу
     */
    @Override
    public double[][] next() throws NoSuchElementException {
        return this.createMatrix();
    }

    /*
     * Удаление считанной матрицы
     */
    @Override
    public void remove() {
        matrixIterator.remove();
    }

    /*
     * Проверка существования матрицы
     */
    @Override
    public boolean hasNext() {
        return (mainArray.size() > 0);
    }


}
