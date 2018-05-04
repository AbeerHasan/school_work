package dsassi_2;
/**
 *
 * @author DELL
 */
import java.io.RandomAccessFile;
import java.util.*;
public class DSAssi_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
    Scanner cin = new Scanner(System.in);
    File f=new File();
    ArrayList<String> a=new ArrayList<String>();
    ArrayList<String> b=new ArrayList<String>();
    ArrayList<String> c=new ArrayList<String>();
    int n1=0,n2=0,n3=0,n4=0;
    String s=" ";
    System.out.println("Data file contents :");
    f.DisplayAllRecords("Data.bin");
    System.out.println("Enter run size :");
    n1=cin.nextInt();
    System.out.println("Runs contents :");
    a=f.DivideInputFileIntoNRuns("Data.bin" ,n1);
    f.DisplayRunsContent(a);
    System.out.println("Sorted runs contents :");
    b=f.SortEachRunOnMemoryAndWriteItBack(a);
    f.DisplayRunsContent(b);
    /*System.out.println("Enter number to search :");
    n2=cin.nextInt();
    System.out.println("Enter run name to search in:");
    s=cin.nextLine();
    s=cin.nextLine();
    n3=f.BinarySearchOnSortedFile(s, n2);
    System.out.println("Your value is in index :"+n3);
    */System.out.println("Enter size of memory :");
    n4=cin.nextInt();
    System.out.println("Enter the sorted file name :");
    s=cin.nextLine();
    s=cin.nextLine();
   // f.DoNWayMergeAndWriteASortedFile(b,n4,s);
    System.out.println("Sorted file contents :");
    f.DisplayAllRecords(s);
    
    for(int i=0;i<(64/4)/n4;i++){
    for(int d=0;d<n4;d++){
        c.add(b.get(d));
        System.out.print(" * "+c.get(d));
    }
    f.DoMergeFile(c, "D"+i+".bin");
    }
    for(int j=0;j<(64/4)/n4;j++){
    RandomAccessFile file = new RandomAccessFile("D"+j+".bin", "rw");
    for(int i=0;i<file.length()/4;i++){
        
      System.out.println(file.readInt());
      }
    System.out.println("---------------------------------");
    }
    }
}
