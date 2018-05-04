/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbd_assi2;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class DBD_assi2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        Normal_Form F=new Normal_Form();
        ArrayList<String> R=new ArrayList<String>();
        R.add("A");
        R.add("B");
        R.add("C");
        R.add("D");
        F.filename="F";
        F.Get_Keys(R);
        int nf=F.Check_normal_form();
        if(nf!=4){
            F.R_In_BCNF(nf);
        }
    }
    
}
