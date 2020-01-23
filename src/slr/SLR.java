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
    static boolean firstRule = true;
    public static Rule[] Grammer;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        int GrammerSize = scanner.nextInt();
        String rule ;
        for (int i = 0; i < (GrammerSize); i++) {
            rule = scanner.next();
            SplitRule(rule);
        }
        Grammer = new Rule[GrammerSize];
    }
    private static void SplitRule(String r){
        String[] splited = r.split("->",1);
        boolean isTerminal = false;
        boolean isStartChar = false;
        if(firstRule){
            isTerminal = true;
            isStartChar = true;
            firstRule = false;
        }
        //boolean isTerminal = splited[0] == ''
        Character left = new Character(splited[0].charAt(0),isTerminal,isStartChar);
        ArrayList<Character> right = new ArrayList<>();
        for (int i = 0; i < splited[1].length(); i++) {
            if(splited[1].charAt(i) >= 65 && splited[1].charAt(i) <= 90 )
                isTerminal = true;
            Character a = new Character(splited[1].charAt(i),isTerminal,isStartChar);            
            right.add(a);
        }
        Rule rule = new Rule(left,right);
        
        
        
    }
}
