package main;

import fift.Creator;
import fift.Field;

/**
 * Created by Roman on 26.06.2017.
 */
public class Main {
    public static void main(String[] args) {
        int size = 4;
        Creator fift = Creator.create(size);
        fift.generateField(10);
        System.out.println(fift.getcurrentField());
        while (!fift.isSolved()) {
            for (Field field : fift.solve()) {
                System.out.println(field);
            }
            break;
        }
        System.out.println("Solved");
    }

}