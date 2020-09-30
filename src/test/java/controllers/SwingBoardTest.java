package controllers;

import entities.ComputerShip;
import entities.Guess;
import entities.UserShip;
import enumeration.Outcomes;
import org.junit.jupiter.api.Test;
import service.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SwingBoardTest {
    PlayerSelection playerSelection = new PlayerSelection();
    GameState state = new GameState();
    SwingBoard swingBoard = new SwingBoard(state, playerSelection);


    @Test
    public void shouldSetUpComputerShip() {
        int expectedShipArrayListSize = 1;
        swingBoard.initComputerShips(3);
        List<ComputerShip> computerShip = state.getComputerShips();
        assertNotNull(computerShip);
        assertEquals(expectedShipArrayListSize, computerShip.size());
    }

    @Test
    public void shouldReselectComputerShip() {
        int randomPanel = 0;
        UserShip userShip = playerSelection.createUserShip(1, 2, 1);
        UserShip userShip1 = playerSelection.createUserShip(54, 2, 1);
        state.addPlayerShips(userShip);
        state.addPlayerShips(userShip1);

        int size = 4;
        List<Integer> testPanelNumbers = new ArrayList<>(size);
        for (int i = 0; i <= size; i++) {
            testPanelNumbers.add(i);
        }
        while (!(state.getComputerShips().size() == 1)) {
             randomPanel = playerSelection.generateComputerPanel(testPanelNumbers);
            swingBoard.initComputerShips(randomPanel);
        }
            assertTrue( randomPanel>=2);
        }



    @Test
    public void shouldHitUserShip(){
        UserShip userShip = playerSelection.createUserShip(52,2,1);
        state.addPlayerShips(userShip);

        Guess computerGuess = playerSelection.createGuess(52,2);
        state.addComputerGuess(computerGuess);

        assertEquals(Outcomes.USERHIT, state.getOutcome(computerGuess));
    }

    @Test
    public void shouldHitComputerShips(){
        swingBoard.initComputerShips(1);
        swingBoard.initComputerShips(5);
        Guess userGuess = playerSelection.createGuess(1,2);
        state.addUserGuess(userGuess);
        assertEquals(Outcomes.GAME_OVER, state.getOutcome(userGuess));
    }




    @Test
    public void shouldUserShipHavePartialHit() {
        UserShip userShip1 = playerSelection.createUserShip(1, 2, 2);
        UserShip userShip2 = playerSelection.createUserShip(52, 2, 2);
        state.addPlayerShips(userShip1);
        state.addPlayerShips(userShip2);
        Guess computerGuess = playerSelection.createGuess(52, 2);
        state.addComputerGuess(computerGuess);
        assertEquals(Outcomes.PARTIAL_HIT, state.getOutcome(computerGuess));
    }

//    @Test
//    public void shouldComputerWin(){
//
//
//
//
//    }
//
//
//
//
//
//    }






}