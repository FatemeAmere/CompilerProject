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
        
    }
    private static void SplitRule(String r){
        ArrayList<Character> rightArray = new ArrayList<>();
        boolean isTerminal;
        boolean isStartChar;
        Character left;
        Character right;
        
        String[] splited = r.split("->",2);
        
        if(isFirstRule){
            start = splited[0].charAt(0);
            left = new Character('@',false,false);
            right = new Character(splited[0].charAt(0),false,true);
            rightArray.add(right);
            Rule firstRule = new Rule(rightArray,left);
            grammer.add(firstRule);
            isFirstRule = false;
        }
        rightArray = new ArrayList<>();
        
        isTerminal = CheckTerminal(splited[0].charAt(0));
        isStartChar = CheckStartChar(splited[0].charAt(0));      
        left = new Character(splited[0].charAt(0),isTerminal,isStartChar);
        
        for (int i = 0; i < splited[1].length(); i++) {
            isTerminal = CheckTerminal(splited[1].charAt(i));
            isStartChar = CheckStartChar(splited[1].charAt(i));  
            Character a = new Character(splited[1].charAt(i),isTerminal,isStartChar);            
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
