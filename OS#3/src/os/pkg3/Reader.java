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

public class Reader extends Thread{
    public Buffer buf;
    public String name;

    public Reader() {
        name="";
    }
        
    public Reader(Buffer buf, String name) {
        this.buf = buf;
        this.name = name;
    }
    public void run(){
        System.out.println(this.name+" Starting reading");
        if(buf.write.count>0 && buf.read.count>0){
            System.out.print(this.name+" read : ");
            buf.Read();
            System.out.println(this.name+" finished reading");
        }
        else{
            System.out.println(this.name+" Blooked");
        }
    }
    
    
}    

