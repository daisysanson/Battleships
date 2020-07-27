package Controllers;
///LOGIC FOR GUESSES
import service.Guess;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerSelection {
    private PanelLocation panelLocation;
    private ArrayList<Guess> playerCoords = new ArrayList<Guess>();


    public void promptUser() {

    }


    public void getGuess(Guess guess) {
        System.out.println("X coord : " + guess.getX());
        System.out.println("Y coord : " + guess.getY());

    }


    public Guess createGuess(int x, int y) {
        Guess guess = new Guess(x, y);
        playerCoords.add(guess);
        getGuess(guess);
        return guess;
    }

}


