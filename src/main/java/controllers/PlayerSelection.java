package controllers;
///Entity generator


import entities.ComputerShip;
import entities.Guess;
import entities.Ship;
import entities.UserShip;

import java.util.List;
import java.util.logging.Logger;

public class PlayerSelection {
    private Logger log = Logger.getLogger("PlayerSelection");
    private ComputerShip computerShip2 = new ComputerShip();


    public Guess createGuess(int x, int y) {
        return new Guess(x, y);

    }

    public Ship createShip(int x, int y) {
        return new ComputerShip(x, y);
    }


    public List<Integer> initList() {
       return computerShip2.listOfComputerPanels();


    }

    public int generateComputerPanel(List list) {
        return computerShip2.randomComputerPanelGenerator(list);
    }


    public UserShip createUserShip(int x, int y, int size) {
        return new UserShip(x, y, size);
    }

}

 /* 'Computer winner mode'
  public ArrayList<Integer> initList(){
        ArrayList<Integer> list = computerShip2.debugComputerHits();
        return list;

    }
}




*/
