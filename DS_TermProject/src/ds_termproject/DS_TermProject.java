/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ds_termproject;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author DELL
 */
public class DS_TermProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
        tree t=new tree();
       // System.out.println("1- &&&&&&&&&&&&&");
    //
        t.CreateIndexFileFile("Btree",5,4);
        //System.out.println("2- &&&&&&&&&&&&&");
        RandomAccessFile file=new RandomAccessFile("Btree"+".bin", "rw");
        t.DisplayIndexFileContent("Btree");
        t.RefrishTreeInfo("Btree");
        //System.out.println("3- &&&&&&&&&&&&&");
        //
       
        ArrayList<Integer> n=new ArrayList<Integer>();
        n.add(4*2+1);
        for(int i=1;i<4*2+1;i++){
        n.add(-1);
        }
        t.writeopject(file,n, 0);
        n.clear();
        n.add(1);
        n.add(3);
        n.add(2);
        n.add(7);
        n.add(3);
        n.add(21);
        n.add(4);
        n.add(-1);
        n.add(-1);
        
        
        t.writeopject(file,n,1);
        n.clear();
        n.add(0);
        n.add(1);
        n.add(0);
        n.add(2);
        n.add(0);
        n.add(3);
        n.add(0);
        n.add(-1);
        n.add(-1);
        t.writeopject(file,n,2);
        n.clear();
        n.add(0);
        n.add(5);
        n.add(0);
        n.add(7);
        n.add(0);
        n.add(-1);
        n.add(-1);
        n.add(-1);
        n.add(-1);
        t.writeopject(file,n,3);
        n.clear();
        n.add(0);
        n.add(9);
        n.add(0);
        n.add(10);
        n.add(0);
        n.add(13);
        n.add(0);
        n.add(21);
        n.add(0);
       t.writeopject(file,n,4);
       System.out.println(" --------------------------------- ");
             t.DisplayIndexFileContent("Btree");
        //System.out.println(t.SearchARecord("Btree",15));
        ///
        System.out.println(" Delete 10");
        t.DeleteRecordFromIndex("Btree",10);
        t.DisplayIndexFileContent("Btree");
        System.out.println(" Delete 7");
        t.DeleteRecordFromIndex("Btree",7);
        t.DisplayIndexFileContent("Btree");///
        System.out.println(" Delete 5");
        t.DeleteRecordFromIndex("Btree",5);
        t.DisplayIndexFileContent("Btree");///
        System.out.println(" Delete 2");
        t.DeleteRecordFromIndex("Btree",2);
        t.DisplayIndexFileContent("Btree");///
        /*System.out.println(" Delete 17");
        t.DeleteRecordFromIndex("Btree",17);
        t.DisplayIndexFileContent("Btree");
        System.out.println(" Delete 18");
        t.DeleteRecordFromIndex("Btree",18);
        t.DisplayIndexFileContent("Btree");////
        System.out.println(" Delete 11");
        /*t.DeleteRecordFromIndex("Btree",11);
        t.DisplayIndexFileContent("Btree");
        System.out.println(" Delete 12");
        t.DeleteRecordFromIndex("Btree",12);
        t.DisplayIndexFileContent("Btree");
        System.out.println(" Delete 14");
        t.DeleteRecordFromIndex("Btree",14);
        t.DisplayIndexFileContent("Btree");
        System.out.println(" Delete 15");
        t.DeleteRecordFromIndex("Btree",15);
        t.DisplayIndexFileContent("Btree");
        //*/
    }
    /* -----------------------layla's main ---------------------------------------
    String FileName="Index_File.bin";
        int M;
        int NumberOfRecords;
        int Choice=0;
        int InsertNum=0;
        int Value=0;
        int Index=0;
        
        tree T=new tree();
        
        RandomAccessFile FileStore=new RandomAccessFile(FileName, "rw");
        
        Scanner Input= new Scanner(System.in);
        
                
        System.out.println("-------------------------------------------------------------");
        System.out.print("Enter Number of Records : ");
        NumberOfRecords=Input.nextInt();
        
        System.out.println("-------------------------------------------------------------");
        System.out.print("Enter Number of Descendants : ");
        M=Input.nextInt();
        
        T.CreateIndexFileFile1(FileStore,NumberOfRecords,M);
        T.DisplayIndexFileContent(FileName);
        
        
        while (true)
        {           

            System.out.println("--------------------------InsertNewRecordAtIndex------------------------------");

            System.out.print("Enter Your Value : ");
            Value=Input.nextInt();

            System.out.print("Enter Your Index : ");
            Index=Input.nextInt();

            System.out.print("Result = ");
            System.out.println(T.InsertNewRecordAtIndex(FileStore,Value, Index,1));

            System.out.println("---------------------------");

            T.DisplayIndexFileContent(FileName);

            System.out.println("---------------------------");
                   

        }
    }
    
    */ 
}
