package service;

import common.Outcomes;
import entities.ComputerShip;
import entities.Guess;
import entities.Ship;


import java.util.ArrayList;
import java.util.logging.Logger;

public class GameState {

    static Logger log = Logger.getLogger("Game state");

    private Guess guess;
    private boolean playerWinner = false;
    private boolean isGameOver = false;
    private boolean validGuess = true;
    private boolean shipHit = false;
    private boolean shipClash = false;
    private int noOfComputerShips = 2; //will be an option in later iteration
    private ArrayList<Guess> guesses = new ArrayList<Guess>();
    private ArrayList<ComputerShip> computerShips = new ArrayList<ComputerShip>();

    public GameState() {
    }

    public Outcomes getOutcome(Guess guess) {
        boolean isGameOver = checkGameOver();
        {
            if (isShipHit())
                return Outcomes.HIT;
        }
        if (!isValidGuess()) {
            return Outcomes.INVALID;
        }
        if (isGameOver) {
            return Outcomes.GAME_OVER;
        }
        return Outcomes.MISS;
    }

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
    }

    public void addCoords(Guess guess) {
        shipHit = false;
        if (!checkValidGuess(guess)) {
            validGuess = false;

        }
        if (checkShipHit(guess)) {
            shipHit = true;


        }
        if (checkValidGuess(guess)) {
            validGuess = true;
            guesses.add(guess);
        }

    }


    public void shipSunk(Ship ship) {
        log.info("X: " + ship.getX() + "Y: " + ship.getY() + " has been removed.");
        computerShips.remove(ship);
    }


    public ArrayList<Guess> getGuesses() {
        return guesses;
    }

    public void addComputerShips(ComputerShip ship) {
        if (checkClashes(ship)) {
            shipClash = true;


        }


        computerShips.add(ship);
    }


    public ArrayList<ComputerShip> getComputerShips() {
        return computerShips;
    }

    public boolean checkShipHit(Guess guess) {
        for (ComputerShip ship : computerShips) {
            if (!(guess.getX() == ship.getX() && guess.getY() == ship.getY())) {
                return false;
            }
            shipSunk(ship);
            break;
        }
        return true;
    }


    public boolean checkValidGuess(Guess guess) {
        for (Guess guess1 : guesses) {
            if ((guess.getX() == (guess1.getX()) && (guess.getY() == guess1.getY()))) {
                return false;
            }
        }
        return true;
    }


    public boolean checkClashes(Ship ship) {
        for (Ship ship1 : computerShips) {
            if ((ship1.getX() == (ship.getX()) && (ship1.getY() == ship.getY()))) {
                return true;
            }
        }
        return false;
    }


    public boolean isValidGuess() {
        return validGuess;
    }

    public boolean isShipHit() {
        return shipHit;
    }


    public boolean checkGameOver() {
        return (guesses.size() >= 6 || getComputerShips().size() == 0);
    }


    public String calculateWinner() {
        if (shipHit) {
            return "YOU WIN!";
        }
        return "YOU LOSE!";
    }

    public boolean isPlayerWinner() {
        return playerWinner;
    }


    public void setPlayerWinner(boolean playerWinner) {
        this.playerWinner = playerWinner;
    }

    public GameState resetGuesses() {
        isGameOver = false;
        GameState state = new GameState();
        return state;
    }


    public int getNoOfComputerShips() {
        return noOfComputerShips;
    }
}





