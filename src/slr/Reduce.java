/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

import java.util.ArrayList;
import java.util.HashSet;
import static slr.SLR.grammer;

/**
 *
 * @author mahsa
 */
public class Reduce {

    public static HashSet computeFollow(MyCharacter mainChar) {
        HashSet<MyCharacter> followSet = new HashSet<MyCharacter>();
        HashSet<MyCharacter> tmpSet = new HashSet<MyCharacter>();
        ArrayList<MyCharacter> right;
        int mainCharIndex;
        MyCharacter nextChar;

        if (mainChar.equals(grammer.get(0).getLeft())) {
            followSet.add(new MyCharacter('$', true, false));
        }

        for (Rule rule : grammer) {
            right = rule.getRight();

            if (right.contains(mainChar)) {// if mainChar is in the right side
                mainCharIndex = right.indexOf(mainChar);

                if (mainCharIndex != right.size() - 1) {//if mainChar is not the last Char
                    nextChar = right.get(mainCharIndex + 1);

                    tmpSet = computeFirst(nextChar);

                    followSet.addAll(tmpSet);
                    if (tmpSet.contains(new MyCharacter('#', true, false))) {
                        followSet.addAll(computeFollow(rule.getLeft()));
                    }

                } else {
                    if (!mainChar.equals(rule.getLeft())) {// for rules like: A->BA
                        followSet.addAll(computeFollow(rule.getLeft()));
                    }
                }
            }
        }

        followSet.remove(new MyCharacter('#', true, false));

        return followSet;
    }

    public static HashSet computeFirst(MyCharacter mainChar) {
        HashSet<MyCharacter> firstSet = new HashSet<MyCharacter>();
        HashSet<MyCharacter> tmpSet = new HashSet<MyCharacter>();
        MyCharacter left;
        ArrayList<MyCharacter> right;

        if (mainChar.isIsTerminal()) {
            firstSet.add(new MyCharacter(mainChar.getC(), true, false));
        } else {

            for (Rule rule : grammer) {
                //System.out.println("MainCHar: "+mainChar);
                left = rule.getLeft();
                //System.out.println("left: "+left);
                right = rule.getRight();
                //System.out.println("right: "+right);

                if (left.equals(mainChar)) {// if mainChar is in the right side

                    for (MyCharacter ch : right) {
                        //System.out.println("$"+ch);
                        tmpSet = computeFirst(ch);
                        firstSet.addAll(tmpSet);
                        if (!tmpSet.contains(new MyCharacter('#', true, false))) {
                            break;
                        }
                    }
                }
            }
        }
        return firstSet;
    }
    
    public static void addReduces(ArrayList<State> states,Object[][] parseTable){
        
        for(State state:states){
            for(Rule rule:state.getRules()){
                
            }
        }
        
    }

}

/* for test
8
E->TA
A->+TA
A->#
T->FB
B->*FB
B->#
F->(E)
F->n
 */
