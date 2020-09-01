package entities;

import java.util.ArrayList;
import java.util.Random;


public class ComputerShip extends Ship {
    private Random rand = new Random();

    public ComputerShip(int x, int y) {
        super(x, y);
        this.rand = rand;
    }

    public ComputerShip() {

    }

    public ArrayList<Integer> listOfComputerPanels() {
        int size = 100;
        ArrayList<Integer> list = new ArrayList<Integer>(size);
        for (int i = 1; i <= size; i++) {
            list.add(i);

        }
        return list;
    }


    public int randomComputerPanelGenerator(ArrayList list) {
        if (list.size() == 0 ){
            listOfComputerPanels();
        }
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        list.remove(index);

        return index;
    }


//    public ArrayList<Integer> debugComputerHits() {
//        int size = 3;
//        ArrayList<Integer> list = new ArrayList<Integer>(size);
//        for (int i = 1; i <= size; i++) {
//            list.add(i);
//
//        }
//        return list;
//    }


}



