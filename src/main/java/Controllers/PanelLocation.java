package Controllers;
import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelLocation implements MouseListener {//inheriting JFrame
    private JPanel mainPanel, footerPanel;
    private JFrame frame;
    private JPanel[] panel = new JPanel[9];
    ArrayList<Integer> boxCoords = new ArrayList<Integer>();
    private Integer box;
    private JTextField tfield;

    public PanelLocation() {


        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 3));

        frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents(frame);
        printPanelCompPoints(mainPanel); //will return the coords as the frame has been packed in initComponents

        frame.add(mainPanel);


    }


        private void initComponents(JFrame frame) {
        for (int i = 0; i < 9; i++)
        {
            panel[i] = new JPanel();
            panel[i].addMouseListener(this);
            panel[i].setBorder(BorderFactory.createLineBorder(Color.BLUE));
            mainPanel.add(panel[i]);
        }



        frame.getContentPane().add(mainPanel);

        footerPanel = new JPanel();


        tfield = new JTextField(10);
        footerPanel.add(tfield);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.PAGE_END);


        frame.pack();
        frame.setVisible(true);
    }


    private void printPanelCompPoints(JPanel mainPanel) {
//        System.out.println(mainPanel.getComponentCount());

        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
            boxCoords.add(i);
        }
//        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
//            System.out.println(mainPanel.getComponent(i).getX() + ", " + mainPanel.getComponent(i).getY());
//        }
//        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
//            System.out.println(mainPanel.getComponent(i).getX() + ", " + mainPanel.getComponent(i).getY());
//        }
        for (int i = 1; i < boxCoords.size(); i++) {
            System.out.println(i);

        }


    }


    private void getWhichButtonGotPressed(int x, int y) {
        if ((x == panel[0].getX()) && (y == panel[0].getY()))
        {
            panel[0].setBackground(Color.MAGENTA);

            tfield.setText("You Clicked Panel : " + boxCoords.get(1) +   "which is at" + panel[0].getX() + ", " +  panel[0].getY());
        }
        if ((x == panel[1].getX()) && (y == panel[1].getY()))
        {
            panel[1].setBackground(Color.RED);
            tfield.setText("You Clicked Panel : " + boxCoords.get(2) +  " which is at "+ panel[1].getX() + ", " +  panel[1].getY());
        }
        if ((x == panel[2].getX()) && (y == panel[2].getY()))
        {
            panel[2].setBackground(Color.RED);
            tfield.setText("You Clicked Panel : "  + "which is at" +  panel[2].getX() + ", " +  panel[2].getY());
        }

    }

    public void mouseClicked(MouseEvent e) {
        JPanel panel = (JPanel) e.getSource();

        getWhichButtonGotPressed(panel.getX(), panel.getY());
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}






//    public void displayBoard() {
//            EventQueue.invokeLater(new Runnable() {
//                public void run() {
//                        UIManager.setInstalledLookAndFeels(UIManager.getInstalledLookAndFeels());
//
//
//                    JFrame frame = new JFrame("Battleships");
//                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//                    frame.setLayout(new BorderLayout());
//                    frame.add(new TestPane());
//                    frame.pack();
//                    frame.setLocationRelativeTo(null);
//                    frame.setVisible(true);
//                }
//            });
//        }
//
//        public class TestPane extends JPanel {
//
//            public TestPane() {
//                setLayout(new GridBagLayout());
//
//                GridBagConstraints gbc = new GridBagConstraints();
//                for (int row = 0; row < 10; row++) {
//                    for (int col = 0; col < 10; col++) {
//                        gbc.gridx = col;
//                        gbc.gridy = row;
//
//                        CellPane cellPane = new CellPane();
//
//                        Border border = null;
//                        if (row < 9) {
//                            if (col < 9) {
//                                border = new MatteBorder(1, 1, 0, 0, Color.GRAY);
//                            } else {
//                                border = new MatteBorder(1, 1, 0, 1, Color.GRAY);
//                            }
//                        } else {
//                            if (col < 9) {
//                                border = new MatteBorder(1, 1, 1, 0, Color.GRAY);
//                            } else {
//                                border = new MatteBorder(1, 1, 1, 1, Color.GRAY);
//                            }
//                        }
//                        cellPane.setBorder(border);
//                        add(cellPane, gbc);
//                    }
//                }
//            }
//        }
//
//        public class CellPane extends JPanel {
//
//            private Color defaultBackground;
//
//
//            public CellPane() {
//                addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseEntered(MouseEvent e) {
//                        defaultBackground = getBackground();
//                        setBackground(Color.BLUE);
//                    }
//
//                    @Override
//                    public void mouseExited(MouseEvent e) {
//                        setBackground(defaultBackground);
//                    }
//                });
//            }
//
//            @Override
//            public Dimension getPreferredSize() {
//                return new Dimension(50, 50);
//            }
//        }}}

//
//        public void actionPerformed(ActionEvent e, int playerGuesses, int x, int y ) {
//
//            if (board[x][y] == 0) {
//                cells[x][y].setBackground();
//                board[x][y] = 0;
//            }
//
//            //if the guess hit a computer
//            if (board[x][y] == 1) {
//                button[x][y].setBackground(Color.green);
//                board[x][y] = -1;
//            }
//
//            //if the guess hit a user
//            if (board[x][y] == 3) {
//                button[x][y].setBackground(Color.blue);
//                board[x][y] = -1;
//            }
//
//            playerGuesses++;
//        }


//        public void populateBoard(int shipSize, int shipDirection, int x, int y) {
//            /** instance used for randomly placing the ships*/
//
//            /** used to tell the loop whether to keep trying to place the ship or not*/
//            boolean cont = false;
//
//
//            /** integer representing whethere a specific square is empty or not*/
//            boolean isValid = true;
//
//
//            while (!cont) {
//                isValid = true;
//
//                if (shipDirection == 1) {
//                    if (y + shipSize <= 7) {
//                        for (int i = y; i < y + shipSize; i++) {
//                            //square is already occupied
//                            if (board[x][i] != 0) {
//                                isValid = false;
//                            }
//                        }
//
//                        //ship can be placed here
//                        if (isValid) {
//                            for (int i = y; i < y + shipSize; i++) {
//                                board[x][i] = shipSize;
//                            }
//                            return;
//                        }
//                    }
//                }
//
//                //placed to the left
//                {
//                    if (shipDirection == 4) {
//                        for (int i = y; i > y - shipSize; i--) {
//                            //square is already occupied
//                            if (board[x][i] != 0) {
//                                isValid = false;
//                            }
//                        }
//
//                        //ship can be placed here
//                        if (isValid) {
//                            for (int i = y; i > y - shipSize; i--) {
//
//                                board[x][i] = shipSize;
//
//                            }
//                            return;
//                        }
//                    }
//                }
//
//            }
//            //placed upward
//            if (shipDirection == 3) {
//                //both points are one the board
//                if (x - shipSize >= 0) {
//                    for (int i = x; i > x - shipSize; i--) {
//                        //square is already occupied
//                        if (board[i][y] != 0) {
//                            isValid = false;
//                        }
//
//                    }
//
//                    //ship can be placed here
//                    if (isValid) {
//                        for (int i = x; i > x - shipSize; i--) {
//                            board[i][y] = shipSize;
//
//                        }
//                        return;
//                    }
//                }
//            }
//
//            //placed downward
//            {
//                if (shipDirection == 1) {
//                    for (int i = x; i < x + shipSize; i++) {
//                        //square is already occupied
//                        if (board[i][y] != 0) {
//                            isValid = false;
//                        }
//
//                    }
//
//                    //ship can be placed here
//                    if (isValid) {
//                        for (int i = x; i < x + shipSize; i++) {
//
//                            board[i][y] = shipSize;
//                        }
//                        return;
//                    }
//                }
//            }
//        }
//    }
//
//
