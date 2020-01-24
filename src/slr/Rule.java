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
public class Rule {
    private Character left;
    private ArrayList<Character> right;
    private int dotPlace = -1;
    
    public Rule(ArrayList<Character> r,Character l){
        this.right = r;
        this.left = l;
    }
        
    public Rule(ArrayList<Character> r,Character l,int dotPlace){
         this.right = r;
        this.left = l;
        this.dotPlace = dotPlace;
    }
            
    public Character getLeft() {
        return left;
    }

    public void setLeft(Character left) {
        this.left = left;
    }

    public ArrayList<Character> getRight() {
        return right;
    }

    public void setRight(ArrayList<Character> right) {
        this.right = right;
    }

    public int getDotPlace() {
        return dotPlace;
    }

    public void setDotPlace(int dotPlace) {
        this.dotPlace = dotPlace;
    }
    
    
}
