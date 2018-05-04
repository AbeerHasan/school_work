
import com.sun.org.apache.xalan.internal.lib.ExsltMath;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class BayesianClassifier {
    static String filename;
    int classindex,itemsetsize;
    ArrayList<String> bayesian=new ArrayList<String>();
    ArrayList<String> k_nearest=new ArrayList<String>();
    ///car.training.txt ,Car.validation.features.txt ,Car.validation.truth.txt 
    
    //---------------------setters & getters-----------------------------------\\
    void setfileName(){
        filename="car.training.txt";
        classindex=0;
        itemsetsize=0;
    }
        //--------------------------- display transactions --------------------------\\
    
    public void GetAllDataSet() throws Exception {
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        String l="";
        int i=1;
        //------------------------------------------------------
        System.out.println("row 1 2 3 4 5 6 class_label"); 
        while((l=file.readLine())!=null) 
        {
            System.out.println(i+"   "+ l);
            i++;
            itemsetsize++;
        getlinesize(l);
        }
        file.close();
    }
    public void getlinesize(String l){
        String sn="";
        ArrayList<String> temp=new ArrayList<String>();
        for(int i=0;i<l.length();i++){
            if(l.charAt(i)!=','){
                sn+=l.charAt(i);
            //----- for the last item -------\\
            if(i==l.length()-1){
                    temp.add(sn);
                    sn="";
                }
            }else {
                temp.add(sn);
                sn="";
                }
            }
        classindex=temp.size()-1;
    }
    public ArrayList<String>  CutLine(String l){
        String sn="";
        ArrayList<String> temp=new ArrayList<String>();
        for(int i=0;i<l.length();i++){
            if(l.charAt(i)!=','){
                sn+=l.charAt(i);
            //----- for the last item -------\\
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
    public int Getcategoryfrequence(String c ,int index) throws Exception {
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        String l="";
        int f=0;
        while((l=file.readLine())!=null) 
        {
            // ----------- cut the compinetion -------------\\
            ArrayList<String> temp=new ArrayList<String>();
            temp=CutLine(l);   
            if(temp.get(index).equals(c)){
            f++;
            }
        }
        file.close();
        return f;
    }
    
    public int Getcategoryfrequence_withclass(String a ,int ia,String c) throws Exception {
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        String l="";
        int f=0;
        while((l=file.readLine())!=null) 
        {
            // ----------- cut the compinetion -------------\\
                String sn="";
                ArrayList<String> temp=new ArrayList<String>();
                temp=CutLine(l);
            if(temp.get(ia).equals(a)&&temp.get(temp.size()-1).equals(c)){
            f++;
            }
        }
        file.close();
        return f;
    }
    public ArrayList<String> getitems() throws FileNotFoundException, IOException{
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        ///////////////////////////////////////
        ArrayList<String> temp=new ArrayList<String>();
        ArrayList<String> items=new ArrayList<String>();
        file.seek(0);
        String l="",sn="";
        int f=0,in=0;
        while((l=file.readLine())!=null){ 
            temp=CutLine(l);
        //---------------------add distencet item---------------------
            if(!items.contains(temp.get(temp.size()-1))){
                items.add(temp.get(temp.size()-1));
            }
            temp.clear();
        }
    return items;    
   }
    public ArrayList<Double> classLabel_calculateprob() throws Exception,FileNotFoundException, IOException {
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        String l="";
        int prob=1;
        //----------- calculat the prob of the class label categories-------------------
        ArrayList<String> items=new ArrayList<String>();
        ArrayList<Double> classprobs=new ArrayList<Double>();
        items=getitems();
        
        for(int j=0;j<items.size();j++){
            classprobs.add((double)Getcategoryfrequence(items.get(j),classindex)/(double)itemsetsize); 
        }
        file.close();
        return classprobs;
    }
    public void Bayesian(String l) throws Exception,FileNotFoundException, IOException {
        double prob=1,x=1;
        //----------- calculat the prob of the class label categories-------------------
        ArrayList<String> temp=new ArrayList<String>();
        ArrayList<String> items=new ArrayList<String>();
        ArrayList<Double> classprobs=new ArrayList<Double>();
        ArrayList<Double> results=new ArrayList<Double>();
        
        temp=CutLine(l);
        items=getitems();//get the distenct class labels in the origenal data set 
        classprobs=classLabel_calculateprob();
        
        for(int i=0;i<items.size();i++){
            for(int j=0;j<temp.size();j++){
                x=(double)Getcategoryfrequence_withclass(temp.get(j),j,items.get(i))/(double)Getcategoryfrequence(items.get(i),classindex);
                if(x==0){// if the class frequency =0 there will be miss calculation so assigne it to verry small value
                    x=0.0000000001;
                }
                prob*=x;
            }
            prob*=classprobs.get(i);
            results.add(prob);
        }  
        double max=0.0;
        int index=0;
        String classlabel="";

       for(int i=0;i<results.size();i++){
           if(results.get(i)>max){
               max=results.get(i);
               index=i;
           }   
       }
       classlabel=items.get(index);
       System.out.println("class label "+classlabel);
       //---------------------- store the result in array list -------------
       bayesian.add(classlabel);
    }   
    
    public void K_nearest(String l,int k) throws Exception,FileNotFoundException, IOException {
        //----------------------------------------------------------------------------------------
        ArrayList<String> temp=new ArrayList<String>();
        ArrayList<String> tm=new ArrayList<String>();
        ArrayList<String> items=new ArrayList<String>();
        ArrayList<Double> distance=new ArrayList<Double>();
        ArrayList<Integer> index=new ArrayList<Integer>();
        
        items=getitems();
        temp=CutLine(l);
        //----- read a tuple from the file and calculate the distance between it and the given tuple -----------------------------
        RandomAccessFile file = new RandomAccessFile(filename,"rw");
        file.seek(0);
        
        String t="";
        double prob=0;
        int x=0;
        while((t=file.readLine())!=null) 
        {
            tm=CutLine(t);
            for(int i=0;i<temp.size();i++){
                prob+=ExsltMath.power(Double.parseDouble(tm.get(i))-Double.parseDouble(temp.get(i)),2);
                //System.out.println("sqrt "+prob);
            }
            prob=sqrt(prob);
            distance.add(prob);
            index.add(x);//index={0,1,2,3,4,5,6,7,8,9,.....,no of tubles in the data set}
            x++;
        }
        //-------------------------------------------------------------
        double max=0.0;
        int ind=0;
        //----------- sort distances becouse we want the k of tubles with the smallest distances ----------------------------
        for(int i=0;i<distance.size();i++){
            for(int j=i+1;j<distance.size();j++){
                if(distance.get(i)>distance.get(j)){
                    max=distance.get(i);
                    distance.set(i,distance.get(j));
                    distance.set(j,max);
                    //-------------to save the index of the tuple in order to know its place in the file to get its class lable ------------------
                    ind=index.get(i);
                    index.set(i,index.get(j));
                    index.set(j,ind);
                }    
            }
        }
        //----------- get the classlabels of the k-nearest tuples from the file -------------------------------
        tm.clear();
        temp.clear();
        file.seek(0);
        for(int i=0;i<k;i++){
            ind=index.get(i);
            t="";
            file.seek(0);
            for(int j=0;j<=ind;j++){
                if((t=file.readLine())!=null){
                }
            }
            tm=CutLine(t); 
            //System.out.println("tuble "+t);
            temp.add(tm.get(tm.size()-1));
            //System.out.println("class label "+temp);
        }
        file.close();
        //--------------------- get the most frequent class labels in the k-nearest tubles ---------------
        int freq=0;
        index.clear();//calculate the frequency of each class lable 
        for(int i=0;i<items.size();i++){
            for(int j=0;j<temp.size();j++){
                if(temp.get(j).equals(items.get(i))){
                    freq++;
                }
            }
            index.add(freq);
        }
        
        String classlabel="";
        int m=-1;// get the class lable with the max freqency 
        for(int i=0;i<items.size();i++){
            if(index.get(i)>m){
                m=index.get(i);
                ind=i;
            }
        }
        classlabel=items.get(ind);
        System.out.println("classlabel is "+classlabel);
        //------------- store the results to an arraylist ------------------------------
        k_nearest.add(classlabel);
    }    
    
    public void calculateAccuracy(ArrayList<String> classefire) throws FileNotFoundException, IOException{
        RandomAccessFile f = new RandomAccessFile("Car.validation.truth.txt","rw");
        int righ=0,i=0;
        double a=0.0;
        String l="";
        while((l=f.readLine())!=null){
            if(l.equals(classefire.get(i))){
                righ++;
            }
            i++;
           // if(i==10){
             //   break;
            //}
        }
        System.out.println(" no of right class lables : "+righ);
        System.out.println("classefir Size : )"+classefire.size());
        
        a=(double)((double)righ/(double)classefire.size())*100;
        System.out.println("Accuracy = "+a+"%");
        f.close();
    } 
}
