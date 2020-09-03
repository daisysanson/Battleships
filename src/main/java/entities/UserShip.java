package entities;

        import sun.dc.pr.PRError;

        import javax.swing.*;

public class UserShip extends Ship {
    private int size;


    public UserShip(int x, int y, int size){}

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
