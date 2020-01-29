/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

import java.util.ArrayList;

/**
 *
 * @author marzieh
 */
public class diagram {

    
    

    private ArrayList<Rule> rules;
    private ArrayList<State> states = new ArrayList<>();
    private ArrayList<Vector> vectors = new ArrayList<>();

    public diagram(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public ArrayList<Vector> getVectors() {
        return vectors;
    }

    
    
    

    

    

    

    private MyCharacter absentInChars(ArrayList<MyCharacter> vecChars, MyCharacter charAfterDot) {

        MyCharacter matchedChar = null;
        int notMatched = 0;
        for (MyCharacter vc : vecChars) {

            if (!charAfterDot.equals(vc)) {

                matchedChar.equals(vc);

            }

        }

        return matchedChar;
    }

    
}