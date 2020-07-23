import Controllers.PanelLocation;
import Controllers.UI;
import entities.Board;
import entities.Ship;
import service.GameState;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        Ship ship = new Ship();
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new PanelLocation();
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




