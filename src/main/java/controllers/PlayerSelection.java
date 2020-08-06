package controllers;
///Entity generator

import entities.ComputerShip;
import entities.Guess;

public class PlayerSelection {

    public Guess createGuess(int x, int y) {
        Guess guess = new Guess(x, y);
        return guess;
    }


    public ComputerShip createShip(int x, int y) {
        return new ComputerShip(x, y);
//will be updated later to include a direction and size
    }


    public int createComputerCoords() {
        ComputerShip computerShip = new ComputerShip();
        int randomPanel = computerShip.randomPanelGenerator();
        System.out.println("Panel generated at: " + randomPanel);
        return randomPanel;

    }
}

