package fift;

import astar.State;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Roman on 26.06.2017.
 */
public class Field extends State {
    private int[] field;
    private int size;
    private int zero;

    public Field(int size, int[] field){
        for (int i = 0; i < field.length; i++) {
            if (field[i] == 0) {
                zero = i;
            }
        }
        this.size = size;
        this.field = field;
    }

    public Field(Field past) {
        this.field = Arrays.copyOf(past.field, past.field.length);
        this.size = past.size;
        this.zero = past.zero;
    }

    public int getInd(int index) {
        return field[index];
    }

    public int getSize() {
        return size;
    }

    public int indexOf(int value) {
        for (int idx = 0; idx < field.length; ++idx) {
            if (field[idx] == value) return idx;
        }
        return -1;
    }

    public int indexOfX(int value) {
        return indexOf(value) % size;
    }

    public int indexOfY(int value) {
        return indexOf(value) / size;
    }

    private void swap(int left, int right) {
        int temp = field[left];
        field[left] = field[right];
        field[right] = temp;
    }

    boolean turnRight() {
        return makeTurn(1, 0);
    }

    boolean turnUp() {
        return makeTurn(0, -1);
    }

    boolean turnLeft() {
        return makeTurn(-1, 0);
    }

    boolean turnDown() {
        return makeTurn(0, 1);
    }

    private boolean makeTurn(int x, int y) {
        if (Math.abs(x) + Math.abs(y) > 1) {
            return false;
        } else if (x == 0 && y == 0) {
            return true;
        }
        int newZeroX = zero % size + x;
        int newZeroY = zero / size + y;
        if (newZeroX >= 0 && newZeroX < size && newZeroY >= 0 && newZeroY < size) {
            int newZero = newZeroX + newZeroY * size;
            swap(zero, newZero);
            zero = newZero;
            return true;
        } else {
            return false;
        }
    }

    void mixing(int cnt) {
        if (cnt <= 0) {
            return;
        }
        int count = 0;
        Random rnd = new Random(System.currentTimeMillis());
        while (count != cnt) {
            boolean success = false;
            switch (rnd.nextInt() % 4) {
                case 0: success = turnDown();
                    break;
                case 1: success = turnLeft();
                    break;
                case 2: success = turnRight();
                    break;
                case 3: success = turnUp();
                    break;
            }
            if (success) {
                ++count;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Field)) {
            return false;
        }

        Field second = (Field)obj;

        return (this.size == second.size) && Arrays.equals(this.field, second.field);
    }

    @Override
    public int hashCode() {
        return size + Arrays.hashCode(field);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int idx = 0; idx < size * size; ++idx) {
            if (idx % size == 0) {
                out.append("\n");
            }
            out.append(field[idx]).append(" ");
        }
        return out.toString();
    }
}