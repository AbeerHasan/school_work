/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbd_assignment_1;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author DELL
 */
public class DBD_Assignment_1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        // TODO code application logic here
        Scanner in = new Scanner(System.in);
        Functional_Dependencies F=new Functional_Dependencies();
        String att1="",att2="";
        
        System.out.println("Enter Determinant : ");
        att1=in.nextLine();
        System.out.println("Enter Dependent : ");
        att2=in.nextLine();
        
        if(F.Functional_Dependency_between(att1,att2)==true){
            System.out.println("Yes "+att1+" --> "+att2);
        }else {
            System.out.println("No "+att2+" dose not Depende on "+att1);
        }
        //-------------------------------------------------------------------------------
        ArrayList<String> R=new ArrayList<String>();
        R.add("A");
        R.add("B");
        R.add("C");
        R.add("D");
        F.filename="F";
        F.Get_Keys(R);
        F.Get_Minimal_Coverage();
        int nf=F.Check_normal_form();
        if(nf!=4){
            F.R_In_BCNF(nf);
        }
        
    }
    
}
