package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ship {
    private int xCoord;
    private int yCoord;
    private int direction;
    private int shipCounter;
    public HashMap<Integer, String> shipsType = new HashMap<Integer, String>();
    public int shipSize;
    private boolean completeShip;


    public Ship(int xCoord, int yCoord, int direction, int shipSize) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.direction = direction;
        this.shipSize = shipSize;
    }


    public Ship(){}



    public int getXCoord() {
        return xCoord;
    }

    public void setXCoord(int coordX) {
        this.xCoord = coordX;
    }

    public int getYCoord() {
        return yCoord;
    }

    public void setYCoord(int coordY) {
        this.yCoord = coordY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public HashMap<Integer, String> getShipsType() {
        return shipsType;
    }

    private void setShipType() {
        shipsType.put(5, "Carrier");
        shipsType.put(4, "Battleship");
        shipsType.put(3, "Destroyer");
        shipsType.put(2, "Submarine");
        shipsType.put(1, "Patrol Boat");
        this.shipsType = shipsType;
    }


    public boolean isCompleteShip() {
        return completeShip;
    }

    public void setCompleteShip(boolean completeShip) {
        this.completeShip = completeShip;
    }

    public int getShipSize() {
        return shipSize;
    }

    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public void setShipsType(HashMap<Integer, String> shipsType) {
        this.shipsType = shipsType;
    }

    public void setShipSize(int shipSize) {
        this.shipSize = shipSize;
    }

    public boolean isSunk() {
        if (shipSize <= 0) {
            return true;
        } else
            return false;
    }

    public int getShipCounter() {
        return shipCounter;
    }

    public void setShipCounter(int shipCounter) {
        this.shipCounter = shipCounter;
    }
}




