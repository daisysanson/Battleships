package controllers;
///LOGIC FOR GUESSES
import entities.ComputerShip;
import entities.Guess;
import service.GameState;

public class PlayerSelection {
    private PanelLocation panelLocation;
    GameState state = new GameState();




    public void getGuess(Guess guess) {
        System.out.println("X coord : " + guess.getX());
        System.out.println("Y coord : " + guess.getY());

    }


    public Guess createGuess(int x, int y) {
        Guess guess = new Guess(x, y);
        getGuess(guess);
        return guess;
    }


//    public Ship createComputerShip(int x, int y, int direction, int size) {
//        ComputerShip ship = new ComputerShip(x, y, direction, size);
//        return ship;
//
//    }

    public ComputerShip createShip(int x, int y) {
         return new ComputerShip(x, y);
//will be updated later to include a direction and size
    }



    public int createComputerCoords(){
        ComputerShip computerShip = new ComputerShip();
       int randomPanel = computerShip.randomPanelGenerator();
       System.out.println("Panel generated at: " + randomPanel);
       return randomPanel;

    }
}

