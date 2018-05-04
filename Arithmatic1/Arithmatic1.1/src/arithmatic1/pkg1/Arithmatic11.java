/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmatic1.pkg1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author EgyHacker
 */
public class Arithmatic11 {

    /**
     * @param args the command line arguments
     */
     public static double Range() {
          ArrayList<Character> letters = new ArrayList<>();
          ArrayList<Double> prop = new ArrayList<>();
          ArrayList<Double> Range = new ArrayList<>();
          ArrayList<Character> Sort = new ArrayList<>();
          ArrayList<Integer> index = new ArrayList<>();
          ArrayList<Character> Chars = new ArrayList<>();
          letters.add('A');
          letters.add('C');
          letters.add('B');
          letters.add('A');
          prop.add(0.8);
          prop.add(0.02);
          prop.add(0.18);
          
          double max=0;
          double min=0;
          String l="";
          for(int i=0;i<letters.size();i++){l+=letters.get(i);}
          l.toCharArray();
         
          //System.out.println(l);
          Sort=letters;
          Collections.sort(Sort);
          
           
          //calculations
         Range.add(min);
         for(int i=0;i<prop.size();i++)
         {
         max=max+prop.get(i);
         Range.add(max);
         }
         //Creating A,B,C
         Chars=letters;
         Sort=letters;
         
         Collections.sort(Sort);
         LinkedHashSet <Character> lhs = new LinkedHashSet<Character>();
         lhs.addAll(Sort);
         Sort.clear();
         Sort.addAll(lhs);
         char c;
         int index1=0;
         int count=0;
         for(int i=0;i<l.length()-1;i++)
         {
             c=l.charAt(i);
             System.out.println("letter = "+c);
             for(int j=0;j<Sort.size();j++)
             {
                 if(c==Sort.get(j)){
                     index1=count;
                     index.add(index1);
                 }
                 else{count++;}
                 
             }
             System.out.println("index is "+index1);
             
             count=0;
         }
         
         
         
         for(int i=0;i<index.size();i++){
         max=Range.get(index.get(i)+1);
         
         min=Range.get(index.get(i));
         Range.remove(0);
         Range.add(0, min);
         Range.remove(Range.size()-1);
         Range.add(Range.size(), max);
         double temp=0;
         temp=min;
         min=min+(max-min)*0.8; 
         max=temp+(max-temp)*0.82;
         Range.remove(Range.size()/2-1);
         Range.remove(Range.size()/2);
         Range.add(1,min);
         Range.add(2,max);
         System.out.println(Range);
         }
        
         double rand= Math.random()*min;
         return rand;
         
    }

    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Random Number is :"+ Range());
    }
    
}
