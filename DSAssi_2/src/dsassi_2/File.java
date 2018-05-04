package 
dsassi_2;
/**
 *
 * @author DELL
 */
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class File {
    
    public void DisplayAllRecords(String filename ) throws Exception {
        RandomAccessFile file = new RandomAccessFile(filename, "rw");
        long l = (file.length()/4);// 3shan 23raf 3add el records
        for (int i = 0; i < l; i++) 
        {
            System.out.println(file.readInt());
        }
        file.close();
    }

    public ArrayList<String> DivideInputFileIntoNRuns (String Inputfilename, int runSize)throws Exception {
        
        ArrayList<String> files=new ArrayList<String>();
        ArrayList<Integer> run=new ArrayList<Integer>();
        RandomAccessFile file = new RandomAccessFile("Data.bin", "rw");
        /////////////////////////////////////////////////////////////////////////////////
        int mod=(int)(file.length()/4)%runSize;
        int x=(int)(file.length()/4)/runSize;;
        /////////////////////////////////////////////////////////////////////////////////");
        for(int i=0;i<x;i++)
        {
         for(int j=0;j<runSize;j++){
         run.add(file.readInt());
         }
         RandomAccessFile f = new RandomAccessFile("R"+i+".bin", "rw");
         for(int y=0;y<runSize;y++){
             f.writeInt(run.get(y));
         }
         run.clear();
         files.add("R"+i+".bin");
        }
        /////////////////////////////////////////////////////////////////////////////////");
        if(mod!=0){
            file.seek(0);
            int remain=((int)file.length())-(x*4*runSize);
            file.seek(x*4*runSize);
            for(int j=0;j<remain/4;j++){
                run.add(file.readInt());
                }
            file.close();
            RandomAccessFile f = new RandomAccessFile("R"+x+".bin", "rw");
            for(int y=0;y<remain/4;y++){
             f.writeInt(run.get(y));
            }
            f.close();
            files.add("R"+x+".bin");
        }
      
        return files;    
    }
    public void DisplayRunsContent (ArrayList<String> RunsFilesNames)throws Exception{
      for(int i=0;i<RunsFilesNames.size();i++){
        RandomAccessFile f = new RandomAccessFile(RunsFilesNames.get(i), "rw");
        System.out.println("run no "+i+" contents :");
        for(int x=0;x<f.length()/4;x++){
            System.out.println(f.readInt());
        }
        f.close();
    }
    }
    ArrayList<String> SortEachRunOnMemoryAndWriteItBack (ArrayList<String> RunsFilesNames) throws Exception{
      
        ArrayList<Integer> run=new ArrayList<Integer>();
        
        for(int i=0;i<RunsFilesNames.size();i++){
        RandomAccessFile f = new RandomAccessFile(RunsFilesNames.get(i), "rw");
        System.out.println("run no "+i+" contents :");
        for(int x=0;x<f.length()/4;x++){
            run.add(f.readInt());
        }
        run.sort(null);
        f.seek(0);
        for(int x=0;x<f.length()/4;x++){
            f.writeInt(run.get(x));
        }
        f.close();
        run.clear();
    }
        return RunsFilesNames;
    }
    public int BinarySearchOnSortedFile(String Sortedfilename, int SearchValue)throws Exception{
        RandomAccessFile file = new RandomAccessFile(Sortedfilename, "rw");
        int l =(int) (file.length()/4);
        int ind=0;
        int low = 0;
        int high =l-1;
          
         while(high >= low) {
            int middle = (high + low)/ 2 ;
            file.seek(0);
            file.seek(middle*4);
            int x=file.readInt();
            if( x< SearchValue) {
                low = middle + 1;
            }else if(x> SearchValue) {
                high = middle - 1;
            }else if(x == SearchValue){
                return middle;
            }
    }
    return -1;
}
    public int getminInd(ArrayList <Integer> a){
        int min=1000,i=0,x=a.size();
        int ind=-1;
        while(i<x){
            if(min>a.get(i)){
            min=a.get(i);
            }
            i++;
            }
        for(int j=0;j<x;j++){
            if(min==a.get(j)){
               ind=j;
                break;
            }
        }
        return ind;
    }
    
    public int getmin(ArrayList <Integer> a){
        int min=1000,i=0,x=a.size();
        while(i<x){
            if(min>a.get(i)){
            min=a.get(i);
            }
            i++;
            }
        return min;
    }
    public int check(ArrayList<Integer> a,int n){
        int x=0;
        for(int i=0;i<a.size();i++){
            x+=a.get(i);
        }
        if(x==n*1000){
            return 1;
        }
        return -1;
    }
    public void DoMergeFile(ArrayList<String> files,String filename)throws Exception{
        int i=0;
        ArrayList<RandomAccessFile> a=new ArrayList<RandomAccessFile>();
        RandomAccessFile ff;
        for(int f=0;f<files.size();f++){
         ff= new RandomAccessFile(files.get(f), "rw");
        a.add(ff);
        }
        System.out.println(a.size()+"*");
        RandomAccessFile file = new RandomAccessFile(files.get(i), "rw");
        int c=files.size(),r=(int)file.length()/4,min=0;
        ArrayList<Integer> a2=new ArrayList<Integer>();
       // ArrayList<Integer> rs=new ArrayList<Integer>();
        ArrayList<Integer> pos=new ArrayList<Integer>();
            for(int j=0;j<files.size();j++){
                a.get(j).seek(0);
                int x=a.get(j).readInt();
                a2.add(x);
                pos.add(0);
            }
            RandomAccessFile mrg = new RandomAccessFile(filename, "rw");
           int b=-1;
         while(b==-1){
         min=getminInd(a2);
         mrg.writeInt(getmin(a2));
         if(pos.get(min)+1<r){
         pos.set(min,pos.get(min)+1);
         a.get(min).seek(0);
         a.get(min).seek(pos.get(min)*4);
         int x=a.get(min).readInt();
         a2.set(min,x);
         }else {
            a2.set(min,1000); 
          }
           b=check(a2,files.size());
         }
         i++;
    }
    public void clear(int [][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a[0].length;j++){
                a[i][j]=-1;
            }
        }
    }
   /* public void DoNWayMergeAndWriteASortedFile(ArrayList<String> SortedRunsNames, int N ,String Sortedfilename)throws Exception{
        RandomAccessFile file = new RandomAccessFile(SortedRunsNames.get(0), "rw");
        ArrayList<String> dMerge=new ArrayList<String>();
        ArrayList<String> dr=new ArrayList<String>();
        int i=0,n=0;
        
        while(i<SortedRunsNames.size()){
        for (int j=i;j<N;j++){
            d.add(SortedRunsNames.get(i));
        }
            System.out.println("nwy"+d.size());
        DoMergeFile(d,"M"+n+".bin");
        dr.add("M"+n+".bin");
        i+=N;
        n++;
        N+=N;
        d.clear();
        }
        int x=0;
        while(dr.size()>1){
            System.out.println("1- %%%%%%%%% "+dr.size());
    /*        for (int j=0;j<N;j++){
            String s=dr.get(j);
            System.out.println("2- %%%%%%%%%"+dr.size());    
            dr.remove(j);
        for(int m = 0;m<dr.size()-N;m+=N)
        {
        //d.addAll();           
        DoMergeFile((ArrayList)dr.subList(m, m+N-1),"M"+n+".bin");
        
        }
        
        dr.add("M"+n+".bin");
       // x+=N;
        n++;
        N+=N;
        d.clear();
        }
        //Sortedfilename
        RandomAccessFile fi = new RandomAccessFile(Sortedfilename, "rw");
        RandomAccessFile fi2 = new RandomAccessFile(dr.get(0), "rw");
        
        for(int g=0;g<(int)fi2.length()/4;g++){
        fi.writeInt(fi2.readInt());
    }
    }   
    
    /*  public ArrayList<Integer> DoMerge(int [][] a){
        int r=a.length,c=a[0].length,min=0;
        ArrayList<Integer> a2=new ArrayList<Integer>();
        ArrayList<Integer> rs=new ArrayList<Integer>();
        ArrayList<Integer> pos=new ArrayList<Integer>();
            for(int j=0;j<c;j++){
                a2.add(a[0][j]);
                pos.add(0);
            }
           int b=-1;
         while(b==-1){
         min=getminInd(a2);
         rs.add(getmin(a2));
         if(pos.get(min)+1<r){
         pos.set(min,pos.get(min)+1);
         a2.set(min,a[pos.get(min)][min]);
         }else {
            a2.set(min,1000); 
          }
           b=check(a2,a[0].length);
         }
         return rs;
    }*/
  /*  public ArrayList<Integer> DoMerge(int [][] a){
        int r=a.length,c=a[0].length,min=0;
        ArrayList<Integer> a2=new ArrayList<Integer>();
        ArrayList<Integer> rs=new ArrayList<Integer>();
        ArrayList<Integer> pos=new ArrayList<Integer>();
            for(int j=0;j<c;j++){
                a2.add(a[0][j]);
                pos.add(0);
            }
           int b=-1;
         while(b==-1){
         min=getminInd(a2);
         rs.add(getmin(a2));
         if(pos.get(min)+1<r){
         pos.set(min,pos.get(min)+1);
         a2.set(min,a[pos.get(min)][min]);
         }else {
            a2.set(min,1000); 
          }
           b=check(a2,a[0].length);
         }
         return rs;
    }*/
    
    /*
    public ArrayList<Integer> DoMerge(ArrayList<Integer> a1,ArrayList<Integer> a2){
        int x=a1.size(),y=a2.size();
        ArrayList<Integer> resulte=new ArrayList<Integer>();
       int i=0,j=0;
        while(i<x||j<y){
            if(i>=x){
                for(int h=j;h<y;h++){
                    resulte.add(a2.get(h));
                }
                break;
            }else if(j>=y){
                for(int h=i;h<x;h++){
                    resulte.add(a2.get(h));
                }
                break;
            }  
          if(a1.get(i)<a2.get(j)){
              while(a1.get(i)<a2.get(j)){
                  resulte.add(a1.get(i));
                  i+=1;
                  if(i==x){
                      break;
                  }
              }
          }else if(a1.get(i)>a2.get(j)){
              while(a1.get(i)>a2.get(j)){
                  resulte.add(a2.get(j));
                  j+=1;
                  if(j==x){
                      break;
                  }
              }
          }else {
              while(a1.get(i)==a2.get(j)){
                  resulte.add(a1.get(i));
                  resulte.add(a2.get(j));
                  i+=1;
                  j+=1;
                  if(i==x||j==y){
                      break;
                  }
              }
          }
    }
        return resulte;
    }
    */
        /*///////////////////////////////////////////////////////////////////////////
        while(n2>1){
        while(m<n2/2){
        ArrayList<Integer> a1=new  ArrayList<Integer>();
        ArrayList<Integer> a2=new  ArrayList<Integer>();
        for(int j=0;j<r;j++){
            a1.add(arr1[j][m]);
            a2.add(arr1[j][m+1]);
        }
        a1=DoMerge(a1,a2);
        
        for(int h=0;h<r*2;h++){
            arr2[h][m]=a1.get(h);
        }
        m+=1;
        }
        if(n1%2!=0){
          for(int h=0;h<r;h++){
            arr2[h][m]=arr1[h][N-1];
        }   
        }
        //////////////////////////////////////////////////////////////////////////
        clear(arr1);
        for(int k=0;k<arr2.length;k++){
            for(int g=0;g<arr2[0].length;g++){
                arr1[k][g]=arr2[k][g];
            }
        }
        clear(arr2);
        n1=n2;
        n2=n2-1;
        }
  ////////////////////////////////////////      
        }
          RandomAccessFile f = new RandomAccessFile("M"+mergeFilecount+".bin", "rw");
        for(int i=0;i<r*N;i++){
            f.writeInt(arr2[i][0]);
            System.out.println(arr2[i][0]);
        }
        mergeFilecount+=1;
        count+=N;   
        }
        ///////////////////////////////////////////////////////////////////////
      
    }

}
/*1- Sort the data in the file using n-way merge sort.
2- Do a binary search on the sorted file.
In order to deliver your program, you must use the following functions header:

String [] DivideInputFileIntoNRuns (String Inputfilename, int runSize) (1 grade)
void DisplayRunsContent (String [] RunsFilesNames) (0.5 grade)
String [] SortEachRunOnMemoryAndWriteItBack (String [] RunsFilesNames) (0.5 grade)
int BinarySearchOnSortedFile(String Sortedfilename, int SearchValue) (2 Grade)
  
void DoNWayMergeAndWriteASortedFile(String [] SortedRunsNames, int N ,String Sortedfilename) (2 Grade) // You should Display the sorted file after merging is done.*/
/* array to keep track of non considered positions in subarrays
        int[] curPos = new int[r];
        /// final merged array
        int[] mergedArray = new int[r * N];
        int p = 0;
        while (p < r * N){
            int min = Integer.MAX_VALUE;
            int minPos = -1;
            /// search for least element
            for (int i = 0; i < r; i++)
            {
                if (curPos[i] < N)
                {
                    if (arr[i][curPos[i]] < min)    {
                       min = arr[i][curPos[i]];
                       minPos = i;
                    }
                }
            }
            curPos[minPos]++;
            mergedArray[p++] = min;
        }
        
/*merge sort
    private int[] merge(int[][] arr) 

    {

        int K = arr.length;

        int N = arr[0].length;

 

        /** array to keep track of non considered positions in subarrays 

        int[] curPos = new int[K];

 

        /** final merged array 

        int[] mergedArray = new int[K * N];

        int p = 0;

 

        while (p < K * N)

        {

            int min = Integer.MAX_VALUE;

            int minPos = -1;

            /** search for least element 

            for (int i = 0; i < K; i++)

            {

                if (curPos[i] < N)

                {

                    if (arr[i][curPos[i]] < min)

                    {

                        min = arr[i][curPos[i]];

                        minPos = i;

                    }

                }                

            }

            curPos[minPos]++;            

            mergedArray[p++] = min;

        }

        return mergedArray;

    }

 

     Main method 

    public static void main(String[] args) 

    {
public static List<Integer> merge(List<List<Integer>> data){
        int totalSize = 0;
        for(List<Integer> l:data)
            totalSize = totalSize + l.size();
       
        List<Integer> result = new ArrayList<Integer>();
        List<Integer> min ;
       
        while(result.size()<totalSize){
            min = null;
            for(List<Integer> l: data){
               
                if(min==null)
                    min = l;
                else if(l!=null && l.get(0)<min.get(0)){
                    min = l;
                }
            }
           
            result.add(min.get(0));
            min.remove(0);
        }
       
        return result;
       
    }
binary search
int[] data;
2    int size;
3
4    public boolean binarySearch(int key) 
5    {
6         int low = 0;
7         int high = size - 1;
8          
9         while(high >= low) {
10             int middle = (low + high) / 2;
11             if(data[middle] == key) {
12                 return true;
13             }
14             if(data[middle] < key) {
15                 low = middle + 1;
16             }
17             if(data[middle] > key) {
18                 high = middle - 1;
19             }
20        }
21        return false;
22   }
*/}