
import java.io.RandomAccessFile;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DELL
 */
import java.math.BigInteger;
public class DM_assignment_2 {
 public static long Solving(int n1, int n2)
     {
        int min=0,n=n2,count=0;
        boolean b=false;
        while(b==false){
           for(int i=n1;i<=n2;i++){
               if(n%i==0){
                 count++;
               }
           }
           if(count==(n2-n1)+1)
             { b=true;
            }else {n++;}
           count=0;
        }
        return n;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {

        System.out.print(Solving(1,10));//  , max2 = 2798 the answer is 20855802
        
        
        /*/ TODO code application logic here
        BayesianClassifier b=new BayesianClassifier();
        b.setfileName();
        b.GetAllDataSet();
        System.out.println("class labels"+b.getitems());
        b.classLabel_calculateprob();
        RandomAccessFile file = new RandomAccessFile("Car.validation.features.txt","rw");
        file.seek(0);
        String l="";
        int i=1;
        while((l=file.readLine())!=null){
            System.out.println("tuble No : "+i);
            System.out.println("Bayesian classefire :");
            b.Bayesian(l);
            System.out.println("K-nearest classefire :");
            b.K_nearest(l,10);
            System.out.println("----------------------------------------------------------");
            i++;
            //if(i==11){
              //  break;
           // }
        }
        file.close();
        System.out.println("Accuracy : ");
        b.calculateAccuracy(b.bayesian);
        System.out.println("K-nearest Accuracy : ");
        b.calculateAccuracy(b.k_nearest);
*/}
       
    
}
