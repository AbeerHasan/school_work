/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

/**
 *
 * @author DELL
 */
import java.util.Scanner;
import java.util.ArrayList;
public class Main {

	   public static void main(String[] args) 
	    {
		   Scanner cin =new Scanner(System.in);
	       ///////////////////////////////////////////////////////////////
		   ArrayList <String> sentence =new  ArrayList<String>();
		   LzW L=new LzW();
		   String Char=" ";
		   //////////////////////////////////////////////////////////////
		   System.out.println("Enter your sentence :");
		   while(!Char.equals("*")){   
			      Char=cin.next();
		          if(Char.equals("*")){
		          break;
		          }else{
		        	  sentence.add(Char);
		        //  L.make_dictionary();
		          }
		   }
		   //////////////////////////////////////////////////////////////
		//   System.out.print("dictionary:");
		  // L.make_dictionary();
                   //L.display_dictionary();
		  // System.out.println("\n");
		  
                   System.out.print("1-Compression :");
		   L.compress(sentence);
		   System.out.println("\n");
		  
		   System.out.print("Indexs :");
		   L.display_indexs();
		   System.out.println("\n");

                   System.out.print("dictionary:");
		   L.display_dictionary();
		   System.out.println("\n");
		             
		   System.out.println("3-Size Comparison :");
		   L.compare_size(sentence.size());
		   System.out.println("\n");
		   
		   System.out.print("2-Decompression :");
		   L.decompress();
		   System.out.println("\n");

	    }
/* a b a a b a b b a a b a a b a a a a b a b b b b b b b b *
           65  66  65  128  128  129  131  134  130  129  66  138  139  138

           */
}
