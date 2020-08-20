package controllers;

import common.Outcomes;
import entities.ComputerShip;
import entities.UserShip;
import service.GameState;
import entities.Guess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.MouseMotionAdapter;
import java.util.logging.Logger;


public class SwingBoard extends JPanel implements MouseListener {
    private static final int PANEL_LENGTH = 10;
    private static final int PANEL_WIDTH = 10;
    private static final int MAX_USER_SHIPS = 3;

    private Logger log = Logger.getLogger("Panel Location");
    private PlayerSelection playerSelection;
    private GameState state;
    private JPanel mainPanel, footerPanel;
    private JFrame frame;
    private int clicked = 0;
    private int userShips = 0;
    private boolean setUpMode = false;
    private boolean userTurn = false;
    private boolean computerTurn = false;


    private JPanel[][] panel = new JPanel[10][10];
    private JTextField tfield;


    public SwingBoard(GameState state, PlayerSelection playerSelection) {
        this.playerSelection = playerSelection;
        this.state = state;

            initPlayerShips();
            initAI();





    }


//    private void initAI() {
//        initComponents();
//
//        while (state.getComputerShips().size() < state.getNoOfComputerShips()) {
//            initComputerShips(playerSelection.generateComputerPanel());
//        }
//        printPanelCompPoints(mainPanel);
//    }

    private void initAI() {
        initComponents();

//        while (state.getComputerShips().size() < state.getNoOfComputerShips()) {
//            initComputerShips(playerSelection.generateComputerPanel());
//        }
        printPanelCompPoints(mainPanel);
    }

    ;


    private class Panel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(0, 0, 100, 100);
        }
    }

//    public class DragMouseAdapter extends MouseMotionAdapter {
//        @Override
//        public void mouseDragged(MouseEvent e) {
//            System.out.println("Mouse dragged on source: " + e.getSource());
//        }
//    }

    private void initComponents() {
        int height = 800 * 2 / 3;
        int width = 800 * 2 / 3;

        frame = new JFrame("Battleship");
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(width, height));


        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(10, 10));
        for (int y = 0; y < PANEL_LENGTH; y++) {
            for (int x = 0; x < PANEL_WIDTH; x++) {
                panel[y][x] = new Panel();
                panel[y][x].setPreferredSize(new Dimension(100, 100));
                panel[y][x].setBorder(BorderFactory.createLineBorder(Color.black));
                panel[y][x].addMouseListener(this);
                panel[y][x].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panel[y][x].setBackground(new Color(0x0F95FF));
                mainPanel.add(panel[y][x]);

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

    }


    private void initPlayerShips() {
        JOptionPane.showMessageDialog(frame,
                "Pick your ships",
                "Set up!",
                JOptionPane.PLAIN_MESSAGE);
    }


    private void computerTurn() {
        JOptionPane.showMessageDialog(frame,
                "Computer will now guess",
                "Aiming...",
                JOptionPane.PLAIN_MESSAGE);
        Guess computerGuess = initComputerGuess();
        displayEvent(state.getOutcome(computerGuess));


    }

    private void playerPrompt() {
        JOptionPane.showMessageDialog(frame,
                "Take a guess",
                "Aiming...",
                JOptionPane.PLAIN_MESSAGE);
    }


    public int detectMouseClicked(MouseEvent e) {
        clicked++;

        return clicked;

    }


//    private void initComputerShips(int randomPanel) {

//        int randomPanelX = mainPanel.getComponent(randomPanel).getX();
//        int randomPanelY = mainPanel.getComponent(randomPanel).getY();
//        ComputerShip computerShip = (ComputerShip) playerSelection.createShip(randomPanelX, randomPanelY);
//        state.addComputerShips(computerShip);
//    }

    private Guess initComputerGuess() {
        int randomPanel = playerSelection.generateComputerPanel();
        log.info("Computer's guess panel is " + panel);
        int randomPanelX = mainPanel.getComponent(randomPanel).getX();
        int randomPanelY = mainPanel.getComponent(randomPanel).getY();
        Guess computerGuess = playerSelection.createGuess(randomPanelX, randomPanelY);
        state.addComputerGuess(computerGuess);
        log.info("Computer guess: " + "X: " + computerGuess.getX() + "Y: " + computerGuess.getY());
        return computerGuess;
    }


    private void getWhichButtonGotPressed(int x, int y) {
        tfield.setText("You Picked " + x + ", " + y);
        //textbox displaying coordinates of the panel user clicked
    }

    private void displayEvent(Outcomes outcome) {
        switch (outcome) {
            case HIT:
                JOptionPane.showMessageDialog(frame,
                        "You hit the computer's ship!",
                        "HIT!",
                        JOptionPane.PLAIN_MESSAGE);
                if (state.checkGameOver()) {
                    endGame();
                    return;
                }
                break;
            case USERHIT:
                JOptionPane.showMessageDialog(frame,
                        "You're ship has been hit",
                        "HIT!",
                        JOptionPane.PLAIN_MESSAGE);
                if (state.checkGameOver()) {
                    endGame();
                    return;
                }
                break;
            case MISS:
                JOptionPane.showMessageDialog(frame,
                        "You missed!",
                        "Miss!",
                        JOptionPane.PLAIN_MESSAGE);
                break;

            case INVALID:
                JOptionPane.showMessageDialog(frame,
                        "You've already guessed this position!",
                        "Try Again!",
                        JOptionPane.PLAIN_MESSAGE);
                break;

            case GAME_OVER:
                endGame();
                break;
        }
        playerPrompt();
    }

    public void promptPlayAgain() {
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

    @Override
    public void mouseClicked(MouseEvent e) {
        if (setUpMode) {
            log.info("mouse clicked method called");
            Panel panel = (Panel) e.getSource();
            Guess currentGuess = state.getGuess();
            displayEvent(state.getOutcome(currentGuess));
            Outcomes outcome = state.getOutcome(currentGuess);

            if (outcome.equals(Outcomes.INVALID)) {
                panel.setBackground(Color.BLACK);
                log.info("Already guessed these coords");
                System.out.println("You've already guessed this place");
                return;
            }
            if (outcome.equals(Outcomes.USERHIT)) {
                panel.setBackground(Color.BLACK);
                log.info("User ship Hit");
                System.out.println("You're ship has been hit!");
                return;
            }
            if (outcome.equals(Outcomes.HIT)) {
                panel.setBackground(Color.RED);
                log.info("Computer Ship hit");
                return;
            }
            if (outcome.equals(Outcomes.MISS)) {
                panel.setBackground(Color.BLUE);
                log.info("No ships hit");
                return;
            }
        } else {


        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        if (setUpMode) { //if false

            JPanel panel = (JPanel) e.getSource();
            Guess guess = playerSelection.createGuess(panel.getX(), panel.getY());
            state.addUserGuess(guess);
            state.setGuess(guess);
            getWhichButtonGotPressed(panel.getX(), panel.getY());
            repaint();
            userTurn = true;


        } else {
            if (userShips < MAX_USER_SHIPS) {
                JPanel panel1 = (JPanel) e.getSource();
                UserShip userShip = (UserShip) playerSelection.createUserShip(panel1.getX(), panel1.getY());
                state.addPlayerShips(userShip);
                userShips++;
            } else {
                setUpMode = true;
            }
        }
    }


    public void mouseReleased(MouseEvent e) {


    }///need to stop

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {

    }


    //debug
    private void printPanelCompPoints(JPanel mainPanel) {

        log.info("Panel Total : " + mainPanel.getComponentCount());
    }


}
