package Controllers;

import entities.Board;
import entities.Ship;
import service.GameState;

import java.util.List;
import java.util.Scanner;

public class UI {
   GameState gameState = new GameState();
    Scanner input = new Scanner(System.in);



   public void getUserCoord(Ship ship) {
       System.out.print("Please enter your X coord: ");
       int xCoord = input.nextInt();
       System.out.print("Please enter your Y coord: ");
       int yCoord = input.nextInt();


       gameState.addCoords(xCoord, yCoord, ship);
   }

   public int getUserDirection(Ship ship){
       System.out.println("\nPlease select direction you wish to place your ship - \n1: down \n2: right \n3: up \n4: left");
       int shipDirection = input.nextInt();

       return gameState.shipDirection(shipDirection, ship);
   }

    public int getUserSize(Ship ship) {
        System.out.println("Please select a size, \n1: Patrol Boat \n2: Submarine \n3: Destroyer \n4: Battleship \n5: Carrier");
        int userSize = input.nextInt();
        switch (userSize) {
            case 1:
                System.out.print("You selected a " + ship.shipsType.get(1));
                break;
            case 2:
                System.out.print("You selected a " + ship.shipsType.get(2));
                break;
            case 3:
                System.out.print("You selected a " + ship.shipsType.get(3));
                break;
            case 4:
                System.out.print("You selected a " + ship.shipsType.get(4));
                break;
            case 5:
                System.out.print("You selected a " + ship.shipsType.get(5));
                break;
            default:
                System.out.print("This is not a valid selection");

        }
        return gameState.shipSize(userSize, ship);
    }

    public void getShip(Ship ship){
       System.out.print(ship.getXCoord());
       System.out.print(ship.getyCoord());
       System.out.print(ship.getDirection());
       System.out.print(ship.getShipSize());

    }


    public void displayBoard(Board board){
       board.createBoard();
    }



//    public int placeShip(int x, int y, int coordX, int CoordY){
//        direction = computer.getShipDirection();
//        if (compIsValidPos()) {
//            board[compX][compY] = 1;
//            for (int j = 0; j < size; j++) {
//                switch (compDirection) {
//                    case 1:
//                        //vertical placement down
//                        board[compX][compY] = 1;
//                        compX += 1;
//                        break;
//                    case 2:
//                        board[compX][compY] = 1;
//                        compY += 1;
//                        break;
//                    case 3:
//                        board[compX][compY] = 1;
//                        compX -= 1;
//                        break;
//                    case 4:
//                        board[compX][compY] = 1;
//                        compY -= 1;
//                        break;
//                }
//            }



    }





