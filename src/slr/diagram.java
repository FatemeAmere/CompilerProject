/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElementDecl;

/**
 *
 * @author marzieh
 */
public class diagram {

    State foundedObjInArr;

    ArrayList makeDiagram(ArrayList<Rule> rules) {
        MyCharacter afterDot;
        ArrayList<MyCharacter> vecChars = new ArrayList<>();
        ArrayList states = new ArrayList<>();
        ArrayList<Rule> my_rules = new ArrayList<>();
        int dotLoc, ruleNum = -1;

        Rule currentRule = rules.get(0);
        dotLoc = 0;
        currentRule.setDotPlace(dotLoc);
        my_rules.add(currentRule);
        MyCharacter firstRight = currentRule.getRight().get(dotLoc);
        if (!firstRight.isIsTerminal()) {
            for (int j = 0; j < rules.size(); j++) {
                if (rules.get(j).getLeft().equals(firstRight)) {
                    my_rules.add(rules.get(j));
                }

            }
        }
        State currentState = new State(my_rules);
        states.add(currentState);
        // now we have state0

        for (Rule r : my_rules) {
            afterDot = r.getAfterDot();
            if (absentInChars(vecChars, afterDot)) {//TODO
                vecChars.add(afterDot);
            }
        }

        for (MyCharacter c : vecChars) {
            State nextState = getNextState(currentState, c);
            State DuplicateState =absentInStates(states, nextState);
            if (DuplicateState != null) {
                nextState = absentInStates(states, nextState);
            }
            Vector v = new Vector(c, currentState, nextState);//TODO
        }

        //State s =  new State(my_rules);
        return states;
    }

    State makeaState(ArrayList<Rule> rules) {

    }

    private State getNextState(State s, MyCharacter c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean absentInChars(ArrayList<MyCharacter> vecChars, MyCharacter afterDot) {

        for (MyCharacter vc : vecChars) {

        }

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private State absentInStates(ArrayList states, State nextState) {

        for (MyCharacter vc : vecChars) {

        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
