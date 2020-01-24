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
        ArrayList states =  new ArrayList<>();
        ArrayList<Rule> my_rules = new ArrayList<>();
        int dotLoc , ruleNum=-1;
        
        Rule currentRule = rules.get(0);
        dotLoc = 0;
        currentRule.setDotPlace(dotLoc);
        my_rules.add(currentRule);
        Character firstRight = currentRule.getRight().get(dotLoc);
        if(!firstRight.isIsTerminal()){
            for(int j=0 ;j< rules.size(); j++){
                if(j== ruleNum){
                    continue;
                }
                if(rules.get(j).getLeft().equals(firstRight)){
                    my_rules.add(rules.get(j));
                }
                
            }
        }
        State s = new State(my_rules);
        states.add(s);
        // now we have state0
        
        for(int j=0 ;j< my_rules.size(); j++){
            
        }
        
        
        
        
        //State s =  new State(my_rules);
       
        
        
        return states;
    }

    State makeaState (ArrayList<Rule> rules){
        
    }
}
