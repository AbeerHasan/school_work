/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm_assignment_3;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class Clustring_K_Means {
    
    ArrayList<Double> centroids =new ArrayList<Double>();
   // ArrayList<String> items=new ArrayList<String>();
    ArrayList<String> clusters=new ArrayList<String>();
    ArrayList<String> Oldclusters=new ArrayList<String>();
    
    String filename="";
    int k=0,d=0;
    
    public void setinfo(int K,int D){
        filename="Data.txt";//transactions.txt
        k=K;
        d=D;
        for(int i=0;i<k;i++){
            clusters.add(i+" ");
            Oldclusters.add(i+"");
        }        
    }
    //------------------------------needed functions ------------------------------------
    public ArrayList<String> CutLine(String l){
        String sn="";
        ArrayList<String> temp=new ArrayList<String>();
        for(int i=0;i<l.length();i++){
            if(l.charAt(i)!=' '){
                sn+=l.charAt(i);
                if(i==l.length()-1){
                    temp.add(sn);
                    sn="";
                }
            }else {
                temp.add(sn);
                sn="";
                }
            }
        return temp;
    }
    public ArrayList<String> getitems(int index) throws FileNotFoundException, IOException{
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        ArrayList<String> items=new ArrayList<String>();
        ArrayList<String> temp=new ArrayList<String>();
        //ArrayList<String> items=new ArrayList<String>();
        file.seek(0);
        String l="",sn="";
        int f=0,in=0;
        while((l=file.readLine())!=null){ 
            temp=CutLine(l);
            if(!items.contains(temp.get(index))){
                items.add(temp.get(index));
            }
            temp.clear();
        }
        file.close();
    return items;    
   }
    //- step 1 ----------------------------------------------------------------------------------
    public boolean CheckifContinuaseNumeric(ArrayList<String> items) throws FileNotFoundException, IOException{
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        String l="";
        if(items.size()>d){
          return true;  
        }
        return false;
    }
    //- step 2 -----------------------------------------------------------------------------------
    public ArrayList<Double> set_Centroid() throws IOException{
        int r=0,x=0;
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        ArrayList<String> temp=new ArrayList<String>();
        file.seek(0);
        String l="";
        for(int i=0;i<k;i++){
            r=(int)(Math.random()*(24+1));
            file.seek(0);
            for(int j=0;j<=r;j++){
                if((l=file.readLine())!=null){
                }
            }
            temp=CutLine(l);
            centroids.add(Double.parseDouble(temp.get(1)));
            centroids.add(Double.parseDouble(temp.get(2)));
        }
        file.close();
        System.out.println(centroids);
        return centroids;
    }
    //- step 3 ----------------------------------------------------------------------------
    public void MaketheClusters(ArrayList<Double> cent) throws FileNotFoundException, IOException{
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        ArrayList<String> temp=new ArrayList<String>();
        ArrayList<Double> dis=new ArrayList<Double>();
        //file.seek(0);
        Oldclusters.clear();
        for(int i=0;i<clusters.size();i++){
            Oldclusters.add(clusters.get(i));
        }
        clusters.clear();
        int ind=0;
        double man=0;
        String l="";
        //------------ calculate distances -------------------------------------------
        ArrayList<Integer> index=new ArrayList<Integer>();
        file.seek(0);
        while((l=file.readLine())!=null){
            dis.clear();
            temp=CutLine(l);
            //System.out.println(temp);
            double d1=Double.parseDouble(temp.get(1)),d2=Double.parseDouble(temp.get(2));
            int b=-1;
            for(int i=0;i<cent.size();i+=2){
                if(d1==cent.get(i)&&d2==cent.get(i+1)){
                    b=i;
                }
            }
            //System.out.println("b : "+b);
            if(b==-1){
                for(int i=0;i<cent.size();i+=2){
                    Double d=(d1-cent.get(i));
                    Double dd=(d2-cent.get(i+1));
                    if(d<0){
                        d=d*-1;
                    }
                    if(dd<0){
                        dd=dd*-1;
                    }
                man=d+dd;
                dis.add(man);
                }
            }else{
                clusters.add(b+"");
                clusters.add(d1+"");
                clusters.add(d2+"");
                dis.add(100000.0);
                
            }
            
            //----------- get the most near point -------------------------
            double min=10000000000.0;
            
            int m=0;
            for(int i=0;i<dis.size();i++){
                if(dis.get(i)<min){
                    min=dis.get(i);
                    m=i;
                }
            }
            //System.out.println(temp.get(0)+" "+dis);
            String s="";
            if(m==0){
                s="0";
            }else{
            s=(m+1)+"";
            }
            if(b==-1){
            clusters.add(s);
            clusters.add(temp.get(1));
            clusters.add(temp.get(2));
            }
        }
        file.close();
        System.out.println("Clusters : "+clusters);
        //return clusters;
    }
    //- step 4 -----------------------------------------------------------------------------
    public ArrayList<Double> RecalculateTheCentroid() throws FileNotFoundException, IOException{
        int ind=0;
        String l="";
        ArrayList<String> temp=new ArrayList<String>();
        ArrayList<String> tp=new ArrayList<String>();
        ArrayList<Double> newcent=new ArrayList<Double>();
       // RandomAccessFile file = new RandomAccessFile(filename,"rw");
        //------------ organize clusters array --------------------\\
        for(int i=0;i<clusters.size();i+=3){
            String s=clusters.get(i);
            if(!temp.contains(s)){
                if(!tp.contains(s)){
                tp.add(s);
                }
                for(int j=0;j<clusters.size();j+=3){
                    if(clusters.get(j).contains(s)){
                        temp.add(s);
                        temp.add(clusters.get(j+1));
                        temp.add(clusters.get(j+2));
                    }
                }
            }
        }
       // System.out.println("Organize Clusters : "+clusters);
        
        //----------- calculate the means ------------------------\\
        double x=0.0,y=0.0;
        int f=0;
        for(int i=0;i<tp.size();i++){
            x=0.0;
            y=0.0;
            f=0;
            for(int j=0;j<clusters.size();j+=3){
                if(tp.get(i).contains(clusters.get(j))){
                    x+=Double.parseDouble(clusters.get(j+1));
                    y+=Double.parseDouble(clusters.get(j+2));
                    f++;
                }
            }
            double mx=x/(double)f;
            double my=y/(double)f;
            
            newcent.add(mx);
            newcent.add(my);
        }
        
        System.out.println("new centroid : "+newcent);
        return newcent;
    }
    //- step 5 check the stoping condetion ------------------------------------------------
    public boolean stopingCondetion(ArrayList<String> newclus,ArrayList<String> oldclust){
        ArrayList<String> temp=new ArrayList<String>();
        ArrayList<String> tp=new ArrayList<String>();
        ArrayList<String> Ntemp=new ArrayList<String>();
        ArrayList<String> Ntp=new ArrayList<String>();
        
        //------------ organize new clusters array --------------------\\
        for(int i=0;i<newclus.size();i+=3){
            String s=newclus.get(i);
            if(!Ntemp.contains(s)){
                if(!Ntp.contains(s)){
                Ntp.add(s);
                }
                for(int j=0;j<newclus.size();j+=3){
                    if(newclus.get(j).equals(s)){
                        Ntemp.add(s);
                        Ntemp.add(newclus.get(j+1));
                        Ntemp.add(newclus.get(j+2));
                    }
                }
            }
        }
        //------------ organize old clusters array --------------------\\
        for(int i=0;i<oldclust.size();i+=3){
            String s=oldclust.get(i);
            if(!temp.contains(s)){
                if(!tp.contains(s)){
                tp.add(s);
                }
                for(int j=0;j<oldclust.size();j+=3){
                    if(oldclust.get(j).equals(s)){
                        temp.add(s);
                        temp.add(oldclust.get(j+1));
                        temp.add(oldclust.get(j+2));
                    }
                }
            }
        }
        //---------- cut the clusters and compare them -----------------\\
        ArrayList<String> point=new ArrayList<String>();
        ArrayList<String> newpoint=new ArrayList<String>();
        ArrayList<Boolean> chang=new ArrayList<Boolean>();
        String s="";
        
        for(int i=0;i<Ntp.size();i++){
            int countN=0,count=0;
            s=Ntp.get(i);
            for(int j=0;j<newclus.size();j+=3){
                if(s.equals(newclus.get(j))){
                   countN++; 
                }
            }
            
            for(int j=0;j<oldclust.size();j+=3){
                if(s.equals(oldclust.get(j))){
                   count++; 
                }
            }
            
            if(count==countN){
                chang.add(true);
            }else{
                chang.add(false);
            }
        }
        if(chang.contains(false)){
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
}
