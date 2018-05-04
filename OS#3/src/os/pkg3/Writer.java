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
public class Writer extends Thread{
        public Buffer buf;
        public String name;
        public String data;

    public Writer(Buffer buf, String name, String data) {
        this.buf = buf;
        this.name = name;
        this.data = data;
        
    }
    public void run(){
        System.out.println(this.name+" starting writing");
        if(buf.write.count==1 && buf.read.count==2){
            System.out.print(this.name+" writing: ");
            buf.Write(data);
            System.out.println(this.name+" finished writing");
        }
        else{
            System.out.println(name +"Blocked");
        }
    }    
}
