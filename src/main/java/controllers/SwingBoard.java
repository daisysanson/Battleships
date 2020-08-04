package controllers;

import entities.ComputerShip;
import service.GameState;
import entities.Guess;

import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingBoard extends JPanel implements MouseListener {//inheriting JFrame
    PlayerSelection playerSelection;
    GameState state;
    static Logger log = Logger.getLogger("Panel Location");


    private ArrayList<Point> computerPositions = new ArrayList<Point>();
    private JPanel mainPanel, footerPanel;
    private JFrame frame;
    private final int LENGTH = 10;
    private final int WIDTH = 10;
    private JPanel[][] panel = new JPanel[10][10];
    private JTextField tfield;


    public SwingBoard(GameState state, PlayerSelection playerSelection) {
        this.playerSelection = playerSelection;
        this.state = state;

        initNewGame();


    }

    public void initNewGame() {
        log.info("New Game Generated");
        initComponents();
        while (state.getComputerShips().size() < state.getNoOfComputerShips()) {
            initComputerShips(playerSelection.createComputerCoords());
        }
        printPanelCompPoints(mainPanel);

    }


    class Panel extends JPanel {
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(0, 0, 100, 100);
        }
    }


    private void initComponents() {
        int height = 800 * 2 / 3;
        int width = 800 * 2 / 3;

        frame = new JFrame("Battleship");
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(10, 10));
        for (int y = 0; y < LENGTH; y++) {
            for (int x = 0; x < WIDTH; x++) {
                panel[y][x] = new Panel();
                panel[y][x].setPreferredSize(new Dimension(100, 100));
                panel[y][x].setBorder(BorderFactory.createLineBorder(Color.black));
                panel[y][x].addMouseListener(this);
                panel[y][x].setBackground(new Color(0x0F95FF));
                mainPanel.add(panel[y][x]);
            }
        }


        footerPanel = new JPanel();
        tfield = new JTextField(30);
        tfield.setEditable(false);
        footerPanel.add(tfield);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(footerPanel, BorderLayout.PAGE_END);


        frame.pack();
        frame.setVisible(true);
    }


    private void printPanelCompPoints(JPanel mainPanel) {

        log.info("Panel Total : " + mainPanel.getComponentCount());
    }


    private void initComputerShips(int randomPanel) {
        int randomPanelX = mainPanel.getComponent(randomPanel).getX();
        int randomPanelY = mainPanel.getComponent(randomPanel).getY();
        ComputerShip computerShip = playerSelection.createShip(randomPanelX, randomPanelY);
        state.addComputerShips(computerShip);
        System.out.println("X is: " + randomPanelX + "Y is: " + randomPanelY);


    }


    private void getWhichButtonGotPressed(int x, int y) {
        tfield.setText("You Picked " + x + ", " + y);
        //textbox displaying coordinates of the panel user clicked
    }

    private void displayEvent(int outcome) {
        if (outcome == 0) {
            JOptionPane.showMessageDialog(frame,
                    "You hit the computer's ship!",
                    "HIT!",
                    JOptionPane.PLAIN_MESSAGE);
            if (state.checkGameOver()) {
                endGame();
            }
        }
        if (outcome == 1) {
            JOptionPane.showMessageDialog(frame,
                    "You missed!",
                    "Miss!",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (outcome == 2) {
            JOptionPane.showMessageDialog(frame,
                    "You've already guessed this position!",
                    "Try Again!",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (outcome == 3) {
            endGame();
        }

    }

    public void promptPlayAgain() {
        int dialogButton = JOptionPane.YES_NO_OPTION;
        if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            resetGame();
        } else {
            System.exit(0);
        }
    }


    public void resetGame() {

        frame.setVisible(false);
        frame.removeAll();
        GameState newGame = state.resetGuesses();
        SwingBoard location = new SwingBoard(newGame, playerSelection);
        revalidate();
        repaint();
    }

    public void endGame() {
        tfield.setText(state.calculateWinner());
        JOptionPane.showMessageDialog(frame,
                "Game Over",
                "Game Over!",
                JOptionPane.PLAIN_MESSAGE);
        promptPlayAgain();

    }


    public void mouseClicked(MouseEvent e) {

        Panel panel = (Panel) e.getSource();
        displayEvent(state.getOutcome());
        while (true) {
            if (!state.isValidGuess()) {
                panel.setBackground(Color.RED);
                log.info("Already guessed these coords");
                System.out.println("You've already guessed this place");
                break;
            }
            if (state.isShipHit()) {
                panel.setBackground(Color.BLACK);
                log.info("Computer ship hit");
                break;
            }
            if (state.isValidGuess() && !state.isShipHit()) {
                panel.setBackground(Color.BLUE);
                log.info("No ships hit");
                break;
            }
        }
    }


    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel) e.getSource();
        Guess guess = playerSelection.createGuess(panel.getX(), panel.getY());
        state.addCoords(guess);

        getWhichButtonGotPressed(panel.getX(), panel.getY());
        repaint();

    }


    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }


}
