/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lzw;

import java.util.ArrayList;

public class LzW {

	private String p,c;
	private ArrayList <String>dictionary=new  ArrayList<String>();
	private ArrayList <Integer>index=new  ArrayList<Integer>();
	private int count;
	
	public LzW(){
		p="";
		c="";
		count=0;
	}
///////////////////////////////////////////////////////////////////
	public int search_i(String ch){
		for (int i=0;i<dictionary.size();i++){
			if(dictionary.get(i).equals(ch)){
				return i;
			}
		}
		return -1;
	}
////////////////////////////////////////////////////////////////////	
	public void make_dictionary(){
		for(int i=0;i<128;i++){
			String s=Character.toString((char)i);
			dictionary.add(s);
		}
	}
//////////////////////////////////////////////////////////////////
	public void display_dictionary(){
		for(int j=0;j<dictionary.size();j++){
			System.out.print(" '"+dictionary.get(j)+"' ");
		}
	}
	public void display_indexs(){
		for(int j=0;j<index.size();j++){
			System.out.print(" ["+index.get(j)+"] ");
		}
	}
///////////////////////////////////////////////////////////////////
	public void compress(ArrayList <String> sentence){
	    dictionary.clear();
            make_dictionary();
		
                int i;
		for(i=0;i<sentence.size();i++){
	       c=sentence.get(i);
           if(search_i(p+c)!=-1){
        	  p=p+c;
	       }else {
	          System.out.print(p+" , ");
	          count+=1;
		      dictionary.add(p+c);
		      index.add(search_i(p));
			  p=c;
	       }
		}
        if(i==sentence.size()){
     	   System.out.print(p+" .");
     	   count+=1;
     	   index.add(search_i(p));
        }
	}
///////////////////////////////////////////////////////////////////
	public void decompress(){
	   dictionary.clear();
	   make_dictionary();

	   String cc,pc="";
	   int i=0,x;
	  
	   x=index.get(i);
	   cc=Character.toString((char)x);
	   System.out.print(cc);
	   
	   for(i=1;i<index.size();i++){
		   pc=cc;
		   x=index.get(i);
		   cc=dictionary.get(x);
		   if(search_i(cc)!=-1){
			   System.out.print(cc);
			   p=pc;
			   c=cc.substring(0,1);
			String s=p+c;
			   dictionary.add(s);
		   }else {
			   p=pc;
			   c=pc.substring(0,1);
			   System.out.print(p+c);
			   dictionary.add(p+c);
			   
		   }
	   }

	   System.out.print("\n");
	}
//////////////////////////////////////////////////////////////////
	public void compare_size(int s){
		System.out.println("Origenal size is : "+s*8+" .");
		System.out.println("Compressed size is : "+count*8+" .");
	}
//////////////////////////////////////////////////////////////////*/
	//End of class
}
