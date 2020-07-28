package Controllers;
import javafx.scene.layout.Pane;
import service.GameState;
import entities.Guess;
import sun.tools.jps.Jps;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PanelLocation extends JPanel implements MouseListener {//inheriting JFrame
    GameState state;
    PlayerSelection playerSelection;
    private boolean mustDraw = false;
    private ArrayList<Point>  points = new ArrayList<Point>();
    private JPanel mainPanel, footerPanel;
    private JFrame frame;
    private int LENGTH = 10;
    private int WIDTH = 10;
    private int clickCount;
    private JPanel[][] panel = new JPanel[10][10];
    private ArrayList<Integer> boxCoords = new ArrayList<Integer>();
    private Integer box;
    private JTextField tfield;


    public PanelLocation(GameState state, PlayerSelection playerSelection) {
        this.state = state;
        this.playerSelection = playerSelection;


        initComponents();
        printPanelCompPoints(mainPanel); //will return the coords as the frame has been packed in initComponents

    }


    class Panel extends JPanel {
        public void paintComponent(Graphics g) {
            g.drawRect(0, 0, 100, 100);
            }
        }

        private void initComponents() {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int height = 800 * 2 / 3;
            int width = 800 * 2 / 3;

            frame = new JFrame("Battleship");
            frame.setResizable(false);
            frame.setPreferredSize(new Dimension(width, height));

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


            mainPanel = new JPanel();
            JLabel label = new JLabel("Click count: " + clickCount);

            mainPanel.setLayout(new GridLayout(10, 10));
            for (int y = 0; y < LENGTH; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    panel[y][x] = new Panel();
                    panel[y][x].setPreferredSize(new Dimension(100, 100));
                    panel[y][x].setBorder(BorderFactory.createLineBorder(Color.black));
                    panel[y][x].add(label);
                    panel[y][x].addMouseListener(this);
                    panel[y][x].setBackground(new Color(0xFFFF00));
                    mainPanel.add(panel[y][x]);
                }
            }


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
            System.out.println("Panel Total : " + mainPanel.getComponentCount());

            for (int i = 0; i < mainPanel.getComponentCount(); i++) {
                boxCoords.add(i);
            }
////        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
////            System.out.println(mainPanel.getComponent(i).getX() + ", " + mainPanel.getComponent(i).getY());
////        }
////        for (int i = 0; i < mainPanel.getComponentCount(); i++) {
////            System.out.println(mainPanel.getComponent(i).getX() + ", " + mainPanel.getComponent(i).getY());
////        }
//        for (int i = 1; i < boxCoords.size(); i++) {
//            System.out.println(i);

        }


        private void getWhichButtonGotPressed(int x, int y) {
            tfield.setText("You Picked " + x + ", " + y);
        }


        public void getColourOfPanel(JPanel panel, MouseEvent e) {
            Color c = panel.getBackground();
            System.out.println("color: " + c.toString());
        }


//    public void addLabelToPanel(JPanel panel, MouseEvent e) {
//
//        if (panel instanceof JPanel) {
//            JPanel panelPressed = (JPanel) panel;
//            panelPressed.setBackground(Color.blue);
//        }
//
//    }


        public void repaintCoords() {
            ArrayList<Guess> coords = state.getCoordinatesSelected();
            for (Guess guess : coords) {
                Point point = new Point(guess.getX(), guess.getY());
                points.add(point);
            }

        }
        public void mouseClicked(MouseEvent e) {
            JPanel panel = (JPanel) e.getSource();
            Guess guess = playerSelection.createGuess(panel.getX(), panel.getY());
            ArrayList addCoords = state.addCoords(guess);
            getWhichButtonGotPressed(panel.getX(), panel.getY());
            getColourOfPanel(panel, e);
            repaintCoords();


        }

        public void mousePressed(MouseEvent e) {
            Panel panel = (Panel) e.getSource();
            panel.setBackground(Color.red);
           /* if(points.contains(panel.getX()), (panel.getY());{
                panel.setBackground(Color.pink);*/
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
