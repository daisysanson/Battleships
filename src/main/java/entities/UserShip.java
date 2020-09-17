package entities;

public class UserShip extends Ship {
    private int size;
    private int[] shipPairs;

    public UserShip(int x, int y, int size) {
        super(x, y);
        this.size = size;
    }

    public UserShip(int x, int y) {
        super(x, y);
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
