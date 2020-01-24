/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Polaris
 */
public class State {

    private ArrayList<Rule> rules;
    private int number;

    public State(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public ArrayList<Rule> getRules() {
        return rules;
    }

    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean hasContinue() {
        int countOfEndDots = 0;
        for (Rule r : rules) {
            if (r.isDotEnd()) {
                countOfEndDots++;
            }
        }
        if (countOfEndDots == rules.size()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.rules);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.rules, other.rules)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "State{" + "rules=" + rules + ", number=" + number + '}';
    }

    
}
