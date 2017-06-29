package fift;

import astar.Astar;
import astar.Finding;

import java.util.List;

/**
 * Created by Roman on 26.06.2017.
 */
public class Creator {
    private final int fieldSize;
    private Field currentField;
    private Field terminateField;

    private Creator(int fieldSize, int[] termArrangement) {
        this.fieldSize = fieldSize;
        terminateField = new Field(fieldSize, termArrangement);
    }

    public static Creator create(int size) {
        int[] termArray = new int[size * size];
        for (int i = 0; i < termArray.length; i++) {
            termArray[i] = (i + 1) % termArray.length;
        }
        return new Creator(size, termArray);
    }

    public void generateField(int mixCnt) {
        currentField = new Field(terminateField);
        currentField.mixing(mixCnt);
    }

    public Field getcurrentField() {
        return currentField;
    }

    public boolean isSolved() {
        return terminateField.equals(currentField);
    }

    public List<Field> solve() {
        Astar<Field, Field1> solver = new Astar<>(new Field1());
        Finding<Field> solution =  solver.search(currentField, terminateField);
        currentField = new Field(terminateField);
        return solution.getList();
    }
}