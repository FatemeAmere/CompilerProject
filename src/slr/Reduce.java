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
        ArrayList<MyCharacter> right;
        int rightSize;
        int mainCharIndex;
        MyCharacter nextChar;

        if (mainChar == grammer.get(0).getLeft()) {
            followSet.add(new MyCharacter('$', true, false));
        }

        for (Rule rule : grammer) {
            right = rule.getRight();
            rightSize = right.size();

            if (right.contains(mainChar)) {// if mainChar is in the right side
                mainCharIndex = right.indexOf(mainChar);

                if (mainCharIndex != right.size() - 1) {//if mainChar is not the last Char
                    nextChar = right.get(mainCharIndex + 1);

                    if (nextChar.isIsTerminal()) {
                        followSet.add(nextChar);
                    } else {
                        if (computeFirst(nextChar).contains(new MyCharacter('#', true, false))) {
                            followSet.addAll(computeFollow(rule.getLeft()));
                        }
                    }

                } else {
                    followSet.addAll(computeFollow(rule.getLeft()));
                }
            }
        }

        return followSet;
    }

    private static HashSet computeFirst(MyCharacter mainChar) {
        HashSet<MyCharacter> firstSet = new HashSet<MyCharacter>();
        MyCharacter left;
        ArrayList<MyCharacter> right;

        if (mainChar.isIsTerminal()) {
            firstSet.add(new MyCharacter(mainChar.getC(), true, false));
        } else {

            for (Rule rule : grammer) {
                left = rule.getLeft();
                right = rule.getRight();

                if (left == mainChar) {// if mainChar is in the right side

                    for(MyCharacter ch:right)
                    {
                        firstSet.addAll(computeFirst(ch));
                        if (!computeFirst(ch).contains(new MyCharacter('#', true, false))) {
                            break;
                        }
                    }
                }
            }
        }
        return firstSet;
    }

}
