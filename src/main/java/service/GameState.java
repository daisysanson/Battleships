package service;
import common.Outcomes;

import entities.ComputerShip;
import entities.Guess;
import entities.Ship;
import entities.UserShip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Logger;

public class GameState {

    static Logger log = Logger.getLogger("Game state");

    private Guess guess;
//    private Guess debugGuess;

    private boolean playerWinner = false;
    private boolean isGameOver = false;
    private boolean validGuess = true;
    private boolean computerShipHit = false;
    private boolean userShipHit = false;
    private boolean shipClash = false;
    private int noOfComputerShips = 1;
    private int noOfPlayerShips = 1;
    private ArrayList<Guess> userGuesses = new ArrayList<Guess>();
    private ArrayList<Guess> computerGuesses = new ArrayList<Guess>();
    private ArrayList<ComputerShip> computerShips = new ArrayList<ComputerShip>();
    private ArrayList<UserShip> userShips = new ArrayList<>();

    public GameState() {
    }

    public Outcomes getOutcome(Guess guess) {
        boolean isGameOver = checkGameOver();
        if (isComputerShipHit()) {
            return Outcomes.HIT;

        }
        if (isUserShipHit()) {
            return Outcomes.USERHIT;

        }
        if (!isValidGuess()) {
            return Outcomes.INVALID;

        }
        if (isGameOver) {
            return Outcomes.GAME_OVER;

        } else {
            return Outcomes.MISS;
        }
    }


    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
    }

    public void addUserGuess(Guess guess) {
        computerShipHit = false;
        if (!checkUserValidGuess(guess)) {
            validGuess = false;
        }
        if (checkComputerShipHit(guess)) {
            computerShipHit = true;
        }
        if (checkUserValidGuess(guess)) {
            validGuess = true;
            userGuesses.add(guess);
        }

    }

    public ArrayList<Guess> getUserGuesses() {
        return userGuesses;
    }


    public boolean checkComputerShipHit(Guess guess) {
        ArrayList<ComputerShip> matchingShip = new ArrayList<>();
        Iterator<ComputerShip> it = computerShips.iterator();
        while (it.hasNext()) {
            ComputerShip ship = it.next();
            {
                if ((guess.getX() == ship.getX() && guess.getY() == ship.getY()))
                    matchingShip.add(ship);
            }
        }
        if (matchingShip.size() >= 1) {
            for (ComputerShip ship1 : matchingShip) {
                if ((guess.getX() == ship1.getX() && guess.getY() == ship1.getY())) {
                    computerShipSunk(ship1);
                }
            }
            return true;

        }

        return false;
    }


    public boolean checkUserValidGuess(Guess guess) {
        for (Guess guess1 : userGuesses) {
            if ((guess.getX() == (guess1.getX()) && (guess.getY() == guess1.getY()))) {
                return false;
            }

        }
        for (UserShip ship : userShips ){
            if((guess.getX() == ship.getX() && (guess.getY() == ship.getY()))){
                return false;
            }

        }
        return true;

    }


    public void addComputerGuess(Guess guess) {
        computerShipHit = false;
        userShipHit = false;
        if (!checkComputerValidGuess(guess)) {
            validGuess = false;
        }
        if (checkUserShipHit(guess)) {
            userShipHit = true;
        }
            computerGuesses.add(guess);
        }



    public boolean checkUserShipHit(Guess guess) {
        ArrayList<UserShip> matchingShip = new ArrayList<>();
        Iterator<UserShip> it = userShips.iterator();
        while (it.hasNext()) {
            UserShip ship = it.next();
            if ((guess.getX() == ship.getX() && guess.getY() == ship.getY())) {
                matchingShip.add(ship);
            }

        }
        if (matchingShip.size() >= 1) {
            for (UserShip ship1 : matchingShip) {
                if ((guess.getX() == ship1.getX() && guess.getY() == ship1.getY())) {
                    userShipSunk(ship1);
                }
            }
            return true;

        }

        return false;

    }


    public boolean checkComputerValidGuess(Guess guess) {
        for (Guess guess1 : computerGuesses) {
            if ((guess.getX() == (guess1.getX()) && (guess.getY() == guess1.getY()))) {
                return false;
            }
        }
        return true;
    }


    public void addPlayerShips(UserShip ship) {
        if (checkUserShipClashes(ship) || (checkComputerShipClashes(ship))) {
            shipClash = true;
            return;
        }

        log.info("Ship at x: " + ship.getX() + "and Y" + ship.getY() + " added");
        userShips.add(ship);


    }

    public ArrayList<Guess> getComputerGuesses() {
        return computerGuesses;
    }

    public void addComputerShips(ComputerShip ship) {
        if (checkComputerShipClashes(ship) || (checkUserShipClashes(ship))) {
            shipClash = true;
            return;
        }
        log.info("Computer Ship at x: " + ship.getX() + "and computer ship Y: " + ship.getY() + " added");
        computerShips.add(ship);
    }


    public ArrayList<UserShip> getUserShips() {
        return userShips;
    }

    public ArrayList<ComputerShip> getComputerShips() {
        return computerShips;
    }


    //get this from SHIP object
    public void computerShipSunk(Ship ship) {
        log.info("X: " + ship.getX() + "Y: " + ship.getY() + " has been removed.");
        computerShips.remove(ship);
    }


    public void userShipSunk(Ship ship) {
        log.info("X: " + ship.getX() + "Y: " + ship.getY() + " has been removed.");
        userShips.remove(ship);
    }


    public boolean checkComputerShipClashes(Ship ship) {
        for (Ship ship1 : computerShips) {
            if ((ship1.getX() == (ship.getX()) && (ship1.getY() == ship.getY()))) {
                return true;
            }
        }
        return false;
    }

    public boolean checkUserShipClashes(Ship ship) {
        for (Ship ship1 : userShips) {
            if ((ship1.getX() == (ship.getX()) && (ship1.getY() == ship.getY()))) {
                return true;
            }
        }
        return false;
    }


    public boolean isValidGuess() {
        return validGuess;
    }

    public boolean isComputerShipHit() {
        return computerShipHit;
    }

    public boolean isUserShipHit() {
        return userShipHit;
    }

    public boolean checkGameOver() {
        return (getUserShips().size() == 0)  || userGuesses.size() == 5 || getComputerShips().size() == 0;
    }


    public String calculateWinner() {
        if (computerShips.size() == 0) {
            return "YOU WIN!";
        }
        return "YOU LOSE!";
    }


    public GameState resetGame() {
        GameState state = new GameState();
        return state;
    }


    public int getNoOfComputerShips() {
        return noOfComputerShips;
    }


}





