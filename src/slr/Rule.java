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
    private MyCharacter left;
    private ArrayList<MyCharacter> right;
    private int dotPlace = -1;
    
    public Rule(ArrayList<MyCharacter> r,MyCharacter l){
        this.right = r;
        this.left = l;
    }
        
    public Rule(ArrayList<MyCharacter> r,MyCharacter l,int dotPlace){
         this.right = r;
        this.left = l;
        this.dotPlace = dotPlace;
    }
            
    public MyCharacter getLeft() {
        return left;
    }

    public void setLeft(MyCharacter left) {
        this.left = left;
    }

    public ArrayList<MyCharacter> getRight() {
        return right;
    }

    public void setRight(ArrayList<MyCharacter> right) {
        this.right = right;
    }

    public int getDotPlace() {
        return dotPlace;
    }

    public void setDotPlace(int dotPlace) {
        this.dotPlace = dotPlace;
    }

    @Override
    public String toString() {
        String s = left.toString() + "->" ;
        for (int i = 0; i < right.size();i++) {
            s += right.get(i).toString();
        }
        return s;
    }
    
    public MyCharacter getAfterDot(){
        return right.get(dotPlace);
    }
    
}
