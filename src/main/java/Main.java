import controllers.PanelLocation;
import controllers.PlayerSelection;

import service.GameState;

import javax.swing.*;

public class Main {


    public static void main(String[] args) {
        final PlayerSelection playerSelection = new PlayerSelection();
        final GameState gameState = new GameState();
        SwingUtilities.invokeLater(new Runnable()

        {
            public void run()
            {
                new PanelLocation(gameState, playerSelection);
            }
        });
    }
}




//
//
//
//        UI ui = new UI();;
//        ui.getUserCoord(ship);
//        ui.getUserSize(ship);
//        ui.getUserDirection(ship);
//        ui.getShip(ship);
//
//




