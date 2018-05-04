import java.util.ArrayList; 
import java.util.Scanner;
public class Lz77 {

	ArrayList<Tag>List=new ArrayList<>();
	public void compress(){
		String text,t1,t2 ;
		Scanner in =new Scanner(System.in);
		System.out.println("Enter text :");
		text=in.next();
		Tag T=new Tag();
		T.pointer=0; 
		T.length=0;
		T.nextchar=text.charAt(0);
		List.add(T);
		
		for(int i=1;i<text.length();i++){
			t1=text.substring(0,i);//3shan yrga3 ykaren beno w ben 2elle 2blo  
			t2=Character.toString(text.charAt(i));//byshel el 7arf mo2akatan l7ad ma yshof elle b3do
			if(t1.contains(t2)==false){
				T= new Tag();
				T.pointer=0;
				T.length=0;
				T.nextchar=text.charAt(i);
				List.add(T);
			}else {
				while (t1.contains(t2)==true){
				if(i!=text.length()-1==true){//3shan 2t2aked en el gomla m5lsetsh 
				t2+=Character.toString(text.charAt(i+1));//3shan lw rege3t w l2et el7arf w l2et en 
				//el arf elle b3do kman mwgod wra elle owa bydawar 3leh 
				i++;
				}else {//lw wesel l2a5er 7arf
					T= new Tag();
					T.pointer=t1.length()-t1.lastIndexOf(t2) ;
					T.length=t2.length();
					T.nextchar='0';
					List.add(T);
					return;
	
				}
				}
				T= new Tag();
				T.pointer=t1.length()-t1.lastIndexOf(t2.substring(0, t2.length()-1));///kalb
				T.length=t2.length()-1;
				T.nextchar=t2.charAt(t2.length()-1);
				List.add(T);   			
    		}
		}
   	}
	
	public void display(){
		for(int i=0;i<List.size();i++){
			System.out.println("< "+List.get(i).pointer+" , "+List.get(i).length+" , "+List.get(i).nextchar+" >");
			System.out.print("\n");
		}
	}
	public void decombress(){
		String text=" ";
		for(int i=0;i<List.size();i++){
			if(List.get(i).pointer==0&&List.get(i).length==0){
				text+=List.get(i).nextchar;
			}else {
				int x=text.length()-List.get(i).pointer ;
				text+=text.substring(x,x+List.get(i).length);
				if(List.get(i).nextchar!='0'){
					text +=List.get(i).nextchar;
				}
			}
			
		}System.out.print(text);
	}
}
