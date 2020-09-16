package controllers;

import common.Outcomes;
import common.Winner;
import entities.ComputerShip;
import entities.UserShip;
import service.GameState;
import entities.Guess;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Component;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Iterator;


public class SwingBoard extends JPanel implements MouseListener {
    private static final int PANEL_LENGTH = 10;
    private static final int PANEL_WIDTH = 10;
    private static final int MAX_USER_SHIPS = 6;

    private Logger log = Logger.getLogger("Panel Location");
    private PlayerSelection playerSelection;
    private GameState state;
    private ArrayList list;
    private JPanel mainPanel, footerPanel;
    private JFrame frame;
    private ArrayList<Panel> listOfPanels = new ArrayList<Panel>();
    private int computerPanel;
    private int computerPanelClicked = 0;
    private int shipCreator = 0;
    private ArrayList<Integer> intValueOfPanel = new ArrayList<>();
    private int userShips = 0;
    private boolean setUpMode = false;
    private boolean userTurn = false;
    private boolean computerTurn = false;


    private JPanel[][] panel = new JPanel[10][10];
    private JTextField tfield;


    public SwingBoard(GameState state, PlayerSelection playerSelection) {
        this.playerSelection = playerSelection;
        this.state = state;
        this.list = playerSelection.initList();

        log.info("--------------------New Game!----------------------");
        setUpMode = false;
        initPlayerShips();
        initComponents();


    }

    private void initAI() {
        while (state.getComputerShips().size() < state.getNoOfComputerShips()) {
            initComputerShips(playerSelection.generateComputerPanel(list));
        }
    }

    ;


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
                panel[y][x].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                panel[y][x].setBackground(new Color(0x0F95FF));
                listOfPanels.add((Panel) panel[y][x]);
                mainPanel.add(panel[y][x]);

            }


            footerPanel = new JPanel();
            tfield = new JTextField(50);
            tfield.setHorizontalAlignment(SwingConstants.CENTER);
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
                "Pick your ships. Max two squares in size",
                "Set up!",
                JOptionPane.PLAIN_MESSAGE);
    }


    private void playerPrompt() {
        JOptionPane.showMessageDialog(frame,
                "Take a guess",
                "Aiming...",
                JOptionPane.PLAIN_MESSAGE);

    }

    private void playerInvalidShipPlacement() {
        JOptionPane.showMessageDialog(frame,
                "You've already placed a ship here! Pick another place",
                "Steady on!",
                JOptionPane.WARNING_MESSAGE);
    }

    private void playerReselectShips() {
        JOptionPane.showMessageDialog(frame,
                "You can't have ships bigger than 2 squares; please reselect!",
                "Nope!",
                JOptionPane.WARNING_MESSAGE);
    }


    public void checkTurn() {
        if (!computerTurn) {
            playerPrompt();
        }
    }


    private void initComputerShips(int randomPanel) {
        int randomPanelX = mainPanel.getComponent(randomPanel).getX();
        int randomPanelY = mainPanel.getComponent(randomPanel).getY();
        ComputerShip computerShip = (ComputerShip) playerSelection.createShip(randomPanelX, randomPanelY);
        log.info("Computer Ship panel is: " + randomPanel);
        state.addComputerShips(computerShip);

    }

    private Guess initComputerGuess(ArrayList list) {
        int randomPanel = playerSelection.generateComputerPanel(list);
        setComputerPanel(randomPanel);
        log.info("Computer's guess panel is " + randomPanel);
        int randomPanelX = mainPanel.getComponent(randomPanel).getX();
        int randomPanelY = mainPanel.getComponent(randomPanel).getY();
        Guess computerGuess = playerSelection.createGuess(randomPanelX, randomPanelY);
        state.addComputerGuess(computerGuess);
        log.info("Computer guess: " + "X: " + computerGuess.getX() + "Y: " + computerGuess.getY());

        return computerGuess;
    }


    private int setComputerPanel(int computerPanel) {
        return this.computerPanel = computerPanel;
    }

    private void getWhichButtonGotPressed(int x, int y) {
        tfield.setText("You Picked " + x + ", " + y);

    }


    private void computerTurn() {
        JOptionPane.showMessageDialog(frame,
                "Computer will now guess",
                "Aiming...",
                JOptionPane.PLAIN_MESSAGE);
        Guess computerGuess = initComputerGuess(list);
        state.setGuess(computerGuess);


        Outcomes outcome = state.getOutcome(computerGuess);
        displayEvent(state.getOutcome(computerGuess));
        if (setUpMode) {
            computerTurn = false;
            computerClickOutcome(outcome, computerPanel);
            checkTurn();
        } else {
            return;
        }
    }


    public void setComputerPanelClicked(int computerPanelClicked) {
        this.computerPanelClicked = computerPanelClicked;
    }


    private void computerClickOutcome(Outcomes outcome, int computerPanel) {
        Component computerPanel1 = mainPanel.getComponent(computerPanel);

        if (outcome.equals(Outcomes.USERHIT)) {
            userShips--;
            computerPanel1.setBackground(Color.BLACK);
            state.checkProximity(computerPanel);
            Component pairPanel = mainPanel.getComponent(state.getPair());
            if (state.getPair() == 0) {
                return;
            }
            if (pairPanel.getBackground() == Color.pink) { // if there's a panel in proximity to computerPanel, and it has alrady been git, the 'whole' ship appears black as sunk!
                pairPanel.setBackground(Color.BLACK);
                log.info("User ship sunk");
                return;
            }
        }
        if (outcome.equals(Outcomes.MISS)) {
            computerPanel1.setBackground(Color.BLUE);
            log.info("No ships hit");
            return;

        }
        if (outcome.equals(Outcomes.PARTIAL_HIT)) {
            computerPanel1.setBackground(Color.PINK);
            log.info("Your ship's side has been hit");
            state.checkProximity(computerPanel);
            Component pairPanel = mainPanel.getComponent(state.getPair());
            for (UserShip ship : state.getUserShips()) {
                if (pairPanel.getX() == ship.getX() && pairPanel.getY() == ship.getY()) {
                    ship.setSize(1);
                }
            }

            pairPanel.setBackground(Color.PINK);
            return;
        }
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
                        "You're ship has sunk",
                        "HIT!",
                        JOptionPane.PLAIN_MESSAGE);
                userShips--;
                if (state.checkGameOver()) {
                    endGame();
                    return;
                }
                break;
            case PARTIAL_HIT:
                JOptionPane.showMessageDialog(frame,
                        "A part of your ship has been hit",
                        "Oof!",
                        JOptionPane.PLAIN_MESSAGE);
                break;
            case MISS:
                JOptionPane.showMessageDialog(frame,
                        "Miss",
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
                return;
        }

    }


    public void promptPlayAgain() {
        if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play Again?",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            return;
        } else {
            System.exit(0);
        }
    }


    public void resetGame() {
        frame.setVisible(false);
        frame.removeAll();
        GameState newGame = state.resetGame();
        PlayerSelection playerSelection = new PlayerSelection();
        SwingBoard location = new SwingBoard(newGame, playerSelection);
        revalidate();
        repaint();
        return;

    }

    public void getEndingDialogue() {
        if (state.calculateWinner() == Winner.PLAYER) {
            JOptionPane.showMessageDialog(frame,
                    "You win!",
                    "Game Over!",
                    JOptionPane.PLAIN_MESSAGE);
        }
        if (state.calculateWinner() == Winner.COMPUTER) {
            JOptionPane.showMessageDialog(frame,
                    "You lose!",
                    "Game Over!",
                    JOptionPane.PLAIN_MESSAGE);

        }
        if (state.calculateWinner() == Winner.DRAW) {
            JOptionPane.showMessageDialog(frame,
                    "Draw!",
                    "Game Over!",
                    JOptionPane.PLAIN_MESSAGE);

        }
    }

    public void endGame() {
        getEndingDialogue();
        promptPlayAgain();
        resetGame();

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if (setUpMode) { //if set up mode has ended
            log.info("mouse clicked method called");
            Panel panel = (Panel) e.getSource();
            Guess currentGuess = state.getGuess();
            displayEvent(state.getOutcome(currentGuess));
            Outcomes outcome = state.getOutcome(currentGuess);

            if (state.checkGameOver()) {
                return;

            } else {
                userTurn = false;
                computerTurn = true;

                if (outcome.equals(Outcomes.INVALID)) {
                    log.info("Already guessed these coords/ships are here");
                    System.out.println("Invalid Guess! You've already guessed this or your own ships are here");
                    if (panel.getBackground() == Color.pink || panel.getBackground() == Color.green) {
                        return;
                    } else {
                        panel.setBackground(Color.YELLOW);
                    }
                    return;
                }
                if (outcome.equals(Outcomes.USERHIT)) {
                    panel.setBackground(Color.BLACK);
                    log.info("User ship Hit");
                    System.out.println("Your ship has been hit!");
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
            }
        } else {
            JPanel panel1 = (JPanel) e.getSource(); //setting colour of player ships
            panel1.setBackground(Color.GREEN);


        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        boolean shipsValid = false;
        int numberOfPanelClicked = 0;

        if (setUpMode) { //if false
            displayNoOfShipsRemaining();
            userTurn = true;
            JPanel panel = (JPanel) e.getSource();
            Guess guess = playerSelection.createGuess(panel.getX(), panel.getY());
            state.addUserGuess(guess);
            state.setGuess(guess);
            getWhichButtonGotPressed(panel.getX(), panel.getY());
            repaint();
            mouseClicked(e);
            mouseReleased(e);
        } else { //setup 
            JPanel panel2 = (JPanel) e.getSource();
            if (userShips < MAX_USER_SHIPS) {
                while (shipCreator < 6) {
                    for (Panel panel : listOfPanels) {
                        if (panel2.getX() == panel.getX() && panel2.getY() == panel.getY()) {
                            numberOfPanelClicked = listOfPanels.indexOf(panel); //gets index value of panel by finding matching x,y coords in list, created when BOARD was initiated
                            intValueOfPanel.add(numberOfPanelClicked); //creates list of panels that have been clicked
                            for (int i = 0; i < intValueOfPanel.size(); i++) {
                                if (intValueOfPanel.lastIndexOf(intValueOfPanel.get(i)) != i) { //if user selects same panel twice , choice is removed
                                    intValueOfPanel.remove(i);
                                    log.info("Removing duplicated ship...");
                                    playerInvalidShipPlacement();
                                    return;
                                }
                            }
                        }
                    }
                    mouseClicked(e);

                    shipCreator++;
                    return;

                }


                for (int i = 0; i < intValueOfPanel.size(); i++) {
                    int panelFind = intValueOfPanel.get(i);//search through list to determine ship sizes,
                    int size = state.createShipSize(panelFind, intValueOfPanel);
                    if (size > 2) {
                        playerReselectShips();
                        resetGame();
                    } else {
                        int panelX = mainPanel.getComponent(panelFind).getX();
                        int panelY = mainPanel.getComponent(panelFind).getY();
                        initShip(panelX, panelY, size);
                    }
                }
            } else { //multiple ships with same 'size' but are separate ships, user sees it as one ship.
                initAI();
                setUpMode = true;
                playerPrompt();

            }
        }
    }

    public void displayNoOfShipsRemaining() {
        int guessesRemaining = state.getGuesses();
        int computerShipsRemaining = state.getComputerShips().size();
        tfield.setText("You have " + guessesRemaining + " guesses remaining." + " The computer has " + computerShipsRemaining + " remaining.");

    }

    public ArrayList<Integer> getIntValueOfPanel() {
        return intValueOfPanel;
    }

    public void setIntValueOfPanel(ArrayList<Integer> intValueOfPanel) {
        this.intValueOfPanel = intValueOfPanel;
    }

    public void initShip(int x, int y, int size) {
        UserShip userShip = playerSelection.createUserShip(x, y, size);
        state.addPlayerShips(userShip);
        userShips++;
        debugSize();

    }

    public void debugSize() { //prints out size of ship generated
        ArrayList<UserShip> matchingShip = new ArrayList<>();
        Iterator<UserShip> iter = state.getUserShips().iterator();
        int i = 0;
        for (Iterator<UserShip> it = state.getUserShips().iterator(); it.hasNext(); i++) {
            UserShip s = it.next();
            System.out.println(i + ": " + "x: " + s.getX() + "y: " + s.getY());
            System.out.println("size: " + s.getSize());


        }
    }


    public void mouseReleased(MouseEvent e) {
        if (setUpMode) { //checks turn taking
            if (computerTurn == true && userTurn == false) {
                computerTurn();
            }
        }
    }

    public void mouseEntered(MouseEvent e) {
        if (setUpMode) {
            displayNoOfShipsRemaining();
        }
    }

    public void mouseExited(MouseEvent e) {

    }

}

////////////////////// COMPUTER WINNER/////////////////
/* Add initComputerGuess() to initAI method
disable out the other computerTurn and computerGuess method
enable methods at bottom of gameState
enable initList 2 in PlayerSelection


  private void initComputerGuess() {
      state.setComputerGuessesToUser();
    }



    private void computerTurn() { // COMPUTER WINNER
        ArrayList<Integer> computerIntValuePanel = new ArrayList();

        JOptionPane.showMessageDialog(frame,
                "Computer will now guess",
                "Aiming...",
                JOptionPane.PLAIN_MESSAGE);


        Random rand = new Random();
        int index = rand.nextInt(state.getDebugComputerGuesses().size());
        Guess computerGuess = state.getDebugComputerGuesses().get(index);
        log.info("comp guess x: " + computerGuess.getX() + "comp guess y: " + computerGuess.getY());

        for (Panel panel : listOfPanels) {
            if (panel.getX() == computerGuess.getX() && panel.getY() == computerGuess.getY()) {
                computerPanelClicked = listOfPanels.indexOf(panel);
                log.info("panel is: " + computerPanelClicked);
                computerIntValuePanel.add(computerPanelClicked);

//            }
//            for (int i = 0; i < computerIntValuePanel.size(); i++) {
//                int panelFind = computerIntValuePanel.get(i);//search through list to determine ship sizes 6
//                int panelX = mainPanel.getComponent(panelFind).getX();
//                int panelY = mainPanel.getComponent(panelFind).getY();

                state.addComputerGuess(computerGuess);
                state.getDebugComputerGuesses().remove(computerGuess);
                Outcomes outcome = state.getOutcome(computerGuess);
                displayEvent(state.getOutcome(computerGuess));
                computerTurn = false;
                computerClickOutcome(outcome, computerPanelClicked);
                checkTurn();
            }
        }
    }
}
*/

