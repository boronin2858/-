package generators;


import java.util.ArrayList;
import java.util.Collections;

/*
 * равномерное распределение
 */
public class NormalDist extends Generator {

    public NormalDist() {
        super();
        this.name = "Нормальное распределение";
        this.fileName = "normal.png";
    }

    public Integer[] generateArray() {
        Integer[] array = super.generateArray();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i<current/REP; i++) {
            for (int j = 0; j<REP; j++) {
                arrayList.add(i);
            }
        }

        // размешаем список
        Collections.shuffle(arrayList);
        return arrayList.toArray(array);
    }
}
