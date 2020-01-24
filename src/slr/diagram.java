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

    
    
    void makeDiagram() {

        State currentState = createFirstState();

        //addStateToDiagram(currentState);

    }

    private void addStateToDiagram(State currentState) {

        ArrayList<MyCharacter> vecChars = getVectorChars(currentState);

        for (MyCharacter c : vecChars) {
            State nextState = getNextState(currentState, c);
            State DuplicateState = absentInStates(nextState);
            if (DuplicateState != null) {
                nextState.equals(DuplicateState);
            }
            Vector v = new Vector(c, currentState.getNumber(), nextState.getNumber());//TODO
            vectors.add(v);

            if (nextState.hasContinue()) {

                addStateToDiagram(nextState);

            }
        }
    }

    private State getNextState(State s, MyCharacter c) {
        State nextState = null;
        for (Rule r : c.getAssociatedRule()) {
            r.shiftDot();
            nextState.addRule(r);
            if (r.getAfterDot().isIsStartChar()) {

                addAnotherRules(nextState, r.getAfterDot());
            }

        }
        return nextState;
    }

    private ArrayList getVectorChars(State currentState) {

        ArrayList<MyCharacter> vecChars = new ArrayList<>();
        MyCharacter afterDot;
        boolean dontAddInVC = false;
        for (Rule r : currentState.getRules()) {

            for (MyCharacter vc : vecChars) {
                if (r.getAfterDot().equals(vc)) {

                    vc.addToAssociatedRule(r);
                    dontAddInVC =true;
                }

            }
            
            if (!dontAddInVC) {
                r.getAfterDot().addToAssociatedRule(r);
                vecChars.add(r.getAfterDot());
            }

        }
        return vecChars;
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

    private State absentInStates(State nextState) {

        State matchedState = null;
        for (State s : states) {

            if (nextState.equals(s)) {

                matchedState.equals(s);

            }
        }

        return matchedState;
    }

    private void addAnotherRules(State currentState, MyCharacter firstRight) {
        for (int j = 0; j < rules.size(); j++) {
            if (rules.get(j).getLeft().equals(firstRight)) {
                Rule r = new Rule(rules.get(j).getRight(), rules.get(j).getLeft(), 0);
                currentState.addRule(r);
            }

        }
    }

    private State createFirstState() {
        State currentState = new State(null);
        Rule currentRule=rules.get(0);
        currentRule.setDotPlace(0);

        currentState.addRule(currentRule);
        if (!currentRule.getAfterDot().isIsTerminal()) {
            addAnotherRules(currentState, currentRule.getAfterDot());

        }
        states.add(currentState);
        return currentState;
    }

}
