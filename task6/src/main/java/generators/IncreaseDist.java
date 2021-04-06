package generators;

import java.util.ArrayList;

/*
 * Сортированный по возрастанию
 */
public class IncreaseDist extends Generator {
    public IncreaseDist() {
        super();
        this.name = "Сортированные по возрастанию";
        this.fileName = "increase.png";
    }

    public Integer[] generateArray() {
        Integer[] array = super.generateArray();
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        for (int i = 0; i<current/REP; i++) {
            for (int j = 0; j<REP; j++) {
                arrayList.add(i);
            }
        }
        return arrayList.toArray(array);
    }
}
