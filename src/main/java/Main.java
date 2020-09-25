import controllers.SwingBoard;
import controllers.PlayerSelection;

import service.GameState;

import javax.swing.SwingUtilities;


/**
 * The 'Battleships' program is a version of the widely known game 'battleships',playable between a player(user) and
 * the computer.
 * @author  Daisy Sanson
 * @version 1.0
 */

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

