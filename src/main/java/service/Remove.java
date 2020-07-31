package service;

import entities.Guess;

import java.util.ArrayList;

public class Remove {


    public void removeInValidGuess(Guess guess, ArrayList list){
        list.remove(guess);
    }



}
