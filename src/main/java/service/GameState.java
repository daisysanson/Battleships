package service;

import entities.ComputerShip;
import entities.Guess;
import entities.Ship;

import java.awt.*;
import java.util.ArrayList;

public class GameState {

    Remove remove;
    private boolean sizeEntered;
    private boolean validGuess;
    private int outcome;
    private boolean coordsEntered;
    private boolean directionEntered;
    private boolean completeShip;
    private Ship ship;
    private boolean shipHit;
    private ArrayList<Guess> guesses = new ArrayList<>();
    private ArrayList<ComputerShip> computerShips = new ArrayList<>();
    private ArrayList<Component> panels = new ArrayList<>();

    public GameState() {
    }



    public int getOutcome() {
        if (shipHit == true) {
            return outcome = 0;
        } if(validGuess == false){
            return 2;
        }
        return 1;
    }




    public void addCoords(Guess guess) {
        while (true) {
            if (!checkValidGuess(guess)) {
                validGuess = false;
                System.out.println("Panel already selected");
                break;
            } if(checkShipHit(guess) == true) {
                shipHit = true;
                System.out.println("you've hit a ship!");
                break;

            }
            validGuess = true;
            shipHit = false;
            guesses.add(guess);
            break;

        }

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




        public boolean checkValidGuess (Guess guess){
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



