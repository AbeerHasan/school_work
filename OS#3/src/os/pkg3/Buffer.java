/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package os.pkg3;

/**
 *
 * @author DELL
 */
public class Buffer {
    private static String buffer;
    
    semaphore write= new semaphore(1);
    semaphore read= new semaphore(2);
    
    public Buffer(String s){
        buffer = s;
    }
    public synchronized void Write(String s){
        write.P();
        buffer+=s;
        System.out.println(buffer);
        write.V();
    }
    public synchronized void Read(){
        read.P();
        System.out.println(buffer);
        read.V();
    }
}
