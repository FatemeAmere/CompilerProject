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
    ArrayList makeDiagram (ArrayList<Rule> rules){
        ArrayList states ,my_rules;
        int i ;
        Character dot = new Character('.', false, false);
        Rule currentRule = rules.get(0);
        i = 0;
        currentRule.setDotPlace(i);
        if(currentRule.getRight().get(i).get){
            
        }
        
        
        
        
        
        //State s =  new State(my_rules);
       
        
        
        return states;
    }

    private boolean checkAfterDot(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
