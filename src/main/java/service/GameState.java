package service;

import entities.Ship;

import java.util.ArrayList;
import java.util.List;

public class GameState {

    private boolean sizeEntered;
    private boolean coordsEntered;
    private boolean directionEntered;
    private boolean completeShip;
    private Ship ship;
    public static List<Integer> shipCoords = new ArrayList<Integer>();
    public static List<Ship> userShips = new ArrayList<Ship>();


    public List<Integer> addCoords(int xCoord, int yCoord, Ship ship) {
        shipCoords.add(yCoord);
        shipCoords.add(xCoord);
        coordsEntered = true;
        return shipCoords;
    }


    public int shipDirection(int direction, Ship ship) {
        ship.setDirection(direction);
        directionEntered = true;
        return direction;
    }

    public int shipSize(int size, Ship ship) {
        ship.setShipSize(size);
        sizeEntered = true;
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



