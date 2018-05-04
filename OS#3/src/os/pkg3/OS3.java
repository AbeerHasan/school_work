/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.pkg3;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class OS3 {

    static Buffer buf ;
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        String s;
        System.out.println("Enter intial buffer !");
        s = input.nextLine();
        buf = new Buffer(s);
        System.out.println("Enter num reader !");
        int numReader = input.nextInt();
        System.out.println("Enter num writer !");
        int numWriter = input.nextInt();
        String []readers = new String[numReader];
        String []writers = new String[numWriter];
        String []data = new String[numWriter];
        for(int i=0;i<numReader;i++){
            System.out.println("Enter reader name !");
            String name = input.next();
            readers[i] = name;
        }
        for(int i=0;i<numWriter;i++){
            System.out.println("Enter writer name !");
            String name = input.next();
            System.out.println("Enter data to be written !");
            String dataa = input.next();
            writers[i] =name;
            data[i] =dataa;
        }
        for(int i=0;i<numReader;i++){
            Reader r = new Reader(buf,readers[i]);
            r.start();
        }
        for(int i=0;i<numWriter;i++){
            Writer w = new Writer(buf,writers[i],data[i]);
            w.start();
        }

    }

  
    
}
