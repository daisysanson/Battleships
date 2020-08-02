package entities;
import java.util.Random;



public class ComputerShip extends Ship  {
    private Random rand = new Random();
    private final long seed = 42;


    public ComputerShip(int x, int y, int direction, int size) {
        super(x, y, direction, size);
    }

    public ComputerShip(int x, int y){
        // need to set x and y
        super(x, y);
    };

    public ComputerShip(){}

    public  int randomPanelGenerator() {
       return rand.nextInt(100 - 0 + 1);
    }

    public int randomNumberGenerator(){
        rand.setSeed(seed);
        int random = Math.abs(rand.nextInt() % (1 - 4));
        return random;

    }



    }


