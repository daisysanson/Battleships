import controllers.SwingBoard;
import controllers.PlayerSelection;

import entities.ComputerShip;
import service.GameState;

import javax.swing.SwingUtilities;

public class Main {


    public static void main(String[] args) {
//        ComputerShip computerShip = new ComputerShip();
//        computerShip.randomNumberGenerator();

        final PlayerSelection playerSelection = new PlayerSelection();
        final GameState gameState = new GameState();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingBoard(gameState, playerSelection);
            }
        });
    }
}

