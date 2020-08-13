package entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class ComputerShip extends Ship {
    private Random rand = new Random();

    public ComputerShip(int x, int y, int direction, int size) {
        super(x, y, direction, size);
    }


    public ComputerShip(int x, int y) {
        super(x, y);
    }

    public ComputerShip() {

    }

    public ArrayList<Integer> listOfPanels() {
        int size = 100;
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);

        }
        return list;
    }


    public int randomComputerPanelGenerator(ArrayList list) {
        if (list.size() == 0) {
            list = listOfDirections();
        }
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        list.remove(index);

        return index;
    }


    public ArrayList<Integer> listOfDirections() {
        int size = 4;
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);

        }
        return list;
    }

    public int randomNumberGenerator(ArrayList list) {
        if (list.size() == 0) {
            list = listOfDirections();
        }
            Random rand = new Random();
            int index = rand.nextInt(list.size());
            list.remove(index);

        return index;
    }

}



