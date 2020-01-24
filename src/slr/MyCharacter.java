/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

import java.util.ArrayList;

/**
 *
 * @author Polaris
 */
public class MyCharacter {
    private char c;
    private boolean isTerminal;
    private boolean isStartChar;
    private ArrayList<Rule> AssociatedRule;
    
    public MyCharacter (char c , boolean isTerminal,boolean isStartChar){
        this.c = c;
        this.isStartChar = isStartChar;
        this.isTerminal = isTerminal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.c;
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
        final MyCharacter other = (MyCharacter) obj;
        if (this.c != other.c) {
            return false;
        }
        return true;
    }

    public ArrayList<Rule> getAssociatedRule() {
        return AssociatedRule;
    }

    public void addToAssociatedRule(Rule r) {
        this.AssociatedRule.add(r);
    }

    public char getC() {
        return c;
    }

    public void setC(char c) {
        this.c = c;
    }

    public boolean isIsTerminal() {
        return isTerminal;
    }

    public void setIsTerminal(boolean isTerminal) {
        this.isTerminal = isTerminal;
    }

    public boolean isIsStartChar() {
        return isStartChar;
    }

    public void setIsStartChar(boolean isStartChar) {
        this.isStartChar = isStartChar;
    }

    
    
    @Override
    public String toString() {
        return c + "''" +isTerminal + "''" + isStartChar;
    }
    
    
}
