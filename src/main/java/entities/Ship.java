package entities;

import java.util.HashMap;

public class Ship {
    private int x;
    private int y;
    private int direction;
    private int shipCounter;
    public HashMap<Integer, String> shipsType = new HashMap<Integer, String>();
    public int size;
    private boolean completeShip;


    public Ship(int x, int y, int direction, int size) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.size = size;
    }

    public Ship(int x, int y) {
        this(x, y, 0, 0);
    }


    public Ship() {
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
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

    public int getSize() {
        return size;
    }

    public void setShipsType(HashMap<Integer, String> shipsType) {
        this.shipsType = shipsType;
    }

    public void setShipSize(int shipSize) {
        this.size = shipSize;
    }

    public boolean isSunk() {
        if (size <= 0) {
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




