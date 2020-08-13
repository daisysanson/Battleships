package controllers;
///Entity generator

import entities.ComputerShip;
import entities.Guess;
import entities.Ship;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PlayerSelection {
    private Logger log = Logger.getLogger("PlayerSelection");
    private ComputerShip computerShip2 = new ComputerShip();



    public Guess createGuess(int x, int y) {
        Guess guess = new Guess(x, y);
        return guess;
    }

    public ComputerShip createShip(int x, int y) {
        return new ComputerShip(x, y);
    }


    public int initDirection(ArrayList list) {
        int direction = computerShip2.randomNumberGenerator(list);
        return direction;

    }

    public int generateComputerPanel() {
        ArrayList<Integer> list = computerShip2.listOfPanels();
        int randomPanel = computerShip2.randomComputerPanelGenerator(list);
        return randomPanel;
    }



    public boolean checkSideShipValid(int seqPanel) {
        if ((seqPanel <= 0) || (seqPanel > 99)) {
            return false;
        }
        return true;
    }


    public int generateSideShip(int randomPanel){
         ArrayList<Integer> list = computerShip2.listOfDirections();
        int seqPanel = 0;
        int direction = initDirection(list);

        while (true) {
            if (direction == 1) { //down
                seqPanel = randomPanel + 10;
            }
            if (direction == 2) {//up
                seqPanel = randomPanel - 10;
            }
            if (direction == 3) { //left
                seqPanel = randomPanel - 1;
            }
            if (direction == 4) { //right
                seqPanel = randomPanel + 1;
            }
            if (checkSideShipValid(seqPanel)) {
                log.info("Direction is " + direction);
                log.info("Random Panel generated at: " + randomPanel);
                log.info("Seq Panel generated at: " + seqPanel);
                break;

            } else {
                seqPanel = 0;
                direction = initDirection(list);

            }

        }
        return seqPanel;
    }
}