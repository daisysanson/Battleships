package entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class ComputerShip extends Ship {
    private Random rand = new Random();

    public ComputerShip(int x, int y) {
        super(x, y);
        this.rand = new Random();
    }

    public ComputerShip() {

    }

    public List<Integer> listOfComputerPanels() {
        int size = 100;
        List<Integer> list = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);

        }
        return list;
    }


    public int randomComputerPanelGenerator(List list) {
        if (list.isEmpty()) {
            listOfComputerPanels();
        }
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        list.remove(index);

        return index;
    }


    public List<Integer> debugComputerHits() { //check computer selects
        int size = 100;
        List<Integer> list = new ArrayList<>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);

        }
        return list;
    }


}



