/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smallest.factorial.divisable.by.the.number;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public class SmallestFactorialDivisableByTheNumber {
    public static ArrayList<BigInteger> facts=new ArrayList<BigInteger>();
    public static ArrayList<Integer> nums=new ArrayList<Integer>();
        
        public static int getFacts(int newN){
            BigInteger N = new BigInteger(newN+"");
            BigInteger fact = BigInteger.valueOf(1);
            if(facts.isEmpty()){
                int i=2;
                while(i<=N.intValue()){
                    fact = fact.multiply(BigInteger.valueOf(i));
                    facts.add(fact);
                    nums.add(i);
                    i++;
                }
            }else {
                int i=nums.get(nums.size()-1)+1;
                fact=facts.get(facts.size()-1);
                int j=1;
                while(fact.divideAndRemainder(N)[1]!=BigInteger.ZERO){
                    //System.out.print("0 ");
                    fact = fact.multiply(BigInteger.valueOf(i));
                    if(j<=15){
                        facts.add(fact);
                        nums.add(i);
                    }
                    i++;
                    j++;
                }
                return i-1;
            }
            return 0;
        }
        
        public static int binarySearch(int search) {
	    //System.out.println("1");
            int start=0;
            int end=(facts.size()-1);
            while(start<=end){
                int m = (start + end) / 2;
                if(facts.get(m).divideAndRemainder(BigInteger.valueOf(search))[1]!=BigInteger.ZERO){
                    start=m+1;
                    if(facts.get(start).divideAndRemainder(BigInteger.valueOf(search))[1]==BigInteger.ZERO){
                        return start;
                    }
                }else {
                    end=m-1;
                    if(end>=0&&facts.get(end).divideAndRemainder(BigInteger.valueOf(search))[1]==BigInteger.ZERO){
                        if((end-1)>=0&&facts.get(end-1).divideAndRemainder(BigInteger.valueOf(search))[1]!=BigInteger.ZERO){
                            return end;
                        }
                    }
                }
            }
            return -1;
        }///
        public static int get_SmallestInteger(int n){
            BigInteger N = new BigInteger(n+"");
            BigInteger fact = BigInteger.valueOf(1);
            int i=0;
            if(facts.get(facts.size()-1).divideAndRemainder(BigInteger.valueOf(n))[1]!=BigInteger.ZERO){
                i=getFacts(n);
                //i=nums.get(nums.size()-1);
                return i;
            }
            i=binarySearch(n);
            return nums.get(i);
        }//*/
        
    //----------------------------------------------------------------------------------------------------------------    
        public static long Solving(int power) {
            double d=Math.pow(10,power);
            getFacts(10);
            long sum=2;
            //System.out.println("1");
            for(int i=3;i<=d;i++){
                BigInteger o=new BigInteger(i+"");
                
                while(facts.get(0).compareTo(o)==-1){
                    facts.remove(0);
                    nums.remove(0);
                }
                if(facts.get(0).equals(o)){
                    sum+=nums.get(0);
                    facts.remove(0);
                    nums.remove(0);
                }else if(facts.get(0).divideAndRemainder(o)[1]==BigInteger.ZERO){
                    sum+=nums.get(0);
                }else if(o.isProbablePrime(55)){
                    sum+=i;
                }else if(i%2==0 && (i/2)%2!=0 && o.divide(BigInteger.valueOf(2)).isProbablePrime(55)){
                    sum+=i/2;
                }else if(i%3==0 && (i/3)%2!=0 && o.divide(BigInteger.valueOf(3)).isProbablePrime(55)&&i/3!=3){
                    sum+=i/3;
                }else if(i%5==0 && (i/5)%2!=0 && o.divide(BigInteger.valueOf(5)).isProbablePrime(55)&& i/5!=5){
                    sum+=i/5;
                }else if(i%7==0 && (i/7)%2!=0 && o.divide(BigInteger.valueOf(7)).isProbablePrime(55)&&i/7!=7){
                    sum+=i/7;
                }else if(i%11==0 && (i/11)%2!=0 && o.divide(BigInteger.valueOf(11)).isProbablePrime(55)&&i/11!=11){
                    sum+=i/11;
                }else if(i%13==0 && (i/13)%2!=0 && o.divide(BigInteger.valueOf(13)).isProbablePrime(55)&&i/13!=13){
                    sum+=i/13;
                }else if(i%17==0 && (i/17)%2!=0 && o.divide(BigInteger.valueOf(17)).isProbablePrime(55)&&i/17!=17){
                    sum+=i/17;
                }else{
                    sum+=get_SmallestInteger(i);
                }
            }    
            return sum;
	}
    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     System.out.println(Solving(7)); //10125843 

    }
    
}
