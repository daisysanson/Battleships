package controllers;

import common.Outcomes;
import entities.ComputerShip;
import service.GameState;
import entities.Guess;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.util.logging.Logger;


public class SwingBoard extends JPanel implements MouseListener {
    private static final int PANEL_LENGTH = 10;
    private static final int PANEL_WIDTH = 10;

    private Logger log = Logger.getLogger("Panel Location");
    private PlayerSelection playerSelection;
    private GameState state;
    private JPanel mainPanel, footerPanel;
    private JFrame frame;

    private JPanel[][] panel = new JPanel[10][10];
    private JTextField tfield;


    public SwingBoard(GameState state, PlayerSelection playerSelection) {
        this.playerSelection = playerSelection;
        this.state = state;

        initNewGame();


    }

    private void initNewGame() {
        log.info("------------------------- New Game Generated---------------------------");
        initComponents();
        while (state.getComputerShips().size() < state.getNoOfComputerShips()) {
            initComputerShips(playerSelection.generateComputerPanel());
        }
        printPanelCompPoints(mainPanel);

    }


    private class Panel extends JPanel {
        @Override
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


        mainPanel = new JPanel();

        mainPanel.setLayout(new GridLayout(10, 10));
        for (int y = 0; y < PANEL_LENGTH; y++) {
            for (int x = 0; x < PANEL_WIDTH; x++) {
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


    private void initComputerShips(int randomPanel){

        int randomPanelX = mainPanel.getComponent(randomPanel).getX();
        int randomPanelY = mainPanel.getComponent(randomPanel).getY();
        ComputerShip computerShip = playerSelection.createShip(randomPanelX, randomPanelY);
        state.addComputerShips(computerShip);

        int seqPanel = playerSelection.generateSideShip(randomPanel);
        int seqPanelX = mainPanel.getComponent(seqPanel).getX();
        int seqPanelY = mainPanel.getComponent(seqPanel).getY();
        ComputerShip computerShip1 = playerSelection.createShip(seqPanelX, seqPanelY);
        state.addComputerShips(computerShip1);

        System.out.println("X is: " + randomPanelX + "Y is: " + randomPanelY);


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
        Panel panel = (Panel) e.getSource();
        Guess currentGuess = state.getGuess();
        displayEvent(state.getOutcome(currentGuess));
        Outcomes outcome = state.getOutcome(currentGuess);

        if (outcome.equals(Outcomes.INVALID)) {
            panel.setBackground(Color.RED);
            log.info("Already guessed these coords");
            System.out.println("You've already guessed this place");
            return;
        }
        if (outcome.equals(Outcomes.HIT)) {
            panel.setBackground(Color.BLACK);
            log.info("Computer ship hit");
            return;
        }
        if (outcome.equals(Outcomes.MISS)) {
            panel.setBackground(Color.BLUE);
            log.info("No ships hit");
            return;
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        JPanel panel = (JPanel) e.getSource();
        Guess guess = playerSelection.createGuess(panel.getX(), panel.getY());
        state.addCoords(guess);
        state.setGuess(guess);
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
