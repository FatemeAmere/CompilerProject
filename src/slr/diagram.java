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

    public ArrayList<Rule> rules;
    public ArrayList<State> states = new ArrayList<>();
    public ArrayList<Vector> vectors = new ArrayList<>();

    void makeDiagram() {

        State currentState = createFirstState();

        addStateToDiagram(currentState);

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

    private boolean absentInChars(ArrayList<MyCharacter> vecChars, MyCharacter afterDot) {

        int notMatched = 0;
        for (MyCharacter vc : vecChars) {

            if (!afterDot.equals(vc)) {

                notMatched++;

            }

        }
        if (notMatched == vecChars.size()) {
            return true;
        }

        return false;
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
        Rule currentRule = rules.get(0);
        currentRule.setDotPlace(0);

        currentState.addRule(currentRule);
        if (!currentRule.getAfterDot().isIsTerminal()) {
            addAnotherRules(currentState, currentRule.getAfterDot());

        }
        states.add(currentState);
        return currentState;
    }

    private ArrayList getVectorChars(State currentState) {

        ArrayList<MyCharacter> vecChars = new ArrayList<>();
        MyCharacter afterDot;
        for (Rule r : currentState.getRules()) {
            afterDot = r.getAfterDot();
            afterDot.addToAssociatedRule(r);//-----> BUG
            if (absentInChars(vecChars, afterDot)) {
                vecChars.add(afterDot);
            }
        }
        return vecChars;
    }
}
