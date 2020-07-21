package entities;

public class Board {

    private int[][] board = new int[10][10];
    private final int BOARD_HEIGHT = 10;
    private final int BOARD_WIDTH = 10;
    private int x;
    private int y;
    public int ships = 6;


    public Board(int[][] board) {
        this.board = board;
    }

    public Board() {

    }


    public void createBoard() {
        System.out.println("\n\t0 \t1 \t2 \t3 \t4 \t5 \t6 \t7 \t8 \t9");
        System.out.println(); //prints top of grid

        for (int BOARD_WIDTH = 0; BOARD_WIDTH < 10; BOARD_WIDTH++) {
            System.out.print((BOARD_WIDTH + 0) + ""); //printing spaces in between the grid
            for (int BOARD_HEIGHT = 0; BOARD_HEIGHT < 10; BOARD_HEIGHT++) {
                if (board[BOARD_WIDTH][BOARD_HEIGHT] == -1) {
                    System.out.print("\t" + "~"); //setting up a miss which a tilda will appear if value is -1
                } else if (board[BOARD_WIDTH][BOARD_HEIGHT] == 3) {
                    System.out.print("\t" + "s");
                } else if (board[BOARD_WIDTH][BOARD_HEIGHT] == 2) { //a hit is worth 2, prints x
                    System.out.print("\t" + "X");
                } else if (board[BOARD_WIDTH][BOARD_HEIGHT] == 1) {
                    System.out.print("\t" + "c");
                } else {
                    System.out.print("\t" + "*");
                }
            }
            System.out.println();
        }
    }
}




