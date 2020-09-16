import controllers.SwingBoard;
import controllers.PlayerSelection;

import service.GameState;

import javax.swing.SwingUtilities;

public class Main {


    public static void main(String[] args) {

        final PlayerSelection playerSelection = new PlayerSelection();
        final GameState gameState = new GameState();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingBoard(gameState, playerSelection);
            }
        });
    }
}

