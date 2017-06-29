package fift;

import astar.State1;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Roman on 26.06.2017.
 */
public class Field1 implements State1<Field> {

    public int getWeightF(Field from, Field to) {
        if (from.getSize() == to.getSize()) {
            int size = from.getSize();
            int total = 0;
            for (int idx = 0; idx < size * size; ++idx) {
                int currentValue = from.getInd(idx);
                if (currentValue != to.getInd(idx)) {
                    total += getDistance(from.indexOfX(currentValue), from.indexOfY(currentValue),
                            to.indexOfX(currentValue),   to.indexOfY(currentValue));
                }
            }
            return total;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    private int getDistance(int x0, int y0, int x1, int y1) {
        return Math.abs(x0 - x1) + Math.abs(y0 - y1);
    }

    @Override
    public List<Field> getNeigh(Field parent) {
        List<Field> neighList = new LinkedList<>();

        Field neighF;

        neighF = new Field(parent);
        if (neighF.turnUp()) {
            neighList.add(neighF);
        }
        neighF = new Field(parent);
        if (neighF.turnRight()) {
            neighList.add(neighF);
        }
        neighF = new Field(parent);
        if (neighF.turnDown()) {
            neighList.add(neighF);
        }
        neighF = new Field(parent);
        if (neighF.turnLeft()) {
            neighList.add(neighF);
        }

        return neighList;
    }

    @Override
    public int getWeight(Field from, Field to) {
        return getWeightF(from, to);
    }
}