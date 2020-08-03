package service;

import entities.ComputerShip;
import entities.Guess;
import entities.Ship;

import java.awt.*;
import java.util.ArrayList;

public class GameState {

    Remove remove = new Remove();

    private boolean playerWinner;
    private boolean isGameOver;
    private boolean validGuess;
    private boolean shipHit;
    private ArrayList<Guess> guesses = new ArrayList<>();
    private ArrayList<ComputerShip> computerShips = new ArrayList<>();
    private ArrayList<Component> panels = new ArrayList<>();

    public GameState() {
    }


    public int getOutcome() {
        boolean isGameOver = checkGameOver();
        if (isShipHit()) {
            return 0;
        }
        if (!isValidGuess()) {
            return 2;
        }
        if (isGameOver) {
            return 3;
        }
        return 1;
    }


    public void addCoords(Guess guess) {
        shipHit = false;
        if (!checkValidGuess(guess)) {
            validGuess = false;
            System.out.println("Panel already selected");
        }

        if (checkShipHit(guess) == true) {
            shipHit = true;
            System.out.println("you've hit a ship!");
        }
        validGuess = true;
        guesses.add(guess);
    }

    public ArrayList<ComputerShip> addComputerShips(ComputerShip ship) {
        computerShips.add(ship);
        return computerShips;
    }


    public boolean checkShipHit(Guess guess) {
        for (Ship ship : computerShips) {
            if (!(guess.getX() == ship.getX() && guess.getY() == ship.getY())) {
                return false;
            }
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


    public ArrayList<Guess> getGuesses() {
        return guesses;
    }

    public boolean isValidGuess() {
        return validGuess;
    }

    public boolean isShipHit() {
        return shipHit;
    }

    public boolean checkGameOver() {
        return (guesses.size() >= 2 || shipHit);
    }


    public String calculateWinner() {
        if (shipHit == true) {
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

    public void resetGuesses() {
        remove.removeValues(guesses);
        remove.removeValues(computerShips);
        shipHit = false;
        isGameOver = false;
        validGuess = true;

        }
    }






//        (coordinatesSelected.contains(guess.getX()) && coordinatesSelected.contains(guess.getY())){
//            return !validGuess;
//        } else{
//            return validGuess;
//        }}



//    public int shipDirection(int direction, Ship ship) {
//        return direction;
//    }
//
//    public int shipSize(int size, Ship ship) {
//        return size;
//    }

//    public boolean isCompleteShip(Ship ship) {
//        if ((directionEntered == true) && (coordsEntered == true) && (sizeEntered == true)) {
//            userShips.add(ship);
//            return completeShip = true;
//        } else {
//            return false;
//        }
//    }
//}



//    public boolean isValidDirection(Ship ship, Board board) {
//        if ((x == -1) || (y == -1)) {
//            System.out.print("This will cause the ship to appear out of the grid! Please pick another direction");
//            removePlayerCoordinates(userSize, playerDirection, x, y);
//            return false;
//        } else {
//            shipCounter++;
//            return true;
//        }



