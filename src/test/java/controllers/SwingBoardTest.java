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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SwingBoardTest {
    PlayerSelection playerSelection = new PlayerSelection();
    GameState state = new GameState();
    SwingBoard swingBoard = new SwingBoard(state, playerSelection);



    @Test
    public void shouldSetUpComputerShip() {
        swingBoard.initComputerShips(3);
        List<ComputerShip> computerShip = state.getComputerShips();
        assertNotNull(computerShip);
        assertEquals(1, computerShip.size());
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
        Guess userGuess = playerSelection.createGuess(1,2);
        state.addUserGuess(userGuess);
        assertEquals(Outcomes.HIT, state.getOutcome(userGuess));
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