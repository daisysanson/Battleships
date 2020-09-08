package entities;


public class Ship {
    private int x;
    private int y;
    private boolean isHit;


    public Ship(int x, int y) {
        this.x = x;
        this.y = y;
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


    public boolean isHit() {
        return true;
    }



}
//
//    public int getShipCounter() {
//        return shipCounter;
//    }
//
//    public void setShipCounter(int shipCounter) {
//        this.shipCounter = shipCounter;
//    }




