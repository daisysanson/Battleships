package entities;
import java.util.Random;



public class ComputerShip extends Ship  {

    private Random rand = new Random();
    private final long seed = 42;




    public int randomCoordGenerator(){
        rand.setSeed(seed);
        int coord = Math.abs(rand.nextInt() % (10 - 1));
        return coord;

    }

    public int randomNumberGenerator(){
        rand.setSeed(seed);
        int random = Math.abs(rand.nextInt() % (1 - 4));
        return random;

    }



    }


