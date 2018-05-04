package swe.project;;

import java.util.Scanner;

public class Main {
	 public static void main(String[] args){
			
	      Scanner Input =new Scanner(System.in);
          
	      String c=" ";
	      UserAction action =new UserAction(); 
          
	      for(int i=0;i<2;i++){
          System.out.println("Enter comment : ");
          c=Input.nextLine();
          action.Comment(c);
	      }
          action.ShowComments();
	 }
}