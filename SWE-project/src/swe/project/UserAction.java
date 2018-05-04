
package swe.project;;
///////////////////////////////////////////////////////////////////
import java.util.ArrayList;
import java.util.Scanner;
import javax.print.DocFlavor.STRING;
///////////////////////////////////////////////////////////////////
public class UserAction {
	 Scanner Input =new Scanner(System.in);
		
	 private boolean like ;
	 private boolean follow ;
	 private int no_oflikes ;
	 private int no_ofFollower ;
	 private int ID ;
	 private ArrayList <Comment>comments=new ArrayList<Comment>();
     private int counter;
	 //DB : DBcontrol	
////////////////////////////////////////////////////////////////////////////////////////////

	public UserAction() {
			// super();
			//Input = input;
		like = false;
		follow = false;
		no_oflikes = 0;
		no_ofFollower = 0;
		ID = 0;
		counter=0;
	}	 
	
	 public ArrayList<Comment> getComments() {
		return comments;
	}
	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}
	public Scanner getInput() {
		return Input;
	}
	public void setInput(Scanner input) {
		Input = input;
	}
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public boolean isFollow() {
		return follow;
	}
	public void setFollow(boolean follow) {
		this.follow = follow;
	}
	public int getNo_oflikes() {
		return no_oflikes;
	}
	public void setNo_oflikes(int no_oflikes) {
		this.no_oflikes = no_oflikes;
	}
	public int getNo_ofFollower() {
		return no_ofFollower;
	}
	public void setNo_ofFollower(int no_ofFollower) {
		this.no_ofFollower = no_ofFollower;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
/////////////////////////////////////////////////////////////////////////////
	
	public void Like(){
		like=true;
		no_oflikes+=1;
	}
	public void Follow(){
		follow=true;
		no_ofFollower+=1;
	}
	public void Comment(String c){
		Comment C=new Comment();		
		C.setDate();
		C.setID();
		C.setComment(c);
		comments.add(C);
	}
	public void ShowComments(){
		for(int i=0;i<comments.size();i++){
		System.out.println("Dete : "+comments.get(i).getDate());
		System.out.println("comment : "+comments.get(i).getComment());
		System.out.println("ID : "+comments.get(i).getID());
		
		}
	}

}
