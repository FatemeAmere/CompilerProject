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
    char c;
    boolean isTerminal;
    boolean isStartChar;
    
    public Character (char c , boolean isTerminal,boolean isStartChar){
        this.c = c;
        this.isStartChar = isStartChar;
        this.isTerminal = isTerminal;
    }
}
