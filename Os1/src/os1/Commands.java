/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os1;

/**
 *
 * @author Marwa mohammed
 */
  import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
    
  
public class Commands
{

    /**
     * @param args
     * @throws IOException
     */
    public static String deafult="E:/";
    public static String path=deafult ;

    //----------------------mkdir--------------------------
    
    public void mkdir(String n)
    {
        if(n.equals("mkdir"))
        {
            System.out.println("wrong command you Should to name the file");
        }
        else
        {
            String name = n.substring(6);

            String in = path+name;
            File F = new File(in);
            boolean check =false;
            if(!F.exists())
            {
                try
                {
                    check = F.mkdir();
                    if(check)
                    {
                        System.out.println("file is created!");
                    }
                    else
                    {
                        System.out.println("Failed to create file!");
                    }
                }
                catch(SecurityException S)
                {
                    System.out.println("Error While Creating mkdir in Java" + S);
                }
            }
            else
            {
                System.out.println("File has been already created");
            }
        }
    }
    //--------------------rmkdir----------------------------------------
    public void rmkdir(String n)
    {
        if(n.equals("rmkdir"))
            System.out.println("wrong command..you have to name the file");

        else
        {
            String name =n.substring(7);
            String order =path+name;
            File F=new File(order);
            boolean check =false;
            if(F.exists())
            {
                try 
                {
                    check = F.delete();
                    if(check)
                    {
                        System.out.println("File deleted");
                    }
                    else
                    {
                        System.out.println("Faild to delete File ");
                    }
                }
                catch(SecurityException S)
                {
                    System.out.println("Error While Deleteing File " + S);
                }
            }
            else
            {
                System.out.println("File not exist");
            }
        }
    }

    //-------------------------------------pwd----------------
    public void pwd()
    {

        System.out.println(path);
    }
    //------------------------------date----------------
    public void date()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

    //-------------------------------- ls --------------------------------

    public void ls(String pa)
    {

        if(pa.equals("ls"))
        {
            File F=new File(path);
            if(F.exists())
            {
                String Files[] =F.list();
                for(String ou : Files)
                {
                    File fi =new File (path+ou);
                    if(!fi.isHidden())
                    {
                        System.out.println(ou);
                    }

                }
            }
        }
        else
        {
            pa=pa.substring(3);
            File F=new File(pa);
            if(F.exists())
            {
                String Files[] =F.list();
                for(String ou : Files)
                {
                    File fi =new File (pa+ou);
                    if(!fi.isHidden())
                    {
                        System.out.println(ou);
                    }

                }
            }
        }
    }
//-----------------------Clear--------------------------
    public void clear()
    {
        for(int i=0 ; i<300 ; i++)
        {
            System.out.println();
        }
    }

//-------------------------------Cd -----------------------------
    public void cd(String pa)
    {
        if(pa.equals("cd"))
        {

            System.out.println("Command Wrong : you should enter Right command");

            path =deafult;


        }
        else
        {
            pa=pa.substring(3);
            path =pa;
        }

    }

//---------------------------------rm----------------------------
    public void rm(String name)
    {
        if(name.equals("rm"))
        {
            System.out.println("wrong command..you have to name the file");
        }
        else
        {
            name = name.substring(3);
            String order = path+name;
            try
            {
                File F =new File(order);
                boolean check =false;
                check = F.delete();
                if(check)
                    System.out.println("Deleted");
                else
                    System.out.println("deleted");

            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

    }

    //---------------------------cat ----------------
    public void cat(String name) throws IOException
    {
        if(name.equals("cat"))
        {
            System.out.println("wrong command..you have to name the file");
        }
        else {
            name =name.substring(4);
            if(name.indexOf(" ")==-1){
            BufferedReader b=null;
            String in=path+name;
            String lines;
            try {
                b=new BufferedReader(new FileReader(in));
                while((lines=b.readLine())!=null)
                {
                    System.out.println(lines);
                }
            }
            catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(b!=null)
            {
                b.close();
            }
        }
            else 
            {
            	int xx =name.indexOf(" ");
            	
            	String a1=name.substring(0,xx);
            	String a2=name.substring(xx+1);
            	//--------
            	   BufferedReader b=null;
                   String in=path+a1;
                   String lines;
                try {

                    while((lines=b.readLine())!=null)
                    {
                        System.out.println(lines);
                    }
                }
                catch (FileNotFoundException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(b!=null)
                {
                    b.close();
                }
                
                 in=path+a2;
                b=null ;
                lines="";
                try {
                    b=new BufferedReader(new FileReader(in));
                    while((lines=b.readLine())!=null)
                    {
                        System.out.println(lines);
                    }
                }
                catch (FileNotFoundException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if(b!=null)
                {
                    b.close();
                }
                
            	
            }
        }
    }

    //---------------------------------------mv--------------------------------------
    public void mv(String F ,String b) throws IOException
    {
        InputStream i = null;
        OutputStream o = null;
        try {
            File file1 =new File(path+F);
            File file2 =new File(path+b);
            i = new FileInputStream(file1);
            o = new FileOutputStream(file2);
            byte[] buffer = new byte[2000];
            int length;
            while ((length = i.read(buffer)) > 0)
            {
                o.write(buffer, 0, length);
            }
            i.close();
            o.close();
            file1.delete();

            System.out.println("File is moved successful !");
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //--------------------------cp------------------------------
    public void cp(String F ,String b) throws IOException
    {
        InputStream i = null;
        OutputStream o = null;
        try {
            File file1 =new File(path+F);
            File file2 =new File(path+b);
            i = new FileInputStream(file1);
            o = new FileOutputStream(file2);
            byte[] buffer = new byte[2000];
            int length;
            while ((length = i.read(buffer)) > 0)
            {
                o.write(buffer, 0, length);
            }
            i.close();
            o.close();
            
            System.out.println("File is copied successful !");
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //-----------------------less--------------------
   
//--------------------------------- help ------------------
    public void help()
    {
        System.out.println("args : List all command arguments");
        System.out.println("date :  Current date/time");
        System.out.println("exit  : Stop all");
        System.out.println("clear: clear the screen of some other terminal.");
        System.out.println("cd: command changes the current directory to another one.");
        System.out.println("ls: list each given file or directory name");
        System.out.println("pwd: Display current user directory");
        System.out.println("cp: If the last argument names an existing directory, cp copies each other given file into a file with the same name in that directory");
        System.out.println("mv: moves each other given file into a file with the same name in that directory");
        System.out.println("rm: removes each specified file");
        System.out.println("mkdir:creates a directory with each given name");
        System.out.println("rmdir: removes each given empty directory");
        System.out.println("cat: Concatenate files and print on the standard output.");
        System.out.println("more: Let us display and scroll down the output in one direction only");
        System.out.println("less: Like more but more enhanced. It support scroll forward and backward ");
    }
//-------------------------------------args ----------------------------------------
    public void args(){
   	 String commandName;
   	 Scanner in = new Scanner (System.in);
   	 System.out.println("enter Command Name .");
   	 commandName = in.nextLine();
   	 if(commandName.equals("mkdir"))System.out.println("args for this command : DirAndFileName");
   	 else if(commandName .equals("cd") )System.out.println("args for this command : File Name");
   	 else if(commandName .equals("cat") )System.out.println("args for this command : File Name");
   	 else if(commandName .equals("more"))System.out.println("args for this command : File Name");
   	 else if(commandName .equals("less"))System.out.println("args for this command : File Name");
   	 else if(commandName .equals("cp"))System.out.println("args for this command : src File & dest File");
   	 else if(commandName .equals("rmdir"))System.out.println("args for this command : file Name");
   	 else if(commandName .equals( "grep"))System.out.println("args for this command : file Name & text");
   	 else if(commandName .equals("mv"))System.out.println("there are no args for this command.");
   	 else if(commandName .equals( "Date"))System.out.println("there are no args for this command.");
   	 else if(commandName .equals("clear"))System.out.println("there are no args for this command.");
   	 else if(commandName .equals("pwd"))System.out.println("there are no args for this command.");
   	 else if(commandName .equals("ls"))System.out.println("there are no args for this command.");
   	 else System.out.println("error => that is not verify a command");
    }

    public void less(String args){
  	  String choise = "";
  	  Scanner input = new Scanner(System.in);
  	
  	          try {
  	              FileReader fileReader = new FileReader(args);
  	              BufferedReader in = new BufferedReader(fileReader);
  	              String line;
  	              line = in.readLine();
  		          System.out.println(line);
  	              
  	              while(true){ 
  	            	  choise = input.next();
  		              if ((choise =="Y") || (choise !="y") ){
  		            	 if((line = in.readLine())!= null)
  		            		 System.out.println(line);
  		              }
  		              else break; 
  	              }        
  	          } catch (FileNotFoundException ex) {
  	              System.out.println(args+", file not found.");
  	          }
  	          catch (IOException ex) {
  	              System.out.println(args+", input/output error.");
  	          }
  	  
    }
    //-------------------Find -----------
    public void Find(String name)
    {
     File Folder = new File(path);
     
     File[] listofFiles=Folder.listFiles();
     for(int i=0;i<listofFiles.length;i++)
     {
    	 String filename =listofFiles[i].getName();
    	 if(filename.contains(name))
    	 {
    		 System.out.println(filename);
    	 }
     }
    	
    }
    	//------------------------------grep ------------------

    public void more(String args){
  	  String choise = "";
  	  Scanner input = new Scanner(System.in);
  	
  	          try {
  	              FileReader fileReader = new FileReader(args);
  	              BufferedReader in = new BufferedReader(fileReader);
  	              String line;
  	              for(int i=0; i<10 && (line = in.readLine())!= null; i++){
  		                      System.out.println(line);
  	              }
  	              while(true){ 
  	            	  choise = input.next();
  		              if ((choise =="Y") || (choise !="y") ){
  		            	  for(int i=0; i<10 && (line = in.readLine())!= null; i++){
  		                      System.out.println(line);
  		            	  }
  		              }
  		              else break; 
  	              }        
  	          } catch (FileNotFoundException ex) {
  	              System.out.println(args+", file not found.");
  	          }
  	          catch (IOException ex) {
  	              System.out.println(args+", input/output error.");
  	          }
  	  
    }
    public void grep(String name,String f) 
    {
    	File file =new File(path+f);
    	
    	try
    	{
    		Scanner y = new Scanner(file);
    		int line=0 ;
    		while(y.hasNextLine())
    		{
    			String l = y.nextLine();
    			//System.out.println(l);
    			line++;
    			if(l.contains(name))
    			{
    				System.out.println("Founded in line "+ line);
    			}
    		}
    		
    	}catch(Exception e)
    	{
    		e.getMessage();
    	}
    	
    }
    

    public void splits(String []cmms){
  	  for(int i=0;i<cmms.length; i++){

  		  if(cmms[i] .equals("mkdir") ){
  			  String dirName;
  			  Scanner sc = new Scanner(System.in);
  			  dirName = sc.next();
  			  this.mkdir(dirName);
  		  }
  			
  		  else if(cmms[i].equals("cd")){
  			  String args;
  			  Scanner sc = new Scanner(System.in);
  			  args = sc.next();
  			  this.cd(args);
  		  }
  			 
  		  /*else if(cmms[i] == "cat"){
  			  String []arr;
  			  Scanner sc = new Scanner(System.in);
  			  this.cat(arr);
  		  }*/
  			 
  		  else if(cmms[i] == "more"){
  			  String args;
  			  Scanner sc = new Scanner(System.in);
  			  args = sc.next();
  			  this.more(args);
  		  }
  			 
  		  else if(cmms[i] == "less"){
  			  String args ;
  			  Scanner sc = new Scanner(System.in);
  			  args = sc.next();
  			  this.less(args);
  			  
  		  }
  			 
  		  else if(cmms[i] == "cp"){
  			  String path1,path2;
  			  Scanner sc = new Scanner(System.in);
  			  path1 = sc.next();
  			  path2= sc.next();
  			  File src = new File (path1);
  			  File dest = new File (path2);
  			  /*try {
  				this.cp(src, dest);
  			} catch (IOException e) {
  				// TODO Auto-generated catch block
  				e.printStackTrace();
  			}*/
  		  }
  			
  		  else if(cmms[i] == "rmdir"){
  			  String path;
  			  Scanner sc = new Scanner(System.in);
  			  path = sc.next();
  			  File file = new File(path);
  			  this.rmkdir("");
  		  }
  			 
  		  else if(cmms[i] .equals("grep") ){
  			  this.grep("F:\\OS\\txt3.txt","hazem");
  		  }
  		  
  			 else if(cmms[i]  .equals("mv")){
  				 try {
					this.mv("","");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
  			 }
  		  
  			 else if(cmms[i] .equals("date") ){
  				 this.date();
  			 }
  		  
  			 else if(cmms[i].equals("clear")){
  				 this.clear();
  			 }
  		  
  			 else if(cmms[i] .equals("pwd")){
  				 this.pwd();
  			 }
  		  
  			 else if(cmms[i] == "ls"){
  				 this.ls("D:\\");
  			 }
  		  
  			 else System.out.println("error => that is not verify a command");
  	  }
    }
    
    
    public static void main(String[] args) throws IOException
    {

        String x;
        Scanner input =new Scanner(System.in);
        Commands xx =new Commands();

        while(true)
        {

            System.out.print("$ User_Home : //  "+path+" ");
            //System.out.println();
            x=input.nextLine();
            if(x.contains("mkdir")&&x.charAt(0)!='r')
            {
                xx.mkdir(x);
                System.out.println();
          }
            else  if(x.contains("rmkdir"))
            {
                xx.rmkdir(x);
                System.out.println();
            }
            else if(x.contains("cd"))
            {
                xx.cd(x);
                System.out.println();
            }
            else if (x.contains("splits")){
            	String s , arr[];
            	
            	Scanner in = new Scanner(System.in);
            	s = in.nextLine();
            	arr = s.split(";");
            	xx.splits(arr);
            }
            else if(x.contains("ls"))
            {
                xx.ls(x);
                System.out.println();
            }
            else if(x.contains("pwd"))
            {
                xx.pwd();
                System.out.println();
            }
            else if(x.contains("clear"))
            {
                xx.clear();
                System.out.println();
            }
            else if(x.contains("date"))
            {
                xx.date();
                System.out.println();
            }
            else if(x.contains("args"))
            {
                xx.args();
                System.out.println();
            }
            else if(x.contains("help"))
            {
                xx.help();
                System.out.println();
            }
            else if(x.contains("rm"))
            {
                xx.rm(x);
                System.out.println();
            }
            else if(x.contains("cat"))
            {
                xx.cat(x);
                System.out.println();
            }
            
            else if(x.contains("mv"))
            {
                if(x.equals("mv"))
                {
                    System.out.println("Wrong Command .....");
                }
                {
                    String q =x.substring(3);
                    int xn =q.indexOf(" ");
                    String s1 =q.substring(0, xn);
                    String s2 =q.substring(xn+1);
                    xx.mv(s1, s2);
                }
            }
            else if(x.contains("cp"))
            {
                if(x.equals("cp"))
                {
                    System.out.println("Wrong Command .....");
                }
                else
                {
                    String q =x.substring(3);
                    int xn =q.indexOf(" ");
                    String s1 =q.substring(0, xn);
                    String s2 =q.substring(xn+1);
                    xx.cp(s1, s2);
                }
            }
            else if(x.contains("find"))
            {
            	if(x.equals("find"))
            	{
            		System.out.println("Wrong Command ... you shout enter file name and dir");
            	}
            	else 
            	{
            		String q = x.substring(5);
            		int kk =q.indexOf(" ");
            		String s1 =q.substring(0,kk);
            		String s2 =q.substring(kk+1);
            		System.out.println(s2+"  "+s1);
            		path =s2; 
            		xx.Find(s1);
            		path =deafult;
            		
            	}
            	
            }
            
            else if (x.contains("grep"))
            {
            	if(x.equals("grep"))
            	{
            		System.out.println("Wrong Command ... you shout write text and filename ");
            	}
            	else 
            	{
            		String q = x.substring(5);
            		int kk =q.indexOf(" ");
            		String s1 =q.substring(0,kk);
            		String s2 =q.substring(kk+1);
            		//System.out.println(s2+"  "+s1);
            		
            		xx.grep(s1, s2);
            		
            		
            	}
            }
            else if (x.contains("more"))
            {
               // System.exit(0);
            	xx.more("D:\\atef.txt");
            }
            
            else if (x.contains("exit"))
            {
                System.exit(0);
            }
            else if (x.contains("less"))
            {
                //System.exit(0);
            	xx.less("D:\\atef.txt");
            }
           
   x="";
        }
    }


}

