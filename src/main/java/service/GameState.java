package service;

import enumeration.Outcomes;

import enumeration.Winner;
import entities.ComputerShip;
import entities.Guess;
import entities.Ship;
import entities.UserShip;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class GameState {

    static Logger log = Logger.getLogger("Game state");

    private Guess guess;
//    private Guess debugGuess;

    private boolean isGameOver = false;
    private boolean partialHit = false;
    private boolean validGuess = true;
    private boolean computerShipHit = false;
    private boolean userShipHit = false;
    private boolean shipClash = false;
    private int noOfComputerShips = 6;
    private int guesses = 8;
    private int pair;
    private List<Guess> userGuesses = new ArrayList<Guess>();
    private List<Guess> computerGuesses = new ArrayList<Guess>();
    private List<ComputerShip> computerShips = new ArrayList<ComputerShip>();
    private List<UserShip> userShips = new ArrayList<>();
    private List<Guess> debugComputerGuesses = new ArrayList<Guess>();
    private List<Integer> intValueOfPanel;

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
        if (isPartialHit()) {
            return Outcomes.PARTIAL_HIT;
        }
        if (isGameOver) {
            return Outcomes.GAME_OVER;

        } else {
            return Outcomes.MISS;
        }
    }

    public int getGuesses() {
        return guesses;
    }

    public void setGuesses(int guesses) {
        this.guesses = guesses;
    }

    public Guess getGuess() {
        return guess;
    }

    public void setGuess(Guess guess) {
        this.guess = guess;
    }

    public void addUserGuess(Guess guess) {
        userShipHit = false;
        partialHit = false;
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
            guesses--;
        }

    }


    public List<Guess> getUserGuesses() {
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
        for (UserShip ship : userShips) {
            if ((guess.getX() == ship.getX() && (guess.getY() == ship.getY()))) {
                return false;
            }

        }
        return true;

    }


    public void addComputerGuess(Guess guess) {
        computerShipHit = false;
        partialHit = false;
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
                    if (ship1.getSize() >= 2) {
                        hitShip(ship1);
                        partialHit = true;
                        return false;
                    }


                }
            }
            return true;
        }

        return false;

    }


    public void checkProximity(int panel) {
        int number = 0;
        for (int i = 0; i < intValueOfPanel.size(); i++) {
            number = (int) intValueOfPanel.get(i);//panels selected
            int result = panel - number;
            if (result == 0) {
                continue;
            }
            if ((result == 1) || (result == -1)) {
                this.setPair(number);
                return;
            }
            if (result % 10 == 0) { // if its a multiple of 10
                if ((result >= 20) || (result <= -20)) { //if bigger than 2 rows difference than go back through panel selection
                    continue;
                } else {
                    this.setPair(number);
                    return;
                }
            }
            this.setPair(0);
        }

    }


    public int getPair() {
        return pair;
    }

    public void setPair(int pair) {
        this.pair = pair;
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

    public List<Guess> getComputerGuesses() {
        return computerGuesses;
    }

    public void addComputerShips(ComputerShip ship) {
        if (checkComputerShipClashes(ship) || (checkUserShipClashes(ship))) {
            shipClash = true;
            log.info("Ship clashed; selecting another panel... ");
            return;
        }
        log.info("Computer Ship at x: " + ship.getX() + "and computer ship Y: " + ship.getY() + " added");
        computerShips.add(ship);
    }


    public List<UserShip> getUserShips() {
        return userShips;
    }

    public List<ComputerShip> getComputerShips() {
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


    public int createShipSize(int panel, List intValueOfPanel) {
        this.intValueOfPanel = intValueOfPanel;

        //checking if values already in the list
        int size = 1;
        int number = 0;
        for (int i = 0; i < intValueOfPanel.size(); i++) {
            number = (int) intValueOfPanel.get(i);
            int result = panel - number;
            if (result == 0) {
                continue;
            }
            if ((result == 1) || (result == -1)) {
                size++;
                continue;
            }
            if (result % 10 == 0) {
                if ((result >= 20) || (result <= -20)) { //if bigger than 2 rows difference in size than go back through panel selection
                    continue;
                }

                size++;
            }
        }
        return size;
    }


    public boolean hitShip(UserShip ship) {
        if (ship.getSize() == 2) {
            ship.setSize(0);
            return true;
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
        return (getUserShips().size() == 0) || userGuesses.size() == 8 || getComputerShips().size() == 0;
    }


    public Winner calculateWinner() {
        if (computerShips.size() == 0) {
            return Winner.PLAYER;
        } if(computerShips.size() > 0 && userShips.size() > 0){
            return Winner.DRAW;
        }
        return Winner.COMPUTER;
    }

    public boolean isPartialHit() {
        return partialHit;
    }

    public void setPartialHit(boolean partialHit) {
        this.partialHit = partialHit;
    }

    public GameState resetGame() {
        GameState state = new GameState();
        return state;
    }


    public int getNoOfComputerShips() {
        return noOfComputerShips;
    }


////COMPUTER WINNER////

//
//    public void setComputerGuessesToUser() {
//        for (UserShip ship : userShips) {
//            Guess guess = new Guess(ship.getX(), ship.getY());
//            debugComputerGuesses.add(guess);
//        }
//    }
//
//
//    public ArrayList<Guess> getDebugComputerGuesses() {
//        return debugComputerGuesses;
//    }
//
//    public void setDebugComputerGuesses(ArrayList<Guess> debugComputerGuesses) {
//        this.debugComputerGuesses = debugComputerGuesses;
//    }


}





