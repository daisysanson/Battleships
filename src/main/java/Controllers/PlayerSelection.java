package Controllers;
///LOGIC FOR GUESSES
import service.Guess;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerSelection {
    private PanelLocation panelLocation;
    private ArrayList<Guess> playerCoords = new ArrayList<Guess>();



public void promptUser(){

}



    public void getGuess(Guess guess){
        System.out.println(guess.getX());
        System.out.println(guess.getY());

        }



    public void createGuess(int x, int y) {
        Guess guess = new Guess(x, y);

            playerCoords.add(guess);
            getGuess(guess);
        }
    }



