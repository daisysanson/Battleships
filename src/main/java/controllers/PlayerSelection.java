package controllers;
///Entity generator


import entities.ComputerShip;
import entities.Guess;
import entities.Ship;
import entities.UserShip;

import java.util.ArrayList;
import java.util.logging.Logger;

public class PlayerSelection {
    private Logger log = Logger.getLogger("PlayerSelection");
    private ComputerShip computerShip2 = new ComputerShip();


    public Guess createGuess(int x, int y) {
        Guess guess = new Guess(x, y);
        return guess;
    }

    public Ship createShip(int x, int y) {
        return new ComputerShip(x, y);
    }


    public ArrayList<Integer> initList() {
        ArrayList<Integer> list = computerShip2.listOfComputerPanels();
        return list;

    }

    public int generateComputerPanel(ArrayList list) {
        int randomPanel = computerShip2.randomComputerPanelGenerator(list);
        return randomPanel;
    }


    public UserShip createUserShip(int x, int y, int size) {
        return new UserShip(x, y, size);
    }


//
//  public ArrayList<Integer> initList(){
//        ArrayList<Integer> list = computerShip2.debugComputerHits();
//        return list;
//
//    }
}





