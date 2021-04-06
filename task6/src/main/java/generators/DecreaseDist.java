package generators;

import java.util.ArrayList;

public class DecreaseDist extends Generator {
    public  DecreaseDist() {
        super();
        this.name = "Сортированные по убыванию";
        this.fileName = "decrease.png";
    }
    public Integer[] generateArray() {
        Integer[] array = super.generateArray();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = current/REP; i>0; i--) {
            for (int j = 0; j<REP; j++) {
                arrayList.add(i);
            }
        }
        return arrayList.toArray(array);
    }
}
