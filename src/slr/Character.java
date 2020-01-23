/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

/**
 *
 * @author Polaris
 */
public class Character {
    private char c;
    private boolean isTerminal;
    private boolean isStartChar;
    
    public Character (char c , boolean isTerminal,boolean isStartChar){
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
        final Character other = (Character) obj;
        if (this.c != other.c) {
            return false;
        }
        return true;
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
    
    
    
}
