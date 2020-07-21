import Controllers.SwingBoard;
import Controllers.UI;
import entities.Board;
import entities.Ship;
import service.GameState;

public class Main {

    public static void main(String[] args) {
        Ship ship = new Ship();
        SwingBoard swingBoard = new SwingBoard();
        UI ui = new UI();;
        ui.getUserCoord(ship);
        ui.getUserSize(ship);
        ui.getUserDirection(ship);
        ui.getShip(ship);









        }



    }



