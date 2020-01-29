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
                    //first version
                    /*nextChar = right.get(mainCharIndex + 1);

                    tmpSet = computeFirst(nextChar);

                    followSet.addAll(tmpSet);
                    if (tmpSet.contains(new MyCharacter('#', true, false))) {
                        followSet.addAll(computeFollow(rule.getLeft()));
                    } */
                    //************************* //second version
                    for (int i = mainCharIndex + 1; i < right.size(); i++) {
                        MyCharacter ch = right.get(i);
                        //System.out.println("$" + ch);
                        tmpSet = computeFirst(ch);

                        followSet.addAll(tmpSet);
                        if (!tmpSet.contains(new MyCharacter('#', true, false))) {
                            break;
                        } else if (i == right.size() - 1) {
                            mainChar.getFollowDependencies().add(rule.getLeft());
                            if (!mainChar.equals(rule.getLeft())) {// for rules like: A->BA
                                if (!rule.getLeft().getFollowDependencies().contains(mainChar)) {
                                    followSet.addAll(computeFollow(rule.getLeft()));
                                }
                            }
                        }

                    }

                    //*************************
                } else {
                    mainChar.getFollowDependencies().add(rule.getLeft());
                    if (!mainChar.equals(rule.getLeft())) {// for rules like: A->BA
                        if (!rule.getLeft().getFollowDependencies().contains(mainChar)) {
                            followSet.addAll(computeFollow(rule.getLeft()));
                        }
                    }
                }
            }
        }

        followSet.remove(new MyCharacter('#', true, false));

        return followSet;
    }

    public static HashSet computeFollowFinal(MyCharacter mainChar) {    
        HashSet<MyCharacter> mainCharFollowSet = new HashSet<MyCharacter>();      
        HashSet<MyCharacter> depFollowSet = new HashSet<MyCharacter>();
        
        mainCharFollowSet = computeFollow(mainChar);
        
        for(MyCharacter ch:mainChar.getFollowDependencies()){
            depFollowSet.addAll(computeFollow(ch));
        }
        
        mainCharFollowSet.addAll(depFollowSet);
        
        return mainCharFollowSet;
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
                //System.out.println("MainCHar: " + mainChar);
                left = rule.getLeft();
                //System.out.println("left: " + left);
                right = rule.getRight();
                //System.out.println("right: " + right);

                if (left.equals(mainChar)) {// if mainChar is in the right side

                    for (MyCharacter ch : right) {
                        //System.out.println("$" + ch);
                        if (!ch.equals(mainChar)) {// for rules like E->E+T
                            tmpSet = computeFirst(ch);

                            firstSet.addAll(tmpSet);
                            if (!tmpSet.contains(new MyCharacter('#', true, false))) {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return firstSet;
    }

    public static void addReduces(ArrayList<State> states, Object[][] parseTable) {
        HashSet<MyCharacter> tmpSet = new HashSet<MyCharacter>();
        int ruleNumber;
        Object tmpObject;
        ArrayList<String> cell;

        for (State state : states) {
            for (Rule rule : state.getRules()) {
                //System.out.println("state: " + state);
                //System.out.println("rule: " + rule);
                if (rule.isDotEnd()) {
                    tmpSet = computeFollowFinal(rule.getLeft());
                    //System.out.println("tmpSet: " + tmpSet);
                    ruleNumber = computeRuleNumber(rule);
                    for (MyCharacter ch : tmpSet) {
                        tmpObject = parseTable[state.getNumber() + 1][SLR.getColumnIndex(ch.getC())];

                        cell = (ArrayList<String>) tmpObject;
                        //System.out.println("cell: " + cell);
                        if (rule.getLeft().getC() == '@') {
                            cell.add("Acc");
                        } else {
                            cell.add("r" + ruleNumber);
                            //System.out.println("updated cell: " + cell);
                        }
                    }
                }
            }
        }

    }

    public static int computeRuleNumber(Rule mainRule) {
        int ruleNumber = 0;

        for (Rule rule : grammer) {
            if (rule.getLeft().equals(mainRule.getLeft())
                    && rule.getRight().equals(mainRule.getRight())) {
                ruleNumber = grammer.indexOf(rule);
            }
        }

        return ruleNumber;
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
