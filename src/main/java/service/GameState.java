package service;

import entities.Guess;
import entities.Ship;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private boolean sizeEntered;
    private boolean validGuess;
    private boolean coordsEntered;
    private boolean directionEntered;
    private boolean completeShip;
    private Ship ship;
    private ArrayList<Guess> coordinatesSelected = new ArrayList<Guess>();
    public static List<Ship> userShips = new ArrayList<Ship>();

    public GameState() {
    }

    public ArrayList<Guess> addCoords(Guess guess) {
        if (!isValidGuess(guess)) {
            System.out.println("No way");
        }
        coordinatesSelected.add(guess);
        return coordinatesSelected;
    }


    public boolean isValidGuess(Guess guess) {
        for (Guess guess1 : coordinatesSelected) {
            if (guess.getX() == (guess1.getX()) && (guess.getY() == guess1.getY())) {
                return validGuess = false;
            }
        }
        return true;
    }

    public ArrayList<Guess> getCoordinatesSelected() {
        return coordinatesSelected;
    }

    public void setCoordinatesSelected(ArrayList<Guess> coordinatesSelected) {
        this.coordinatesSelected = coordinatesSelected;
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



