package entities;

import java.util.ArrayList;
import java.util.Random;


public class ComputerShip extends Ship {
    private Random rand = new Random();
    private int oldValue;


    public ComputerShip(int x, int y, int direction, int size) {
        super(x, y, direction, size);
    }

    public ComputerShip(int x, int y) {
        super(x, y);
    }

    public ComputerShip() {
    }

    public int randomPanelGenerator() {
        int size = 100;

        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);
        }

        Random rand = new Random();
        int index = rand.nextInt(list.size());
        list.remove(index);

        return index;
    }


    public void randomNumberGenerator() {
    }

}


