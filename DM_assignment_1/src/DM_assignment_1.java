/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.RandomAccessFile;
/**
 *
 * @author DELL
 */
public class DM_assignment_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
    Scanner cin = new Scanner(System.in);    
    System.out.println("Enter file name");
    String f="";
    f=cin.next();
    Association_Apriori A=new Association_Apriori();
    
    ArrayList<String> s=new ArrayList<String>();
    ArrayList<String> ss=new ArrayList<String>();
    ArrayList<Integer> fr=new ArrayList<Integer>();
    
    A.Set_MinSup(200);
    A.Set_MinConf(60);
    A.set_filename(f);
    A.GetAllTransactions();
    s=A.getitems();
    System.out.println("Inetial Item sets : "+s);
    
    int flage=1;
    
    while(flage>0){
        flage=0;
        fr=A.makefreqlist(s);
        System.out.println("Freqqences : "+fr);
        for(int i=0;i<fr.size();i++){
            if(fr.get(i)<3){
                flage++;
            }
        }
        if(flage==0){
            break;
        }
        ss=A.elemenateInfreqItems(s,fr);
        System.out.println("After Elemenation : "+ss);
        s=A.compineFreqItems(ss);
        System.out.println("After compination : "+s);
        System.out.println("-----------------------------------------------------------------------");
        
    }
    System.out.println("final Item sets : "+s);
    
    A.calculateConfidence(s);
}
        
}
