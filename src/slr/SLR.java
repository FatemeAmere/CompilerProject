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
 * @author Polaris
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
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {      
        Scanner scanner = new Scanner(System.in);
        int GrammerSize = scanner.nextInt();
        String rule ;
        grammer = new ArrayList<Rule>();
        for (int i = 0; i < (GrammerSize); i++) {
            rule = scanner.next();
            SplitRule(rule);
        }
        
        //Debug
        printGrammerTerminalAndNonTerminals();        
        
        //Fill vectors and states; 
        
        
        
        
        
        //then
        //creating parse table      
        createParseTable();      
        
        ShiftAndGoto();
         
        PrintParseTable();
    }
    
    
    private static int getColumnIndex(char c){
        for (int j = 1; j < columnSize ; j++) {
            char inTable = (char)parseTable[0][j];
            if(inTable == c){
                return j;
            }
        }
        return -1;
    }
    
    private static void SplitRule(String r){
        ArrayList<MyCharacter> rightArray = new ArrayList<>();
        boolean isTerminal;
        boolean isStartChar;
        MyCharacter left;
        MyCharacter right;
        
        String[] splited = r.split("->",2);
        
        if(isFirstRule){
            start = splited[0].charAt(0);
            left = new MyCharacter('@',false,false);
            right = new MyCharacter(splited[0].charAt(0),false,true);
            rightArray.add(right);
            Rule firstRule = new Rule(rightArray,left);
            grammer.add(firstRule);
            isFirstRule = false;
        }
        rightArray = new ArrayList<>();
        
        isTerminal = CheckTerminal(splited[0].charAt(0));
        isStartChar = CheckStartChar(splited[0].charAt(0));      
        left = new MyCharacter(splited[0].charAt(0),isTerminal,isStartChar);
        
        
        /*for (int i = 0; i < nonTerminals.size(); i++) {
            if(splited[0].charAt(0) == nonTerminals.get(i)){
                break;
            }
            if(i == nonTerminals.size()-1)
                 nonTerminals.add(splited[0].charAt(0));            
        }*/  
        
        if(!nonTerminals.contains(splited[0].charAt(0))){
            nonTerminals.add(splited[0].charAt(0));          
        }
            
        for (int i = 0; i < splited[1].length(); i++) {
            isTerminal = CheckTerminal(splited[1].charAt(i));
            isStartChar = CheckStartChar(splited[1].charAt(i));  
            MyCharacter a = new MyCharacter(splited[1].charAt(i),isTerminal,isStartChar); 
            if(isTerminal && !terminals.contains(splited[1].charAt(i)) )              
                terminals.add(splited[1].charAt(i));
            
            rightArray.add(a);
        }
        Rule firstRule = new Rule(rightArray,left);
        grammer.add(firstRule);
    }
    
    private static boolean CheckTerminal(char c){
        if(c >= 65 && c <= 90)
            return false;
        
        return true;
    }
    
    private static boolean CheckStartChar(char c){
        if(c == start)
            return true;
        
        return false;
    }
    
    private static void ShiftAndGoto(){
        for(Vector v : vectors){
            MyCharacter c = v.getValue();
            int columnIndex = getColumnIndex(c.getC());
            if(c.isIsTerminal()){ //*shift
                ArrayList<String> s = (ArrayList<String>) parseTable[v.getSource()+1][columnIndex];
                String string = "s"+ v.getDestination();
                if(s!=null){
                    s.add(string);                   
                }
                else{
                    s = new ArrayList<String>();
                    s.add(string);
                }
                parseTable[v.getSource()+1][columnIndex] = s;               
            }
            else{ //*got to
                parseTable[v.getSource()+1][columnIndex] = v.getDestination();
            }    
        }   
    }
    
    private static void createParseTable(){
        boolean includeEpsilon = false;
        int terminalsSize= terminals.size();
        for(Character c : terminals){
            if(c == '#'){
                includeEpsilon = true;
                terminalsSize --;
                break;
            }
        }
        rowSize = states.size()+1;
        columnSize = terminalsSize+1+1+nonTerminals.size(); //one for begining one for $
        parseTable = new Object[rowSize][columnSize]; //one for $

        for (int j = 0; j < terminals.size() ; j++) {
            if(terminals.get(j) != '#' )
                parseTable[0][j+1] = terminals.get(j);
        }
        parseTable[0][terminalsSize+1] = '$';
        for (int j = 0 ; j < nonTerminals.size() ; j++) {
            parseTable[0][terminalsSize+2+j] = nonTerminals.get(j);
        }
        for (int i = 0; i < states.size(); i++) {
            parseTable[i+1][0] = i;
        }
    }
        
    
    private static void printGrammerTerminalAndNonTerminals(){
        //Debug
        System.out.println();
        for(int i = 0 ; i < grammer.size() ; i++)
            System.out.println(grammer.get(i).toString());         
        for (int i = 0; i < terminals.size(); i++) 
            System.out.println(terminals.get(i));      
        System.out.println();
        for (int i = 0; i < nonTerminals.size(); i++) 
            System.out.println(nonTerminals.get(i));
        
    }
    
    private static void PrintParseTable(){
        for (int i = 0; i < rowSize ; i++) {
            for (int j = 0; j < columnSize; j++) {
                System.out.print(parseTable[i][j] + " , ");
            }
            System.out.println();
        }
    }
}
