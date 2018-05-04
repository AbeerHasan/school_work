/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbd_assignment_1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Functional_Dependencies {
    
    private ArrayList<String> CandidateKeys=new ArrayList<String>();
    private ArrayList<String> RHS =new ArrayList<String>();
    private ArrayList<String> LHS = new ArrayList<String>();
    private String PrimaryKey="";
    public String filename="";
    
    public boolean Functional_Dependency_between(String att1,String att2) throws SQLException, ClassNotFoundException{
        
        PreparedStatement stmt = null;
        ResultSet RS=null;
        String sql="SELECT "+att1+","+att2+" FROM ass1.r;";
        stmt = DB_Connection.getPreparedStatement(sql);
        RS = stmt.executeQuery(sql);
        
        ArrayList<String> a1 =new ArrayList<String>();
        ArrayList<String> a2 =new ArrayList<String>();
        
        boolean b=true;
        while(RS.next()){
            String r1=RS.getString(1),r2=RS.getString(2);
            int i=a1.indexOf(r1);
            if(i!=-1){
                if(a2.get(i).equals(r2)){
                    b=true;
                }else { b=false; break; }
            }else{
                a1.add(r1);
                a2.add(r2);
            }
        }
        
        stmt.close();
        RS.close();
        
        if(b==true){
            return true;
        }
        return false;
    }
   
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String Get_Closure(ArrayList<String> LHS,ArrayList<String> RHS,String key){
        boolean b=true,bb=false;
        String closure=key;
        int i=0;
        while(i<LHS.size()){
            for(int j=0;j<LHS.get(i).length();j++){
                if(!closure.contains(LHS.get(i).charAt(j)+"")){
                    b=false;
                }
            }
            if(b==true){
                for(int r=0;r<RHS.get(i).length();r++){
                    if(!closure.contains(RHS.get(i).charAt(r)+"")){
                        closure+=RHS.get(i).charAt(r)+"";
                        bb=true;
                    }
                }
                if(bb==true){
                    i=-1;
                }
            }
            b=true;
            bb=false;
            i++;
        }
        return closure;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void Get_Minimal_Coverage(){
        //Right Reduction --------------------------------------------------
        ArrayList<String> rhs =new ArrayList<String>();
        ArrayList<String> lhs =new ArrayList<String>();
        for(int i=0;i<RHS.size();i++){
            if(RHS.get(i).length()>1){
                for(int j=0;j<RHS.get(i).length();j++){
                    rhs.add(RHS.get(i).charAt(j)+"");
                    lhs.add(LHS.get(i));
                    }    
                }else{
                    rhs.add(RHS.get(i));
                    lhs.add(LHS.get(i));
            }
        }
        //Lift Reduction ---------------------------------------------------
        //first round
        for(int i=0;i<lhs.size();i++){
            if(lhs.get(i).length()>1){
                for(int j=0;j<lhs.get(i).length();j++){
                    if(Get_Closure(lhs,rhs,lhs.get(i).charAt(j)+"").contains(rhs.get(i))){
                        lhs.set(i,lhs.get(i).charAt(j)+"");
                    }
                }
            }        
        }
        //second round
        String comp="";
        for(int i=0;i<lhs.size();i++){
            if(lhs.get(i).length()>2){
                for(int j=0;j<lhs.get(i).length()-1;j++){
                    for(int h=j+1;h<lhs.get(i).length();h++){
                        comp+=(lhs.get(i).charAt(j)+"")+(lhs.get(i).charAt(h)+"");
                        if(Get_Closure(lhs,rhs,comp).contains(rhs.get(i))){
                            lhs.set(i,comp);
                        }
                    comp="";
                    }
                }
            }
        } 
        //System.out.println("RHS "+rhs);
        //System.out.println("LHS "+lhs);
        
//Non Redundancy -----------------------------------------------------
        int x=0;
        //Remove direct redundancy
        for(int i=0;i<lhs.size();i++){
            for(int j=0;j<lhs.size();j++){
                if(j!=i && lhs.get(i).length()==lhs.get(j).length()){
                    for(int h=0;h<lhs.get(i).length();h++){
                        if(lhs.get(j).contains(lhs.get(i).charAt(h)+"")){
                            x++;
                        }
                    }
                    if(x==lhs.get(i).length()){
                        if(rhs.get(i).equals(rhs.get(j))){ 
                            lhs.remove(j);
                            rhs.remove(j);
                        }
                    }
                    x=0;
                }
            }
        }
        //Remove in direct redundancy
        ArrayList<String> r =new ArrayList<String>();
        ArrayList<String> l =new ArrayList<String>();
        for(int i=0;i<lhs.size();i++){
            //remove the wanted FD from F
            for(int j=0;j<rhs.size();j++){
                if(i!=j){
                    l.add(lhs.get(j));
                    r.add(rhs.get(j));
                }
            }
            //check for redundant FDs an remove it 
            if(Get_Closure(l,r,lhs.get(i)).contains(rhs.get(i))){
                lhs.remove(i);
                rhs.remove(i);
                        
            }
            l.clear();
            r.clear();
        }
        for(int k=0;k<rhs.size();k++){
            System.out.print(lhs.get(k)+" --> "+rhs.get(k)+" , ");
        }
        System.out.println(" ");
    }
     ArrayList<String> R =new ArrayList<String>();
    public void Get_Keys(ArrayList<String> r) throws FileNotFoundException, IOException{
        R=r;
        RandomAccessFile F = new RandomAccessFile(filename+".pub", "rw");
        ArrayList<String> Distincet_RHS =new ArrayList<String>();
        String l=" ",exp="";
        //Separate expressions 
        if((l=F.readLine())!=null){
            int i=0;
            while(i<l.length()){
                if(l.charAt(i)!='-'&&l.charAt(i)!=','){
                    exp+=l.charAt(i);
                    if(i+1==l.length()){
                        RHS.add(exp);
                    }
                }else if(l.charAt(i)=='-'){
                    LHS.add(exp);
                    exp="";
                }else if(l.charAt(i)==','){
                    RHS.add(exp);
                    exp="";
                }
                i++;
            }
        }
        //Separate Distincet LHS
        for(int i=0;i<RHS.size();i++){
            for(int j=0;j<RHS.get(i).length();j++){
                String ch=RHS.get(i).charAt(j)+"";
                if(!Distincet_RHS.contains(ch)){
                    Distincet_RHS.add(ch);
                }
            }
        }
        //Check the mempers of R which isn't in the RHS
        String key="";
        for(int i=0;i<r.size();i++){
            if(!Distincet_RHS.contains(r.get(i))){
                key+=r.get(i);
            }
        }
        //Get Candidate keys 
        if(Get_Closure(LHS,RHS,key).length()==r.size()&&!key.equals("")){
            CandidateKeys.add(key);
        }else if(Get_Closure(LHS,RHS,key).length()!=r.size()||key.equals("")){
            ArrayList<String> Not_cand=new ArrayList<String>();
            String test="";
            //get candidate key for first round 
            for(int i=0;i<r.size();i++){
                test=key;
                if(!test.contains(r.get(i))){
                    test+=r.get(i);
                    if(Get_Closure(LHS,RHS,test).length()==r.size()){
                        CandidateKeys.add(test);
                    }else {
                        Not_cand.add(r.get(i));
                    }
                }
            }
            //get candidate key for seconde round
            if(Not_cand.size()>1){
                test=key;
                for(int i=0;i<Not_cand.size()-1;i++){
                    for(int j=i+1;j<Not_cand.size();j++){
                        test+=Not_cand.get(i)+Not_cand.get(j);
                        if(Get_Closure(LHS,RHS,test).length()==r.size()){
                            CandidateKeys.add(test);
                        }
                        test=key;
                    }
                }    
            }
        }
        //get primary key 
        int p=-1;
        if(CandidateKeys.size()==1){
            PrimaryKey=CandidateKeys.get(0);
        }else {
            int min=1000;
            for(int i=0;i<CandidateKeys.size();i++){
                if(CandidateKeys.get(i).length()<min){
                    min=CandidateKeys.get(i).length();
                    p=i;
                }
            }
            PrimaryKey=CandidateKeys.get(p);
        }
        
        
        System.out.println("Candidate Keys : "+CandidateKeys);
        System.out.println("The chosen Primary Key :"+PrimaryKey);
    }
    public int check_Cand(String hs){
        int x=0;
        for(int i=0;i<CandidateKeys.size();i++){
            x=0;
            for(int j=0;j<hs.length();j++){
                if(CandidateKeys.get(i).contains(hs.charAt(j)+"")){
                   x+=1;
                }
            }
            if(x==hs.length()&&hs.length()==CandidateKeys.get(i).length()){
                return 1;
            }else if(x==hs.length()&&hs.length()<CandidateKeys.get(i).length()){
                return -1;
            }
        }
        return 0;
    }
    ArrayList<String> PrimeAtts=new ArrayList<String>();
    public int Check_normal_form() throws FileNotFoundException, IOException{
        //Get prime attriputes
        
        for(int i=0;i<CandidateKeys.size();i++){
            for(int j=0;j<CandidateKeys.get(i).length();j++){
                if(!PrimeAtts.contains(CandidateKeys.get(i).charAt(j)+"")){
                    PrimeAtts.add(CandidateKeys.get(i).charAt(j)+"");
                }
            }
        }
        
        //check 2NF
        int Normal_Form=2;
        for(int i=0;i<RHS.size();i++){
            for(int j=0;j<RHS.get(i).length();j++){
                if(!PrimeAtts.contains(RHS.get(i).charAt(j)+"")&&check_Cand(LHS.get(i))==-1){
                    Normal_Form=1;
                }
            }
        }
        
        if(Normal_Form==1){
            System.out.println("R is in the 1NF ");
        }else {
            //check 3NF
            Normal_Form=3;
            for(int i=0;i<RHS.size();i++){
                for(int j=0;j<RHS.get(i).length();j++){
                    if(!PrimeAtts.contains(RHS.get(i).charAt(j)+"")&&check_Cand(LHS.get(i))==0){
                        Normal_Form=2;
                    } 
                }
            }
            
            if(Normal_Form==2){
                System.out.println("R is in the 2NF ");
            }else {
                //check BCNF
                Normal_Form=4;
                for(int i=0;i<RHS.size();i++){
                    for(int j=0;j<RHS.get(i).length();j++){
                        if(PrimeAtts.contains(RHS.get(i).charAt(j)+"")&&check_Cand(LHS.get(i))!=1){
                            Normal_Form=3;
                        } 
                    }
                }
                if(Normal_Form==3){
                    System.out.println("R is in the 3NF ");
                }else System.out.println("R is in the BCNF ");
            }
        
        }
        //------------------------ write R on File --------------------------------
        RandomAccessFile F = new RandomAccessFile(Normal_Form+".pub", "rw");
        F.seek(0);
        String table="";
        for(int i=0;i<R.size();i++){
            table+=R.get(i);
        }
        F.writeChars(table);
        F.close();
        //-------------------------------------------------------------------------
        return Normal_Form;
    }
    public void R_In_BCNF(int NF) throws FileNotFoundException, IOException{
        ArrayList<String> nonPrime =new ArrayList<String>();//Dependent
        ArrayList<String> Prime =new ArrayList<String>();//Determinant
        ArrayList<String> np =new ArrayList<String>();//Determinant
        //2NF ------------------------------------------------------------------------------------------------------------
        if(NF==1){
            //prepration
            for(int i=0;i<RHS.size();i++){
                for(int j=0;j<RHS.get(i).length();j++){
                    if(!PrimeAtts.contains(RHS.get(i).charAt(j)+"")&&check_Cand(LHS.get(i))==-1){
                        if(!nonPrime.contains(RHS.get(i).charAt(j)+"")){
                            nonPrime.add(RHS.get(i).charAt(j)+"");
                            Prime.add(LHS.get(i));
                        }
                    }
                }
            }
            System.out.println("Prime "+Prime);
            System.out.println("nonPrime"+nonPrime);
        
            while(nonPrime.size()>0){
            ArrayList<String> table=new ArrayList<String>();
            for(int i=0;i<nonPrime.size();i++){
                if(table.size()==0){
                    if(!nonPrime.get(i).equals("*")){
                    table.add(Prime.get(i));
                    table.add(nonPrime.get(i));
                    R.remove(nonPrime.get(i));
                    nonPrime.set(i,"*");
                    Prime.set(i,"*");}
                }else if(table.get(0).equals(Prime.get(i))&&!table.contains(nonPrime.get(i))&&!nonPrime.get(i).equals("*")){
                    table.add(nonPrime.get(i));
                    R.remove(nonPrime.get(i));
                    nonPrime.set(i,"*");
                    Prime.set(i,"*");
                }
            }
            nonPrime.remove("*");
            Prime.remove("*");
            NF=2;
            RandomAccessFile F = new RandomAccessFile(NF+".pub", "rw");
            long l=F.length();
            F.seek(l);
            if(!table.isEmpty()){
            String t="";
            for(int i=0;i<table.size();i++){
                t+=table.get(i);
            }
            t+="|";
            F.writeChars(t);
            F.close();
            }
        }
        RandomAccessFile F = new RandomAccessFile(NF+".pub", "rw");
        long l=F.length();
        F.seek(l);
        String table="";
        if(R.size()>1){
        for(int i=0;i<R.size();i++){
            table+=R.get(i);
        }
        F.writeChars(table);
        F.close();
        }
        }
        //3NF ------------------------------------------------------------------------------------------------------------
        if(NF==2){
            RandomAccessFile F = new RandomAccessFile(NF+".pub", "rw");
            String l=F.readLine(),tt="";
            int count=0;
            System.out.println("2NF : ");
            for(int i=0;i<l.length();i++){
                if(l.charAt(i)!=' '&&l.charAt(i)!='|'){
                    tt+=(l.charAt(i)+"");
                    if(i==l.length()-1){
                        count+=1;
                        System.out.println("R"+count+" { "+tt+" }");
                    }
                }else if(l.charAt(i)=='|'){
                    count+=1;
                    System.out.println("R"+count+" { "+tt+" }");
                    tt="";
                }
            }
            //--------------------------------------------------------------
            R.clear();
            
            for(int i=0;i<l.length();i++){
                if(l.charAt(i)!=' '&&l.charAt(i)!='|'){
                    R.add(l.charAt(i)+"");
                    if(i==l.length()-1){
                        for(int j=0;j<R.size();j++){
                            for(int x=0;x<RHS.size();x++){
                                for(int y=0;y<RHS.get(x).length();y++){
                                    if(!PrimeAtts.contains(RHS.get(x).charAt(y)+"")&&check_Cand(LHS.get(x))==0&&R.get(j).equals(RHS.get(x).charAt(y)+"")){
                                        if(!nonPrime.contains(RHS.get(x).charAt(y)+"")&&R.contains(LHS.get(x))){
                                            nonPrime.add(RHS.get(x).charAt(y)+"");
                                            np.add(LHS.get(x));
                                        }
                                    }
                                }
                            }
                        }
                    //System.out.println("non prime "+nonPrime);
                    //System.out.println("np "+np);
                    while(nonPrime.size()>0){
                        ArrayList<String> table=new ArrayList<String>();
                        for(int x=0;x<nonPrime.size();x++){
                            if(table.size()==0){
                                if(!nonPrime.get(x).equals("*")){
                                table.add(np.get(x));
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");}
                            }else if(table.get(0).equals(Prime.get(i))&&!table.contains(nonPrime.get(x))&&!nonPrime.get(x).equals("*")){
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");
                            }
                        }
                        nonPrime.remove("*");
                        np.remove("*");
                        
                        RandomAccessFile Ff = new RandomAccessFile(3+".pub", "rw");
                        long s=Ff.length();
                        Ff.seek(s);
                        String t="";
                        for(int k=0;k<table.size();k++){
                            t+=table.get(k);
                        }
                        t+="|";
                        Ff.writeChars(t);
                        Ff.close();
                        
                    }
                    RandomAccessFile Ff = new RandomAccessFile(3+".pub", "rw");
                    long s=Ff.length();
                    Ff.seek(s);
                    String table="";
                    if(R.size()>1){
                        for(int x=0;x<R.size();x++){
                            table+=R.get(x);
                        }
                        table+="|";
                        Ff.writeChars(table);
                        Ff.close();
                    } 
                    R.clear();
                    }
                }else if(l.charAt(i)=='|'){
                    for(int j=0;j<R.size();j++){
                        for(int x=0;x<RHS.size();x++){
                            for(int y=0;y<RHS.get(x).length();y++){
                                if(!PrimeAtts.contains(RHS.get(x).charAt(y)+"")&&check_Cand(LHS.get(x))==0&&R.get(j).equals(RHS.get(x).charAt(y)+"")){
                                    if(!nonPrime.contains(RHS.get(x).charAt(y)+"")&&R.contains(LHS.get(x))){
                                        nonPrime.add(RHS.get(x).charAt(y)+"");
                                        np.add(LHS.get(x));
                                    }
                                }
                            }
                        }
                    }
                    //System.out.println("non prime "+nonPrime);
                    //System.out.println("np "+np);
                    while(nonPrime.size()>0){
                        ArrayList<String> table=new ArrayList<String>();
                        for(int x=0;x<nonPrime.size();x++){
                            if(table.size()==0){
                                if(!nonPrime.get(x).equals("*")){
                                table.add(np.get(x));
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");}
                            }else if(table.get(0).equals(Prime.get(i))&&!table.contains(nonPrime.get(x))&&!nonPrime.get(x).equals("*")){
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");
                            }
                        }
                        nonPrime.remove("*");
                        np.remove("*");
                        
                        RandomAccessFile Ff = new RandomAccessFile(3+".pub", "rw");
                        long s=Ff.length();
                        Ff.seek(s);
                        String t="";
                        for(int k=0;k<table.size();k++){
                            t+=table.get(k);
                        }
                        t+="|";
                        Ff.writeChars(t);
                        Ff.close();
                        
                    }
                    RandomAccessFile Ff = new RandomAccessFile(3+".pub", "rw");
                    long s=Ff.length();
                    Ff.seek(s);
                    String table="";
                    if(R.size()>1){
                        for(int x=0;x<R.size();x++){
                            table+=R.get(x);
                        }
                        table+="|";
                        Ff.writeChars(table);
                        Ff.close();
                    } 
                    R.clear();
                }
            }
            NF=3;
        }
        //BCNF ------------------------------------------------------------------------------------------------------------
        if(NF==3){
            RandomAccessFile F = new RandomAccessFile(NF+".pub", "rw");
            String l=F.readLine(),tt="";
            int count=0;
            System.out.println("3NF : ");
            for(int i=0;i<l.length();i++){
                if(l.charAt(i)!=' '&&l.charAt(i)!='|'){
                    tt+=(l.charAt(i)+"");
                    if(i==l.length()-1){
                        count+=1;
                        System.out.println("R"+count+" { "+tt+" }");
                    }
                }else if(l.charAt(i)=='|'){
                    count+=1;
                    System.out.println("R"+count+" { "+tt+" }");
                    tt=""; 
                }
            }
            //--------------------------------------------------------------
            R.clear();
            
            for(int i=0;i<l.length();i++){
                if(l.charAt(i)!=' '&&l.charAt(i)!='|'){
                    R.add(l.charAt(i)+"");
                    if(i==l.length()-1){
                        for(int j=0;j<R.size();j++){
                            for(int x=0;x<RHS.size();x++){
                                for(int y=0;y<RHS.get(x).length();y++){
                                    if(PrimeAtts.contains(RHS.get(x).charAt(y)+"")&&check_Cand(LHS.get(x))!=1&&R.get(j).equals(RHS.get(x).charAt(y)+"")){
                                        if(!nonPrime.contains(RHS.get(x).charAt(y)+"")&&R.contains(LHS.get(x))){
                                            nonPrime.add(RHS.get(x).charAt(y)+"");
                                            np.add(LHS.get(x));
                                        }
                                    }
                                }
                            }
                        }
                    //System.out.println("non prime "+nonPrime);
                    //System.out.println("np "+np);
                    while(nonPrime.size()>0){
                        ArrayList<String> table=new ArrayList<String>();
                        for(int x=0;x<nonPrime.size();x++){
                            if(table.size()==0){
                                if(!nonPrime.get(x).equals("*")){
                                table.add(np.get(x));
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");}
                            }else if(table.get(0).equals(Prime.get(i))&&!table.contains(nonPrime.get(x))&&!nonPrime.get(x).equals("*")){
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");
                            }
                        }
                        nonPrime.remove("*");
                        np.remove("*");
                        
                        RandomAccessFile Ff = new RandomAccessFile(4+".pub", "rw");
                        long s=Ff.length();
                        Ff.seek(s);
                        String t="";
                        for(int k=0;k<table.size();k++){
                            t+=table.get(k);
                        }
                        t+="|";
                        Ff.writeChars(t);
                        Ff.close();
                        
                    }
                    RandomAccessFile Ff = new RandomAccessFile(4+".pub", "rw");
                    long s=Ff.length();
                    Ff.seek(s);
                    String table="";
                    if(R.size()>1){
                        for(int x=0;x<R.size();x++){
                            table+=R.get(x);
                        }
                        table+="|";
                        Ff.writeChars(table);
                        Ff.close();
                    } 
                    R.clear();
                    }
                }else if(l.charAt(i)=='|'){
                    for(int j=0;j<R.size();j++){
                        for(int x=0;x<RHS.size();x++){
                            for(int y=0;y<RHS.get(x).length();y++){
                                if(PrimeAtts.contains(RHS.get(x).charAt(y)+"")&&check_Cand(LHS.get(x))!=1&&R.get(j).equals(RHS.get(x).charAt(y)+"")){
                                    if(!nonPrime.contains(RHS.get(x).charAt(y)+"")&&R.contains(LHS.get(x))){
                                        nonPrime.add(RHS.get(x).charAt(y)+"");
                                        np.add(LHS.get(x));
                                    }
                                }
                            }
                        }
                    }
                    //System.out.println("non prime "+nonPrime);
                    //System.out.println("np "+np);
                    while(nonPrime.size()>0){
                        ArrayList<String> table=new ArrayList<String>();
                        for(int x=0;x<nonPrime.size();x++){
                            if(table.size()==0){
                                if(!nonPrime.get(x).equals("*")){
                                table.add(np.get(x));
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");}
                            }else if(table.get(0).equals(Prime.get(i))&&!table.contains(nonPrime.get(x))&&!nonPrime.get(x).equals("*")){
                                table.add(nonPrime.get(x));
                                R.remove(nonPrime.get(x));
                                nonPrime.set(x,"*");
                                np.set(x,"*");
                            }
                        }
                        nonPrime.remove("*");
                        np.remove("*");
                        
                        RandomAccessFile Ff = new RandomAccessFile(4+".pub", "rw");
                        long s=Ff.length();
                        Ff.seek(s);
                        String t="";
                        for(int k=0;k<table.size();k++){
                            t+=table.get(k);
                        }
                        t+="|";
                        Ff.writeChars(t);
                        Ff.close();
                        
                    }
                    RandomAccessFile Ff = new RandomAccessFile(4+".pub", "rw");
                    long s=Ff.length();
                    Ff.seek(s);
                    String table="";
                    if(R.size()>1){
                        for(int x=0;x<R.size();x++){
                            table+=R.get(x);
                        }
                        table+="|";
                        Ff.writeChars(table);
                        Ff.close();
                    } 
                    R.clear();
                }
            }
            NF=4;
        }
        RandomAccessFile F = new RandomAccessFile(4+".pub", "rw");
            String l=F.readLine(),tt="";
            int count=0;
            System.out.println("BCNF : ");
            for(int i=0;i<l.length();i++){
                if(l.charAt(i)!=' '&&l.charAt(i)!='|'){
                    tt+=(l.charAt(i)+"");
                    if(i==l.length()-1){
                        count+=1;
                        System.out.println("R"+count+" { "+tt+" }");
                        tt="";
                    }
                }else if(l.charAt(i)=='|'){
                    count+=1;
                    System.out.println("R"+count+" { "+tt+" }");
                    tt="";
                }
            }
            //--------------------------------------------------------------
            
        // END ------------------------------------------------------------------------------------------------------------    
            
    }
}