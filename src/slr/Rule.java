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
    Character left;
    ArrayList<Character> right;
    
    public Rule(ArrayList<Character> r,Character l){
        this.right = r;
        this.left = l;
    }
    
}
