package swe.project;;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Check_in {
	
	private place pls ;
	private String date ;
    private String statusToWrite ;
    private UserAction action ;
    private int ID ;
	private static int count =0;
	
    ////////////////////////////////////////////////////////////////////////////
	
	public Check_in() {
		//super();
		date ="00/00/2015";
		statusToWrite = "  ";
		ID = 0;
		count+=1;
	}
	
    public String getDate() {
		return date;
	}
	public void setDate() {
	    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    Date dat = new Date();
		String d =dateFormat.format(dat);
		date=d;
	}
	public String getStatusToWrite() {
		return statusToWrite;
	}

	public UserAction getAction() {
		return action;
	}
	public void setAction(UserAction action) {
		this.action = action;
	}
	public int getID() {
		return ID;
	}
	public void setID() {
		ID =count;
	}
    
    ///////////////////////////////////////////////////////////////////////////
    public void set_DAte_Place(place p){
    	pls=p;
    	setDate();
    }
    
	public void WriteStatus(String statusToWrite) {
		this.statusToWrite = statusToWrite;
	}
	
	public void Show_check_In(){
		System.out.println(getDate());
		System.out.println("Status : "+getStatusToWrite());
		System.out.println("I'm in "+pls.getName()+" now :)");
		System.out.println("Rate : "+pls.getRate());
	}
	/*public void MakeAction(){
		
	}*/
}

