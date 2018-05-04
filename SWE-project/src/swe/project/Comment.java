package swe.project;
//////////////////Time & Date Import/////////////////////////
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
////////////////////////////////////////////////////////////
public class Comment {

	private int ID;
	private String date,comment;
	private boolean like;
	private int no_oflikes ;
	private static int count =0;
////////////////////////////////////////////////////////////////////////////////

	public Comment() {
    	ID = 0;
	    date ="00/00/0000";
    	like = false;
	    no_oflikes = 0;
        comment=" no comments ";
        count+=1;
	}
	
	public int getID() {
		return ID;
	}
	public void setID() {
		ID =count;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public boolean isLike() {
		return like;
	}
	public void setLike(boolean like) {
		this.like = like;
	}
	public int getNo_oflikes() {
		return no_oflikes;
	}
	public void setNo_oflikes(int no_oflikes) {
		this.no_oflikes = no_oflikes;
	} 
}
