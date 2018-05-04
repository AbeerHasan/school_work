/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dm_assignment_3;

import java.io.IOException;
import java.util.ArrayList;
import java.math.BigInteger;
import java.util.*;
import java.util.function.Predicate;
/**
 *
 * @author DELL
 */

public class DM_assignment_3 {
    //*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
          // TODO code application logic here
        Clustring_K_Means c=new Clustring_K_Means();
        c.setinfo(3,200);
        
        if(c.CheckifContinuaseNumeric(c.getitems(1))&&c.CheckifContinuaseNumeric(c.getitems(2))){
            c.set_Centroid();
            c.MaketheClusters(c.centroids);
            while(!c.stopingCondetion(c.clusters,c.Oldclusters)){
                c.MaketheClusters(c.RecalculateTheCentroid());
            }
        }else{
            System.out.println("not countinuase");
        }
    }
}
