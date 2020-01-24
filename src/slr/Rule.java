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
          
    public void shiftDot(){
        this.dotPlace++;
    }
    
    public boolean isDotEnd(){
        
        if (dotPlace == right.size()) {
            return true;
           
        }
        return false;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.left);
        hash = 83 * hash + Objects.hashCode(this.right);
        hash = 83 * hash + this.dotPlace;
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
        final Rule other = (Rule) obj;
        if (this.dotPlace != other.dotPlace) {
            return false;
        }
        if (!Objects.equals(this.left, other.left)) {
            return false;
        }
        if (!Objects.equals(this.right, other.right)) {
            return false;
        }
        return true;
    }
    
    
    public MyCharacter getAfterDot(){
        return right.get(dotPlace);
    }
    
}
