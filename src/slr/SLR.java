/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slr;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Fatemeh and Marzieh
 */
public class SLR {

    static boolean isFirstRule = true;
    public static ArrayList<Rule> grammer;
    static char start;
    private static ArrayList<Character> terminals = new ArrayList<Character>();
    private static ArrayList<Character> nonTerminals = new ArrayList<Character>();

    static int rowSize;
    static int columnSize;
    //HashSet<Character> terminals1 = new HashSet<Character>();
    //ArrayList<ArrayList<Object>> parseTable = new ArrayList<ArrayList<Object>>();
    static Object[][] parseTable;

    static ArrayList<Vector> vectors = new ArrayList<Vector>();
    static ArrayList<State> states = new ArrayList<State>();
    static int stateCounter = 0;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int GrammerSize = scanner.nextInt();
        String rule;
        grammer = new ArrayList<Rule>();
        for (int i = 0; i < (GrammerSize); i++) {
            rule = scanner.next();
            SplitRule(rule);
        }

        //Debug
        //printGrammerTerminalAndNonTerminals();        
        //Fill vectors and states; 
        makeDiagram(); // marzi's work

        int count = 0;
        for (State s : states) {
            System.out.println("state" + count + " " + s + "\n");
            count++;
        }
        count = 0;

        System.out.println("\n\n");
        for (Vector v : vectors) {
            System.out.println("vector" + count + " " + v + "\n");
            count++;
        }

        //**************************************** Mahi tests for reduce
//        System.out.println("heLLO");
            createParseTable();
//        printParseTable();
           Reduce.addReduces(states, parseTable);
         printParseTable();
        //****************************************
        //then
        //creating parse table      
//        createParseTable();      
//        
        ShiftAndGoto();
//         
        printParseTable();
    }

    //---------------------------------------------------------------------------------------------------
    public static int getColumnIndex(char c) {
        for (int j = 1; j < columnSize; j++) {
            char inTable = (char) parseTable[0][j];
            if (inTable == c) {
                return j;
            }
        }
        return -1;
    }

    private static void SplitRule(String r) {
        ArrayList<MyCharacter> rightArray = new ArrayList<>();
        boolean isTerminal;
        boolean isStartChar;
        MyCharacter left;
        MyCharacter right;

        String[] splited = r.split("->", 2);

        if (isFirstRule) {
            start = splited[0].charAt(0);
            left = new MyCharacter('@', false, false);
            right = new MyCharacter(splited[0].charAt(0), false, true);
            rightArray.add(right);
            Rule firstRule = new Rule(rightArray, left);
            grammer.add(firstRule);
            isFirstRule = false;
        }
        rightArray = new ArrayList<>();

        isTerminal = CheckTerminal(splited[0].charAt(0));
        isStartChar = CheckStartChar(splited[0].charAt(0));
        left = new MyCharacter(splited[0].charAt(0), isTerminal, isStartChar);

        /*for (int i = 0; i < nonTerminals.size(); i++) {
            if(splited[0].charAt(0) == nonTerminals.get(i)){
                break;
            }
            if(i == nonTerminals.size()-1)
                 nonTerminals.add(splited[0].charAt(0));            
        }*/
        if (!nonTerminals.contains(splited[0].charAt(0))) {
            nonTerminals.add(splited[0].charAt(0));
        }

        for (int i = 0; i < splited[1].length(); i++) {
            isTerminal = CheckTerminal(splited[1].charAt(i));
            isStartChar = CheckStartChar(splited[1].charAt(i));
            MyCharacter a = new MyCharacter(splited[1].charAt(i), isTerminal, isStartChar);
            if (isTerminal && !terminals.contains(splited[1].charAt(i))) {
                terminals.add(splited[1].charAt(i));
            }

            rightArray.add(a);
        }
        Rule firstRule = new Rule(rightArray, left);
        grammer.add(firstRule);
    }

    private static boolean CheckTerminal(char c) {
        if (c >= 65 && c <= 90) {
            return false;
        }

        return true;
    }

    private static boolean CheckStartChar(char c) {
        if (c == start) {
            return true;
        }

        return false;
    }

    private static void ShiftAndGoto() {
        for (Vector v : vectors) {
            MyCharacter c = v.getValue();
            int columnIndex = getColumnIndex(c.getC());
            if (c.isIsTerminal()) { //*shift
                ArrayList<String> s = (ArrayList<String>) parseTable[v.getSource() + 1][columnIndex];
                String string = "s" + v.getDestination();
                //if (s != null) {
                  //  s.add(string);
                //} else {
                  //  s = new ArrayList<String>();
                    s.add(string);
               // }
                parseTable[v.getSource() + 1][columnIndex] = s;
            } else { //*got to
                parseTable[v.getSource() + 1][columnIndex] = v.getDestination();
            }
        }
    }

    private static void createParseTable() {
        boolean includeEpsilon = false;
        int terminalsSize = terminals.size();
        for (Character c : terminals) {
            if (c == '#') {
                includeEpsilon = true;
                terminalsSize--;
                break;
            }
        }
        rowSize = states.size() + 1;
        columnSize = terminalsSize + 1 + 1 + nonTerminals.size(); //one for begining one for $
        parseTable = new Object[rowSize][columnSize]; //one for $

        for (int j = 0; j < terminals.size(); j++) {
            if (terminals.get(j) != '#') {
                parseTable[0][j + 1] = terminals.get(j);
            }
        }
        parseTable[0][terminalsSize + 1] = '$';
        for (int j = 0; j < nonTerminals.size(); j++) {
            parseTable[0][terminalsSize + 2 + j] = nonTerminals.get(j);
        }
        for (int i = 0; i < states.size(); i++) {
            parseTable[i + 1][0] = i;
        }

        for (int i = 1; i < rowSize; i++) {
            for (int j = 1; j < columnSize; j++) {
                parseTable[i][j] = new ArrayList<String>();
            }
        }
    }

    private static void printGrammerTerminalAndNonTerminals() {
        //Debug
        System.out.println();
        for (int i = 0; i < grammer.size(); i++) {
            System.out.println(grammer.get(i).toString());
        }
        for (int i = 0; i < terminals.size(); i++) {
            System.out.println(terminals.get(i));
        }
        System.out.println();
        for (int i = 0; i < nonTerminals.size(); i++) {
            System.out.println(nonTerminals.get(i));
        }

    }

    private static void printParseTable() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(parseTable[i][j] + " , ");
            }
            System.out.println();
        }
    }

    //-----------------------------------------------------------------------
    private static void makeDiagram() {

        State firstState = createFirstState();
        addStateToDiagram(firstState);

    }

    private static State createFirstState() {
        State firstState = new State();
        firstState.setNumber(stateCounter);
        stateCounter++;
        Rule firstRule = grammer.get(0);
        firstRule.setDotPlace(0);

        firstState.addRule(firstRule);
        MyCharacter charAfterDot = firstRule.getAfterDot();

        if (!charAfterDot.isIsTerminal() && charAfterDot.getC() != '%') {
            addAnotherRules(firstState, firstRule.getAfterDot());

        }
        states.add(firstState);
        return firstState;
    }

    private static void addAnotherRules(State currentState, MyCharacter firstRight) {
        for (int j = 0; j < grammer.size(); j++) {
            if (grammer.get(j).getLeft().equals(firstRight)) {
                Rule r;
                if (grammer.get(j).getRight().get(0).getC() == '#') {

                    r = new Rule(grammer.get(j).getRight(), grammer.get(j).getLeft(), 1);
                } else {
                    r = new Rule(grammer.get(j).getRight(), grammer.get(j).getLeft(), 0);
                }
                boolean presentInRules = false;
                for (Rule rule : currentState.getRules()) {
                    if (rule.equals(r)) {
                        presentInRules = true;
                    }
                }
                if (!presentInRules) {

                    currentState.addRule(r);

                    if (!r.getAfterDot().isIsTerminal()) {
                        addAnotherRules(currentState, r.getAfterDot());
                    }
                }

            }

        }
    }

    private static void addStateToDiagram(State currentState) {

        ArrayList<MyCharacter> vecChars = getVectorChars(currentState);

        for (MyCharacter c : vecChars) {
            setAssicoateForC(c, currentState);
            State nextState = getNextState(currentState, c);
            State DuplicateState = absentInStates(nextState);
            boolean loopHappend = false;
            if (DuplicateState != null) {
                Vector v = new Vector(c, currentState.getNumber(), DuplicateState.getNumber());
                vectors.add(v);
                System.out.println("vector = " + v + "\n\n");
            } else {
                nextState.setNumber(stateCounter);
                stateCounter++;
                states.add(nextState);
                System.out.println("state = " + nextState);
                Vector v = new Vector(c, currentState.getNumber(), nextState.getNumber());
                vectors.add(v);
                System.out.println("vector = " + v + "\n\n");

                if (nextState.hasContinue()) {

                    addStateToDiagram(nextState);

                }
            }
        }
    }

    private static ArrayList getVectorChars(State currentState) {

        ArrayList<MyCharacter> vecChars = new ArrayList<>();

        for (Rule r : currentState.getRules()) {

            if (r.getAfterDot().getC() != '%') {
                boolean presentInVC = false;
                for (MyCharacter vc : vecChars) {
                    if (r.getAfterDot().equals(vc)) {
                        presentInVC = true;
                    }
                }
                if (!presentInVC) {
                    vecChars.add(r.getAfterDot());
                }
            }
        }
        return vecChars;
    }

    private static void setAssicoateForC(MyCharacter c, State currentState) {
        for (Rule r : currentState.getRules()) {
            if (r.getAfterDot().getC() != '%') {
                if (r.getAfterDot().equals(c)) {
                    c.addToAssociatedRule(r);
                }
            }
        }

    }

    private static State getNextState(State s, MyCharacter c) {
        State nextState = new State();
        for (Rule r : c.getAssociatedRule()) {
            System.out.println("associate((  " + c + " : " + r + "  ))");
            Rule copyRule = new Rule(r.getRight(), r.getLeft(), r.getDotPlace());
            copyRule.shiftDot();
            nextState.addRule(copyRule);
            if (!copyRule.getAfterDot().isIsTerminal()) {
                addAnotherRules(nextState, copyRule.getAfterDot());
            }

        }
        c.clearAssociateForC();
        return nextState;
    }

    private static State absentInStates(State nextState) {

        State matchedState = null;
        for (State s : states) {

            if (nextState.equals(s)) {

                return s;

            }
        }

        return matchedState;
    }

}


/*

2
S->A
A->a


3
S->AB
A->a
B->b

      

5
S->LaR
S->R
L->bR
L->d
R->L
---
0to9 state



6
E->E+T
E->T
T->TF
F->F*
F->a
F->b
---
0to8 state



4
S->AaAb
S->BbBa
A->#
B->#
---
0to9 state


7
S->Ea
E->EbT
E->T
T->FcT
T->F
F->dEe
F->f
---
13

 */
