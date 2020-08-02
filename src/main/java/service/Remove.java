package service;

import entities.Guess;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Remove {

    public ArrayList resetLists(ArrayList values) {
        for (Object value : values) {
            values.remove(value);
        }
        return values;
    }
}



//    public void removeInValidGuess(Guess guess, ArrayList list){
//        list.remove(guess);
//    }

