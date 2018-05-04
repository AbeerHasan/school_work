/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe.project;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;


public class User {
    private String Email;
    private String PassWord;
    private String FirstName;
    private String LastName;
    private String Country;
    private String Gender;
    private String MobilePhone;
    private String UserName;
    ArrayList<User> FriendList= new ArrayList<User>();
    private String Status;
  public ArrayList <place>placeList=new ArrayList<place>();
  ArrayList<Notification> notification=new ArrayList<Notification> ();
    private String SpecialCode;
    private String UserID;
    private String GUIName;
    Scanner input = new Scanner(System.in);

    public User() {
    }

    public User(String Email, String PassWord, String FirstName, String LastName, String Country, String Gender, String MobilePhone, String UserName, String Status, String SpecialCode, String UserID, String GUIName) {
        this.Email = Email;
        this.PassWord = PassWord;
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.Country = Country;
        this.Gender = Gender;
        this.MobilePhone = MobilePhone;
        this.UserName = UserName;
        this.Status = Status;
        this.SpecialCode = SpecialCode;
        this.UserID = UserID;
        this.GUIName = GUIName;
    }

    public void setCountry(String Country) {
        this.Country = Country;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public void setFriendList(ArrayList<User> FriendList) {
        this.FriendList = FriendList;
    }

    public void setGUIName(String GUIName) {
        this.GUIName = GUIName;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public void setNotification(ArrayList<Notification> notification) {
        this.notification = notification;
    }
    

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }

    public void setPlaceList(ArrayList<place> placeList) {
        this.placeList = placeList;
    }

    public void setSpecialCode(String SpecialCode) {
        this.SpecialCode = SpecialCode;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getCountry() {
        return Country;
    }

    public String getEmail() {
        return Email;
    }

    public String getFirstName() {
        return FirstName;
    }

    public ArrayList<User> getFriendList() {
        return FriendList;
    }

    public String getGUIName() {
        return GUIName;
    }

    public String getGender() {
        return Gender;
    }

    public String getLastName() {
        return LastName;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public ArrayList<Notification> getNotification() {
        return notification;
    }

    public String getPassWord() {
        return PassWord;
    }

    public ArrayList<place> getPlaceList() {
        return placeList;
    }

    public String getSpecialCode() {
        return SpecialCode;
    }

    public String getStatus() {
        return Status;
    }

    public String getUserID() {
        return UserID;
    }

    public String getUserName() {
        return UserName;
    }


    public void SetAllInfo() //elfunc d set bs wla add user?!
    {
        User u= new User();
        String name ,pass ,fname ,lname ,email ,country, gender, id, mob ,status ;
        System.out.println("Enter User Name");
        u.setUserName(name=input.next());
        System.out.println("Enter User First Name");
        u.setFirstName(fname=input.next());
        System.out.println("Enter User Last Name");
        u.setLastName(lname=input.next());
        System.out.println("Enter User Email");
        u.setEmail(email=input.next());
        System.out.println("Enter User Password");
        u.setPassWord(pass=input.next());
        System.out.println("Enter User Country");
        u.setCountry(country=input.next());
        System.out.println("Enter User Gender");
        u.setGender(gender=input.next());
        System.out.println("Enter User ID");
        u.setUserID(id=input.next());
        System.out.println("Enter User Mobile Phone");
        u.setMobilePhone(mob=input.next());
        System.out.println("Enter User Status");
        u.setStatus(status=input.next());
        
        //add user fldatabase
    }
    
    public void GetAllInfo(String email)//btget elinfo 3la asas a elmfrood ad5l elemail msln
    {
        //elmafrood asearch fldatabase blname w ageeb elba2y
        //hna brdo msh 3rfa mn elfriend list wla eldatabase
        
        User u= new User();
        System.out.println("User Name"+ u.getUserName());
        System.out.println("User First Name" + u.getFirstName());       
        System.out.println("User Last Name"+ u.getLastName());
        System.out.println("User Email"+ u.getEmail());
        System.out.println("User Password"+ u.getPassWord());
        System.out.println("User Country"+ u.getCountry());
        System.out.println("User Gender"+ u.getGender());
        System.out.println("User ID"+ u.getUserID());
        System.out.println("User Mobile Phone"+ u.getMobilePhone());
        System.out.println("User Status"+ u.getStatus());
                
        //=========hna b2a lw flfriend list
        User friend= new User();
        
        boolean found=false;
        
        for (int i=0 ;i<FriendList.size() ;i++)
        {
          if(FriendList.get(i).getEmail()==email)
          {
            found =true;
            System.out.println("User Name"+ u.getUserName());
            System.out.println("User First Name" + u.getFirstName());       
            System.out.println("User Last Name"+ u.getLastName());
            System.out.println("User Email"+ u.getEmail());
            System.out.println("User Password"+ u.getPassWord());
            System.out.println("User Country"+ u.getCountry());
            System.out.println("User Gender"+ u.getGender());
            System.out.println("User ID"+ u.getUserID());
            System.out.println("User Mobile Phone"+ u.getMobilePhone());
            System.out.println("User Status"+ u.getStatus());
          } 
        }
       //le msh mwgoud b2a
        if(!found){
         System.out.println("This user is not in your friend list or email in incorrect");
        }
                
    }
    
    public void UpdateInfo(String email)
    {
        //brdo elmafrood aserach fldatabase w a3melo delete msln w a3ml add tane w hb2a 3mlt update
        //bs hwa msh flfiend list sa7?
        
        User u= new User();
        String name ,pass ,fname ,lname ,mail ,country ,gender ,id ,mob ,status;
        System.out.println("Enter User Name");
        u.setUserName(name=input.next());
        System.out.println("Enter User First Name");
        u.setFirstName(fname=input.next());
        System.out.println("Enter User Last Name");
        u.setLastName(lname=input.next());
        System.out.println("Enter User Email");
        u.setEmail(mail=input.next());
        System.out.println("Enter User Password");
        u.setPassWord(pass=input.next());
        System.out.println("Enter User Country");
        u.setCountry(country=input.next());
        System.out.println("Enter User Gender");
        u.setGender(gender=input.next());
        System.out.println("Enter User ID");
        u.setUserID(id=input.next());
        System.out.println("Enter User Mobile Phone");
        u.setMobilePhone(mob=input.next());
        System.out.println("Enter User Status");
        u.setStatus(status=input.next());
        
        //add user fldatabase

    }
    
    public void Showfiend(String email)
    {
        //brdo lazem asearch w aget elinfo bt3to
                
        User u= new User();
        boolean found=false;
        
        for (int i=0 ;i<FriendList.size() ;i++)
        {
          if(FriendList.get(i).getEmail()==email)
          {
            found =true;
            System.out.println("User Name"+ u.getUserName());
            System.out.println("User First Name" + u.getFirstName());       
            System.out.println("User Last Name"+ u.getLastName());
            System.out.println("User Email"+ u.getEmail());
            System.out.println("User Password"+ u.getPassWord());
            System.out.println("User Country"+ u.getCountry());
            System.out.println("User Gender"+ u.getGender());
            System.out.println("User ID"+ u.getUserID());
            System.out.println("User Mobile Phone"+ u.getMobilePhone());
            System.out.println("User Status"+ u.getStatus());
          } 
        }
       //le msh mwgoud b2a
        if(!found){
         System.out.println("This user is not in your friend list or email in incorrect");
        }
          
    }
    
    public void Searchfriend(String mail)
    {
        //asearch fldatabase brdo
        boolean found=false; 
        for (int i=0 ;i<FriendList.size() ;i++)
        {
          if(FriendList.get(i).getEmail()==mail)
          {
            found =true;
              System.out.println("User is in your Friend List");
          } 
        }
       //le msh mwgoud b2a
        if(!found){
         System.out.println("This user is not in your friend list or email in incorrect");
        }
    }

    
   
}
