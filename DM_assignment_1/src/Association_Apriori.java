
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.RandomAccessFile;
import java.util.LinkedHashSet;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DELL
 */
public class Association_Apriori {
    
    private int min_sup;
    private int min_conf;
    static String filename;
    private ArrayList<String> item=new ArrayList<String>();
    private ArrayList<Integer> freq=new ArrayList<Integer>();
        
    //--------------------------- Setters & Getters --------------------------\\
    public void Set_MinSup(int s){
        min_sup=s;
    }
    public void Set_MinConf(int c){
        min_conf=c;
    }
    public void set_filename(String fn){
        filename=fn;
    }
    public String Get_filename(){
        return filename;
    }
    public int Get_MinSup(){
        return min_sup;
    }
    public int Get_MinConf(){
        return min_sup;
    }
    //--------------------------- display transactions --------------------------\\
    
    public void GetAllTransactions() throws Exception {
        RandomAccessFile file = new RandomAccessFile(filename+".pub", "rw");
        file.seek(0);
        String l="";
        int i=1;
        while((l=file.readLine())!=null) 
        {
            System.out.println("T_"+i+"{ "+l+" }");
            i++;
        }
        file.close();
    }
   //----------------------------- calculate freq -------------------------------------\\
    public int getfrequencs(String e) throws FileNotFoundException, IOException{
        RandomAccessFile file = new RandomAccessFile(filename+".pub", "rw");
        file.seek(0);
        String l="",sn="";
        int f=0,in=0;
        while((l=file.readLine())!=null) 
        {
            if(!e.contains(" ")){
                if(l.contains(e)){
                    f++; 
                }
            }else {
                // ----------- cut the compinetion -------------\\
                ArrayList<String> temp=new ArrayList<String>();
                for(int i=0;i<e.length();i++){
                    if(e.charAt(i)!=' '){
                    sn+=e.charAt(i);
                    //----- for the last item -------\\
                    if(i==e.length()-1&&!temp.contains(sn)){
                        temp.add(sn);
                        sn="";
                    }
                    }else {
                        if(!temp.contains(sn)){
                            temp.add(sn);
                        }
                        sn="";
                    }
                }
                // ------------search for the compinetion and calculate the freq -----\\
                ArrayList<Boolean> found=new ArrayList<Boolean>();
                for(int i=0;i<temp.size();i++){
                    if(!l.contains(temp.get(i))){
                        found.add(false);
                    }
                }
                if(!found.contains(false)){
                   f++; 
                }
            }    
        }
        file.close();
        return f;
    }
    public ArrayList<String> getitems() throws FileNotFoundException, IOException{
        RandomAccessFile file = new RandomAccessFile(filename+".pub", "rw");
        file.seek(0);
        ///////////////////////////////////////
        ArrayList<String> items=new ArrayList<String>();
        file.seek(0);
        String l="",sn="";
        int f=0,in=0;
        while((l=file.readLine())!=null){ 
            for(int i=0;i<l.length();i++){
            if(l.charAt(i)!=' '){
                sn+=l.charAt(i);
                if(i==l.length()-1){
                    if(!items.contains(sn)){
                        items.add(sn);
                    }
                    sn="";
                }
            }else {
                if(items.size()==0||!items.contains(sn)){
                    items.add(sn);
                }
                sn="";
            }}
            l="";
        }
    return items;    
   }
   public ArrayList<Integer> makefreqlist(ArrayList<String> items) throws IOException{
        int f=0;
        freq.clear();
        for(int i=0;i<items.size();i++){
           f=getfrequencs(items.get(i));
           freq.add(f);
        }
        return freq;
   } 
   //-----------------------------------------------------------------------------------------------------\\
   public ArrayList<String> elemenateInfreqItems(ArrayList<String> items,ArrayList<Integer> freq){
       ArrayList<String> items1=new ArrayList<String>();
       ArrayList<Integer> freq1=new ArrayList<Integer>(); 
       for(int i=0;i<items.size();i++){
           items1.add(items.get(i));
       }
       for(int i=0;i<items.size();i++){
           freq1.add(freq.get(i));
       }
       for(int i=0;i<freq.size();i++){
           if(freq.get(i)<min_sup){
               items1.set(i,"*");
           }
       }
       freq.clear();
       items.clear();
       for(int i=0;i<freq1.size();i++){
            if(items1.get(i)!="*"){
                items.add(items1.get(i));
                freq.add(freq1.get(i));
            }
        }
       return items;
   }
   public ArrayList<String> convertStringToArray(String s){
       ArrayList<String> co=new ArrayList<String>();
        String temp="";
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)!=' ') {
                temp+=s.charAt(i);
                if(i==s.length()-1){
                    co.add(temp);
                    temp="";    
                }
            }else {
                co.add(temp);
                temp="";
            }
        }          
        return co;
   }
   public String removerRedundant(String comp){
        LinkedHashSet <String> lhs = new LinkedHashSet<String>();
        ArrayList<String> co=new ArrayList<String>();
        co=convertStringToArray(comp);          
        lhs.addAll(co);
        co.clear();
        co.addAll(lhs);
        comp="";
        for(int i=0;i<co.size();i++){
            comp+=" "+co.get(i);
        } 
        if(comp.charAt(0)==' '){
            comp=comp.substring(1);
        }
        return comp;        
   }
    public ArrayList<String> compineFreqItems(ArrayList<String> items) throws IOException{
        String comp="";
        ArrayList<String> items1=new ArrayList<String>();
        for(int i=0;i<items.size();i++){
            for(int j=i+1;j<items.size();j++){
                comp+=items.get(i)+" "+items.get(j);
                comp=removerRedundant(comp);   
                if(!items1.contains(comp)||items1.size()==0){
                    items1.add(comp);
                    comp="";
                }
            }   
        }
        return items1;
    }
    public void calculateConfidence(ArrayList<String> sets) throws IOException{
        ArrayList<String> item  =new ArrayList<String>();
        ArrayList<String> temp  =new ArrayList<String>();
        ArrayList<String> confsets  =new ArrayList<String>();
        ArrayList<Double> conf   =new ArrayList<Double>();
        ArrayList<Integer> fr   =new ArrayList<Integer>(); 
        ArrayList<Integer> setfr=new ArrayList<Integer>(); 
        
        setfr.addAll(makefreqlist(sets));
        
        
        for(int i=0;i<sets.size();i++){
            item=convertStringToArray(sets.get(i));
            temp=compineFreqItems(item);
            temp.addAll(item);
            fr=makefreqlist(temp);
            double cofident=0;
            for(int j=0;j<fr.size();j++){
                cofident=(double)((double)setfr.get(i)/(double)fr.get(j))*100;
                if(cofident>=min_conf){
                    confsets.add(temp.get(j));
                    conf.add(cofident);
                }
            }
            System.out.println("Strong Rules for set No "+(i+1)+" is : "+confsets);
            System.out.println("their confidences "+(i+1)+" is : "+conf);
        }
        
    }
}
















