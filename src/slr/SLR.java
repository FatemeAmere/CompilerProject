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
        System.out.println();
        for(int i = 1 ; i < grammer.size() ; i++){
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
        
        if(!nonTerminals.contains(splited[0].charAt(0)));
            nonTerminals.add(splited[0].charAt(0));
            
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
}
