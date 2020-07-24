package service;

import entities.Ship;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameState {

    private boolean sizeEntered;
    private boolean validGuess = true;
    private boolean coordsEntered;
    private boolean directionEntered;
    private boolean completeShip;
    private Ship ship;
    private HashMap<Integer, Integer> coordinatesSelected = new HashMap<Integer, Integer>();
    public static List<Ship> userShips = new ArrayList<Ship>();


    public HashMap<Integer, Integer> coordsSelected (int x, int y) {
        coordinatesSelected.put(x,y);
        return coordinatesSelected;
    }

    public boolean isValidGuess(int x, int y){
        if(coordinatesSelected.containsKey(x) && coordinatesSelected.containsValue(y)){ //putbeforethecehck
            System.out.println("Nah mate");
            return !validGuess;
        } else{
            return validGuess;
        }
    }



    public int shipDirection(int direction, Ship ship) {
        return direction;
    }

    public int shipSize(int size, Ship ship) {
        return size;
    }

    public boolean isCompleteShip(Ship ship) {
        if ((directionEntered == true) && (coordsEntered == true) && (sizeEntered == true)) {
            userShips.add(ship);
            return completeShip = true;
        } else {
            return false;
        }
    }
}



//    public boolean isValidDirection(Ship ship, Board board) {
//        if ((x == -1) || (y == -1)) {
//            System.out.print("This will cause the ship to appear out of the grid! Please pick another direction");
//            removePlayerCoordinates(userSize, playerDirection, x, y);
//            return false;
//        } else {
//            shipCounter++;
//            return true;
//        }



